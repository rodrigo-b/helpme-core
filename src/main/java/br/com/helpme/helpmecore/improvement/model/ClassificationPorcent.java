package br.com.helpme.helpmecore.improvement.model;

public class ClassificationPorcent {

    private String classification;
    private double totalLikesInPorcent;

    public ClassificationPorcent() {
    }

    public ClassificationPorcent(String classification, double totalLikesInPorcent) {
        this.classification = classification;
        this.totalLikesInPorcent = totalLikesInPorcent;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getTotalLikesInPorcent() {
        return totalLikesInPorcent;
    }

    public void setTotalLikesInPorcent(double totalLikesInPorcent) {
        this.totalLikesInPorcent = totalLikesInPorcent;
    }
}
