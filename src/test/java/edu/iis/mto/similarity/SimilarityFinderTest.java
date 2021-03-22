package edu.iis.mto.similarity;

import edu.iis.mto.searcher.SearchResult;
import edu.iis.mto.searcher.SequenceSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityFinderTest {

    SequenceSearcher searcherThatAlwaysReturnsTrue = new SequenceSearcher() {
        @Override
        public SearchResult search(int elem, int[] sequence) {
            SearchResult.Builder builder = SearchResult.builder();
            builder.withFound(true);
            return builder.build();
        }
    };
    SequenceSearcher searcherThatAlwaysReturnsFalse = new SequenceSearcher() {
        @Override
        public SearchResult search(int elem, int[] sequence) {
            SearchResult.Builder builder = SearchResult.builder();
            builder.withFound(false);
            return builder.build();
        }
    };
    SequenceSearcher searcherThatReturnsTrueForNumberSmallerThanFive = new SequenceSearcher() {
        @Override
        public SearchResult search(int elem, int[] sequence) {
            SearchResult.Builder builder = SearchResult.builder();

            if (elem < 5)
                builder.withFound(true);
            else
                builder.withFound(false);

            return builder.build();
        }
    };

    private SimilarityFinder similarityFinder;
    private int[] emptyArray = new int[]{};
    private int[] firstArray = new int[]{1};
    private int[] copyOfFirstArray = new int[]{1};
    private int[] secondArray = new int[]{2};
    private int[] thirdArray = new int[]{1, 2, 8, 9, 10};
    private int[] fourthArray = new int[]{11, 7, 8, 9, 10};

    @Test
    void shouldReturnOne() {
        double result, expectedValue = 1;

        similarityFinder = new SimilarityFinder(searcherThatAlwaysReturnsTrue);
        result = similarityFinder.calculateJackardSimilarity(firstArray, copyOfFirstArray);

        assertEquals(expectedValue, result);
    }

    @Test
    void shouldReturnZero() {
        double result, expectedValue = 0;

        similarityFinder = new SimilarityFinder(searcherThatAlwaysReturnsFalse);
        result = similarityFinder.calculateJackardSimilarity(firstArray, secondArray);

        assertEquals(expectedValue, result);
    }

    @Test
    void shouldReturnQuarter() {
        double result, expectedValue = 0.25;

        similarityFinder = new SimilarityFinder(searcherThatReturnsTrueForNumberSmallerThanFive);
        result = similarityFinder.calculateJackardSimilarity(thirdArray, fourthArray);

        assertEquals(expectedValue, result);
    }

    @Test
    void passEmptySequenceShouldReturnOne() {
        double result, expectedValue = 1;

        similarityFinder = new SimilarityFinder(searcherThatAlwaysReturnsTrue);
        result = similarityFinder.calculateJackardSimilarity(emptyArray, emptyArray);

        assertEquals(expectedValue, result);
    }

    @Test
    void passTheSameArraysShouldReturnOneExecuteTenTimes() {
        double result, expectedValue = 1;

        for (int i = 0; i < 10; ++i) {
            similarityFinder = new SimilarityFinder(searcherThatAlwaysReturnsTrue);
            result = similarityFinder.calculateJackardSimilarity(firstArray, copyOfFirstArray);
            assertEquals(expectedValue, result);
        }
    }

    @Test
    void passEmptySequenceShouldReturnOneExecuteTenTimes() {
        double result, expectedValue = 1;

        for (int i = 0; i < 10; ++i) {
            similarityFinder = new SimilarityFinder(searcherThatAlwaysReturnsTrue);
            result = similarityFinder.calculateJackardSimilarity(emptyArray, emptyArray);
            assertEquals(expectedValue, result);
        }
    }

    @Test
    void passTheDifferentArraysShouldReturnZeroExecuteTenTimes() {
        double result, expectedValue = 0;

        for (int i = 0; i < 10; ++i) {
            similarityFinder = new SimilarityFinder(searcherThatAlwaysReturnsFalse);
            result = similarityFinder.calculateJackardSimilarity(firstArray, secondArray);
            assertEquals(expectedValue, result);
        }
    }


}
