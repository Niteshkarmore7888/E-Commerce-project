package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty prize;

    public Product(int id, String name, double prize) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.prize = new SimpleDoubleProperty(prize);
    }
    public static ObservableList<Product> getALLProducts(){
        String selectALLProducts="SELECT id,name,prize FROM product";
        return fetchProductData(selectALLProducts);
    }
    public static ObservableList<Product> fetchProductData(String query){
        ObservableList<Product> data= FXCollections.observableArrayList();
        DbConnection dbConnection=new DbConnection();
        try{
            ResultSet rs=dbConnection.getQueryTable(query);
            while (rs.next()){
                Product product=new Product(rs.getInt("id"),rs.getString("name"),rs.getDouble("prize"));
                data.add(product);
            }
            return data;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public int getId() {
        return id.get();
    }


    public String getName() {
        return name.get();
    }


    public double getPrize() {
        return prize.get();
    }


}
