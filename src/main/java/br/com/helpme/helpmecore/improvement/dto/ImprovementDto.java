package br.com.helpme.helpmecore.improvement.dto;

public class ImprovementDto {

    private String classification;
    private String title;
    private String message;

    public ImprovementDto() {
    }


    public String getClassification() {
        return classification;
    }

    public void setClassification(final String classification) {
        this.classification = classification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
