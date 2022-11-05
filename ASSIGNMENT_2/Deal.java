package com.example;

import java.util.ArrayList;

public class Deal {
    private Product p1;
    private Product p2;
    private ArrayList<Float> prices;    // prices in the order-Elite,Prime,Normal
    public Deal(Product p1, Product p2,ArrayList<Float> prices){
        this.p1=p1;
        this.p2=p2;
        this.prices=prices;
    }
    public String toString(){
        return "Product id 1: "+this.p1.getProduct_id()+"\n"+
                "Product id 2: "+this.p2.getProduct_id()+"\n"+
                "Combined prices for: \n"+
                "Elite customers = ₹"+this.prices.get(0)+"\n"+
                "Prime customers = ₹"+this.prices.get(1)+"\n"+
                "Normal customers = ₹"+this.prices.get(2);
    }
    public String get_elite_deal(){
        return "Product id 1: "+this.p1.getProduct_id()+"\n"+
                "Product id 2: "+this.p2.getProduct_id()+"\n"+
                "Combined price for "+
                "Elite customers = ₹"+this.prices.get(0);
    }
    public String get_prime_deal(){
        return "Product id 1: "+this.p1.getProduct_id()+"\n"+
                "Product id 2: "+this.p2.getProduct_id()+"\n"+
                "Combined price for "+
                "Prime customers = ₹"+this.prices.get(1);
    }
    public String get_normal_deal(){
        return "Product id 1: "+this.p1.getProduct_id()+"\n"+
                "Product id 2: "+this.p2.getProduct_id()+"\n"+
                "Combined prices for "+
                "Normal customers = ₹"+this.prices.get(2);
    }

    public float get_elite_price(){
        return this.prices.get(0);
    }
    public float get_prime_price(){
        return this.prices.get(1);
    }
    public float get_normal_price(){
        return this.prices.get(2);
    }

    public void reduce_product_quantities(){
        this.p1.reduce_product_quantity(1);
        this.p2.reduce_product_quantity(1);
    }
    public boolean contains(Product p){
        return (this.p1.equals(p) || this.p2.equals(p));
    }
    public boolean check_product_quantities(Customer customer){
        return( (this.p1.getProduct_quantity()-customer.get_cart_quantity(this.p1)-customer.get_deal_quantity(this.p1))>=1 && (this.p2.getProduct_quantity()-customer.get_cart_quantity(this.p2)-customer.get_deal_quantity(this.p2))>=1);
    }
}
