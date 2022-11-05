package com.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Category {
    private String category_name;
    private int category_id;
    private ArrayList<Float> products;
    public Category(String category_name,int category_id){
        this.category_id=category_id;
        this.category_name=category_name;
        this.products=new ArrayList<>();
    }
    public void addProduct(float prod_id){
        this.products.add(prod_id);
    }

    public void Delete_all_prods(){
        this.products.clear();
    }

    public ArrayList<Float> get_product_ids(){
        return this.products;
    }

    public String getCategory_name() {
        return this.category_name;
    }

    public int getCategory_id() {
        return this.category_id;
    }
    public void delProduct(float product_id){
        this.products.remove(product_id);
    }
    public int getProductslen(){
        return this.products.size();
    }
    public boolean HasProd(float prod_id){
        return this.products.contains(prod_id);
    }
}
