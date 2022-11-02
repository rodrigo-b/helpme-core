package br.com.helpme.helpmecore.improvement.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Scope("prototype")
public class SimilarityService {

    public double checkSimilarity(final String improvementSearch,final String improvementTitle){

        String improvementSearchWithoutWhiteSpace = improvementSearch.trim();
        String improvementTitleWithoutWhiteSpace = improvementTitle.trim();

        List<String> wordPairsSearch = getWordPairs(improvementSearchWithoutWhiteSpace);
        List<String> wordPairsTitle  = getWordPairs(improvementTitleWithoutWhiteSpace);

        AtomicInteger intersectionSize = getSizeOfIntersectionWords(wordPairsSearch, new ArrayList<>(wordPairsTitle));

        return calculateSimilarity(wordPairsSearch.size(), wordPairsTitle.size(), intersectionSize.get());

    }

    private AtomicInteger getSizeOfIntersectionWords(List<String> wordPairsSearch, List<String> wordPairsTitle) {
        AtomicInteger intersectionPoints = new AtomicInteger();

        wordPairsSearch.forEach(word -> {
            if(wordPairsTitle.contains(word)){
                intersectionPoints.getAndIncrement();
                wordPairsTitle.remove(word);
            }
        });
        return intersectionPoints;
    }

    private List<String> getWordPairs(String word){

        List<String> pairs = new ArrayList<>();

        for(int i = 0; i < word.length() - 1; i++) {
            pairs.add(word.substring(i, i+2));
        }

        return pairs;
    }

    private double calculateSimilarity(int searchPairsSize, int titlePairsSize, int intersectionSize){

        double similarity = (2.0 * intersectionSize)/ (titlePairsSize + searchPairsSize);
        return Math.round(similarity * 100);
    }

}
