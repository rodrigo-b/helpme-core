package br.com.helpme.helpmecore.improvement.service.similarity;

import br.com.helpme.helpmecore.improvement.service.SimilarityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimilarityServiceTest {

    @Autowired
    private SimilarityService similarityService;


    @Test
    public void shouldReturn40PercentOfSimilarity(){

        String improvementSearch = "FRANCE";
        String wordToCompare     = "FRENCH";
        double similarity = similarityService.checkSimilarity(improvementSearch, wordToCompare);
        Assertions.assertEquals(40.0 , similarity);
    }


}

