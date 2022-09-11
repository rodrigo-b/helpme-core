package br.com.helpme.helpmecore.improvement.model;

import java.util.Arrays;
import java.util.Optional;

public enum Classification {

    LEISURE("leisure"),
    INFRASTRUCTURE("infrastructure"),
    SECURITY("security"),
    COMMUNICATION("communication"),
    FINANCIAL("financial");

    private final String classification;

    Classification(String classification) {
        this.classification = classification;
    }

    public Optional<Classification> getEnumByClassificationText(String classificationText){

        Optional<Classification> classificationOptional = Arrays.stream(values())
                .filter(classificationEnum -> classificationEnum.classification.equals(classificationText))
                .findAny();

        return classificationOptional;
    }
}
