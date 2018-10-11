package com.importio.model;

public class Article {
    private String title;
    private String desription;
    private Unit unitOfMeasurement;
    private Double weight;

    private Article() {

    }

    public Article(String title, String desription, Unit unitOfMeasurement, Double weight) {
        this.title = title;
        this.desription = desription;
        this.unitOfMeasurement = unitOfMeasurement;
        this.weight = weight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public Unit getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(Unit unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", desription='" + desription + '\'' +
                ", unitOfMeasurement=" + unitOfMeasurement +
                ", weight=" + weight +
                '}';
    }
}
