package com.importio.controller.serviceXML;

import com.importio.builder.ArticleBuilder;
import com.importio.model.Article;
import com.importio.model.Unit;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXArticle extends DefaultHandler {
    private ArticleBuilder articleBuilder;
    private List<Article> list;
    private String tag = "";

    @Override
    public void startDocument() throws SAXException {
        list = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("article")){
            articleBuilder = new ArticleBuilder();
        }
        if (qName.equals("Title") || qName.equals("Description") || qName.equals("UnitOfMeasurement") || qName.equals("Weight")){
            tag = qName;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String information = new String(ch, start, length);
        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {

            if (tag.equals("Title")){
                articleBuilder.withTitle(information);
            }
            if (tag.equals("Description")){
                articleBuilder.withDesription(information);
            }
            if (tag.equals("UnitOfMeasurement")){
                try{
                    articleBuilder.withUnitOfMeasurement(Unit.valueOf(information));
                }catch (Exception e){
                    System.out.println("SAXArticle.characters Exception");
                }
            }
            if (tag.equals("Weight")){
                try{
                    articleBuilder.withWight(Double.parseDouble(information));
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("article")){
            list.add(articleBuilder.toArticle());
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    public List<Article> getList() {
        return list;
    }
}
