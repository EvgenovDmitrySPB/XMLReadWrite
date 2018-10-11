package com.importio.builder;

import com.importio.model.Article;
import com.importio.model.Unit;

public class ArticleBuilder {
    private String title;
    private String desription;
    private Unit unitOfMeasurement;
    private Double weight;

    public ArticleBuilder() {

    }

    public ArticleBuilder withTitle(String title){
        this.title = title;
        return this;
    }
    public ArticleBuilder withDesription(String desription){
        this.desription = desription;
        return this;
    }
    public ArticleBuilder withUnitOfMeasurement(Unit unitOfMeasurement){
        this.unitOfMeasurement = unitOfMeasurement;
        return this;
    }
    public ArticleBuilder withWight(Double weight){
        this.weight = weight;
        return this;
    }

    public Article toArticle(){
        return new Article(this.title, this.desription, this.unitOfMeasurement, this.weight);
    }

}
