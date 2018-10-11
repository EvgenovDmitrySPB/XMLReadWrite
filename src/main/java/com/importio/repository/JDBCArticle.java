package com.importio.repository;

import com.importio.builder.ArticleBuilder;
import com.importio.model.Article;
import com.importio.model.Unit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCArticle {
    private Connection connection;
    private ArticleBuilder articleBuilder;

    public JDBCArticle(Connection connection) {
        this.connection = connection;
    }

    public void save(List<Article> listArticle) {
        try(Statement statement = connection.createStatement()) {
            for (Article ar:listArticle) {
                String getSelect = "SELECT 1 FROM article WHERE Title = '" + ar.getTitle() + "'";
                ResultSet result = statement.executeQuery(getSelect);
                if (result.next()){
                    String getUpdate= "UPDATE article set Description = '" +
                            ar.getDesription() + "', UnitOfMeasurement = '" +
                            ar.getUnitOfMeasurement() + "', Weight = " +
                            ar.getWeight() + " WHERE Title = '" + ar.getTitle() + "'";
                    statement.executeUpdate(getUpdate);
                }else {
                    String getInsert= "INSERT INTO article VALUES('" +
                            ar.getTitle() + "','" +
                            ar.getDesription() + "','" +
                            ar.getUnitOfMeasurement() + "'," +
                            ar.getWeight() + ")";
                    statement.executeUpdate(getInsert);
                }
            }
            System.out.println("Operation save ARTICLE. Ok");
        }catch (SQLException e){
            System.out.println("Operation save ARTICLE. SQLException");
        }
    }

    public List<Article> getAll(){
        List<Article> list = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {

            ArticleBuilder articleBuilder = new ArticleBuilder();
            String getSelect = "select Title,Description,UnitOfMeasurement,Weight from article";
            ResultSet result = statement.executeQuery(getSelect);
            while (result.next()) {

                String title = "";
                String desription = "";
                Unit unitOfMeasurement = null;
                Double weight = 0D;

                title = result.getString(1);
                desription = result.getString(2);
                try{
                    unitOfMeasurement = Unit.valueOf(result.getString(3));
                }catch (Exception e){
                    System.out.println("SAXArticle.characters Exception");
                }
                weight = result.getDouble(4);

                articleBuilder
                        .withTitle(title)
                        .withDesription(desription)
                        .withUnitOfMeasurement(unitOfMeasurement)
                        .withWight(weight);

                list.add(articleBuilder.toArticle());
            }

            System.out.println("Operation getAll ARTICLE. Ok");
            } catch (SQLException e1) {
            System.out.println("Operation getAll ARTICLE. SQLException");
        }

        return list;
    }
}
