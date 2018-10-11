package com.importio.controller;

import com.importio.controller.serviceXML.SAXArticle;
import com.importio.controller.serviceXML.XMLWriter;
import com.importio.model.Article;
import com.importio.repository.JDBCArticle;
import com.importio.util.ConnectorMySQL;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

public class ArticleController extends DefaultHandler{
    private String path = "";
    private List<Article> listArticle;
    private ConnectorMySQL connectorMySQL;
    private Connection connection;
    private JDBCArticle jdbcArticle;
    private Timer timer;

    public ArticleController() {
        connectorMySQL = new ConnectorMySQL();
        jdbcArticle = new JDBCArticle(connectorMySQL.getConnection());
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    public void readfromXML(String pathFile) {
        if (pathFile != ""){


            try{
                File file = new File(pathFile);
                if (file.exists()){
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    factory.setValidating(true);
                    factory.setNamespaceAware(false);
                    SAXParser parser = factory.newSAXParser();

                    SAXArticle handler = new SAXArticle();
                    parser.parse(pathFile, handler);

                    List<Article> listArticle = handler.getList();

                    if (listArticle.size() > 0){
                        //Сохраняем в mySQL
                        jdbcArticle.save(listArticle);
                    }
                }
            }catch (ParserConfigurationException e){
                System.out.println("ParserConfigurationException");

            }catch (SAXException e){
                System.out.println("SAXException");

            }catch(IOException e){
                System.out.println("IOException");

            }
        }
    }
    public void writeToXML(String pathFile) {

        Locale local = new Locale("ru","RU");
        SimpleDateFormat dfDate = new SimpleDateFormat("dd.MM.yy");
        SimpleDateFormat dfTime = new SimpleDateFormat("HH_mm");

        Date currentDate = new Date();
        System.out.println("time = " + dfTime.format(currentDate));
        System.out.println("Date = " + dfDate.format(currentDate));

        pathFile = pathFile + "export_" + dfDate.format(currentDate) +
                "_" + dfTime.format(currentDate) + ".xml";

        System.out.println(pathFile);

        List<Article> list = jdbcArticle.getAll();
        XMLWriter xmlWriter = new XMLWriter(pathFile);
        xmlWriter.xmlWrite(list);

        //File file = new File();
    }

}
