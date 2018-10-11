package com.importio.controller.serviceXML;

import com.importio.model.Article;

import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class XMLWriter {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder = null;
    private String pathFile;

    public XMLWriter(String pathFile){

        this.factory = DocumentBuilderFactory.newInstance();
        this.pathFile = pathFile;
        try {
            this.builder = factory.newDocumentBuilder();
        }catch (ParserConfigurationException e){
            System.out.println("XMLWriter.XMLWriter ParserConfigurationException");
        }
    }

    public void xmlWrite(List<Article> list ){
        try{
            Document doc = builder.newDocument();
            Element rootElement=doc.createElement("articles");

            for (Article ar:list) {

                Element mainElement=doc.createElement("article");
                rootElement.appendChild(mainElement);

                    Element elementTitle = doc.createElement("Title");
                    elementTitle.appendChild(doc.createTextNode(ar.getTitle()));
                    mainElement.appendChild(elementTitle);

                    Element elementDescription = doc.createElement("Description");
                    elementDescription.appendChild(doc.createTextNode(ar.getDesription()));
                    mainElement.appendChild(elementDescription);

                    Element elementUnitOfMeasurement = doc.createElement("UnitOfMeasurement");
                    elementUnitOfMeasurement.appendChild(doc.createTextNode(ar.getUnitOfMeasurement().name()));
                    mainElement.appendChild(elementUnitOfMeasurement);

                    Element elementWeight=doc.createElement("Weight");
                    elementWeight.appendChild(doc.createTextNode(ar.getWeight().toString()));
                    mainElement.appendChild(elementWeight);

            }

            doc.appendChild(rootElement);

            Transformer t=TransformerFactory.newInstance().newTransformer();
            t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(pathFile)));

        }catch (TransformerException e){
            System.out.printf("TransformerException");
        }catch (IOException e){
            System.out.printf("IOException");
            e.printStackTrace();
        }
    }
}
