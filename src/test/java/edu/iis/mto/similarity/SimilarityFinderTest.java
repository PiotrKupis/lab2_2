package edu.iis.mto.similarity;

import edu.iis.mto.searcher.SearchResult;
import edu.iis.mto.searcher.SequenceSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityFinderTest {

    private SimilarityFinder similarityFinder;
    private int[] firstArray = new int[]{1};
    private int[] copyOfFirstArray = new int[]{1};

    SequenceSearcher searcherThatAlwaysReturnsTrue = new SequenceSearcher() {
        @Override
        public SearchResult search(int elem, int[] sequence) {
            SearchResult.Builder builder = SearchResult.builder();
            builder.withFound(true);
            return builder.build();
        }
    };

    @Test
    void shouldReturnOne() {
        double result, expectedValue = 1;

        similarityFinder = new SimilarityFinder(searcherThatAlwaysReturnsTrue);
        result = similarityFinder.calculateJackardSimilarity(firstArray, copyOfFirstArray);

        assertEquals(expectedValue, result);
    }


}
