package com.importio.view;

import com.importio.controller.ArticleController;

public class ArticleView {
    private ArticleController articleController;
    public ArticleView() {
        articleController = new ArticleController();
    }

    public void readfromXML(String pathFile){
        articleController.readfromXML(pathFile);
    }
    public void writeToXML(String pathFile){

        articleController.writeToXML(pathFile);
    }

}
