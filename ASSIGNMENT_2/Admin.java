package com.example;

public class Admin extends User {
    private String username;
    private String password;
    public Admin(String username,String password){
        super(username,password);
    }


    public void AddCategory(String category_name, int category_id){
        Category category=new Category(category_name,category_id);
        FLIPZON.AddCategory(category_id,category);
    }
    public void AddProduct(int category_id,float product_id, float price, String product_details, int product_quantity){
        Product p=new Product(product_details,product_id,price,product_quantity);
        FLIPZON.AddProduct(category_id,product_id, p);
    }

    public void DeleteCategory(int cat_id){
        FLIPZON.DelCategory(cat_id);
    }
    public void DeleteProduct(int category_id, float product_id){
        FLIPZON.DelProd(category_id,product_id, this);
    }


    public void SetDiscounts(Product product, float elite_discount, float prime_discount, float normal_discount){
        product.setElite_discount(elite_discount);
        product.setPrime_discount(prime_discount);
        product.setNormal_discount(normal_discount);
    }

    public void SetCombinedDealPrices(Product p1, Product p2, Deal d){
        // prices in the order-Elite,Prime,Normal
        p1.addDeal(d);
        p2.addDeal(d);
    }
}
