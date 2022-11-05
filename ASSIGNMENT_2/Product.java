package com.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private float product_id;
    private float price;
    private int product_quantity;
    private String product_details;
    private float elite_discount;
    private float prime_discount;
    private float normal_discount;
    private ArrayList<Deal> product_deals;       // prices in the order-Elite,Prime,Normal
    public Product(String prod_details, float product_id, float price, int product_quantity){
        this.product_details=prod_details;
        this.price=price;
        this.product_id=product_id;
        this.product_quantity=product_quantity;
        this.product_deals=new ArrayList<>();
    }

    public float getProduct_id() {
        return this.product_id;
    }

    public float getPrice() {
        return this.price;
    }

    public float getElite_discount() {
        return this.elite_discount;
    }

    public float getNormal_discount() {
        return this.normal_discount;
    }

    public float getPrime_discount() {
        return this.prime_discount;
    }

    public void setElite_discount(float elite_discount) {
        this.elite_discount = elite_discount;
    }

    public void setNormal_discount(float normal_discount) {
        this.normal_discount = normal_discount;
    }

    public void setPrime_discount(float prime_discount) {
        this.prime_discount = prime_discount;
    }
    public void addDeal(Deal d){
        this.product_deals.add(d);
    }

    public int getProduct_quantity() {
        return this.product_quantity;
    }

    public String getProduct_details() {
        return this.product_details;
    }

    public String toString(){
        return "Product id: "+this.getProduct_id()+"\n"+
                "Product price: ₹"+this.getPrice()+"\n"+
                "Product quantity: "+this.product_quantity+"\n"+
                this.product_details;
    }

    public void reduce_product_quantity(int q){
        this.product_quantity-=q;
    }


    public boolean equals(Product p){
        return this.product_id==p.getProduct_id();
    }
    public ArrayList<Deal> getProduct_deals(){
        return this.product_deals;
    }
}
