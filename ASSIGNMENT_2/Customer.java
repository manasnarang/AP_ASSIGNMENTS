package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Customer extends User {
    private String status;
    private ArrayList<Product> cart;
    private ArrayList<Deal> deals_cart;
    private ArrayList<Integer> product_quantities;
    private float account_balance;
    private ArrayList<Integer> coupons;
    public Customer(String username, String password){
        super(username,password);
        this.status="NORMAL";
        this.account_balance=1000;
        this.cart=new ArrayList<>();
        this.product_quantities=new ArrayList<>();
        this.deals_cart=new ArrayList<>();
        this.coupons=new ArrayList<>();
    }


    public String getStatus() {
        return this.status;
    }

    public int get_cart_quantity(Product p){
        if(this.cart.contains(p)) {
            return this.product_quantities.get(this.cart.indexOf(p));
        }
        else {
            return 0;
        }
    }
    public int get_deal_quantity(Product p){
        int deal_quantity=0;
        for(Deal d:this.deals_cart){
            if(d.contains(p)){
                deal_quantity++;
            }
        }
        return deal_quantity;
    }
    public void Add_to_Cart(Product p,int quantity){
        this.cart.add(p);
        this.product_quantities.add(quantity);
    }
    public void add_deal_to_cart(Deal d){
        this.deals_cart.add(d);
    }
    public void displayCoupons(){
        if(coupons.size()==0){
            System.out.println("Dear customer, you do not have any coupons available right now");
        }
        else {
            System.out.println("The following coupons are available: ");
            for(int i=0;i<this.coupons.size();i++){
                System.out.println((i+1)+this.coupons.get(i)+"% discount");
            }
        }
    }
    public float getAccount_balance(){
        return this.account_balance;
    }

    public void print_cart(){
        if(cart.size()==0 && deals_cart.size()==0){
            System.out.println("Cart is empty! ");
        }
        else {
            if(cart.size()>0){
                System.out.println("The products in the cart are: ");
                for(int i=0;i<this.cart.size();i++){
                    System.out.println((i+1)+". Product id = "+this.cart.get(i).getProduct_id()+"\n"+
                            this.cart.get(i).getProduct_details() +
                            "Product price = ₹"+this.cart.get(i).getPrice()+"\n"+
                            "Product quantity = "+this.product_quantities.get(i));
                }
            }
            if (deals_cart.size()>0){
                System.out.println("The deals in the cart are: ");
                if (this.status.equals("Elite")) {
                    for (int i = 0; i < this.deals_cart.size(); i++) {
                        System.out.println((i + 1) + ". " + this.deals_cart.get(i).get_elite_deal());
                    }
                }
                else if(this.status.equals("Prime")){
                    for (int i = 0; i < this.deals_cart.size(); i++) {
                        System.out.println((i + 1) + ". " + this.deals_cart.get(i).get_prime_deal());
                    }
                }
                else{
                    for (int i = 0; i < this.deals_cart.size(); i++) {
                        System.out.println((i + 1) + ". " + this.deals_cart.get(i).get_normal_deal());
                    }
                }
            }
        }
    }
    public void empty_carts(){
        this.deals_cart.clear();
        this.cart.clear();
        this.product_quantities.clear();
    }

    public boolean check_if_cart_is_empty(){
        return (this.cart.size()==0 && this.deals_cart.size()==0);
    }

    public ArrayList<Float> get_total_cost(){
        float cost=0;
        int coupon_discount=this.coupons.size()==0?0:Collections.max(this.coupons);
        float discount_to_compare;
        float delivery_charges=100;
        int delivery_percent;
        float original_cost=0;
        float prod_price;
        int prod_quantity;
        float max_discount=0;
        Product p;
        ArrayList<Float> cost_and_delivery=new ArrayList<>();   //[total cost, delivery charges, max_discount]
        if (this.status.equals("ELITE")) {
            discount_to_compare=Math.max(10,coupon_discount);
            delivery_percent=0;
            for (Deal d : this.deals_cart) {
                cost+=d.get_elite_price();
                original_cost+=d.get_elite_price(); // MIGHT CHANGE TO INDIVIDUAL PRODUCT COSTS
            }
            for(int i=0;i<this.cart.size();i++){
                p=this.cart.get(i);
                prod_quantity=this.product_quantities.get(i);
                prod_price=prod_quantity*(p.getPrice()*(100-Math.max(discount_to_compare,p.getElite_discount())))/100;
                cost+=prod_price;
                original_cost+=p.getPrice()*prod_quantity;

                if(discount_to_compare!=10 && Math.max(discount_to_compare,p.getElite_discount())==coupon_discount && Math.max(discount_to_compare,p.getElite_discount())!=p.getElite_discount()){
                    max_discount=coupon_discount;       //check if coupon is being used
                }
            }
        }
        else if(this.status.equals("PRIME")){
            discount_to_compare=Math.max(5,coupon_discount);
            delivery_percent=2;
            for (Deal d : this.deals_cart) {
                cost+=d.get_prime_price();
                original_cost+=d.get_prime_price(); // MIGHT CHANGE TO INDIVIDUAL PRODUCT COSTS
            }
            for(int i=0;i<this.cart.size();i++){
                p=this.cart.get(i);
                prod_quantity=this.product_quantities.get(i);
                prod_price=prod_quantity*(p.getPrice()*(100-Math.max(discount_to_compare,p.getPrime_discount())))/100;
                cost+=prod_price;
                original_cost+=p.getPrice()*prod_quantity;

                if(discount_to_compare!=5 && Math.max(discount_to_compare,p.getPrime_discount())==coupon_discount && Math.max(discount_to_compare,p.getPrime_discount())!=p.getPrime_discount()){
                    max_discount=coupon_discount;
                }
            }
            delivery_charges+=original_cost*(delivery_percent)/100;

        }
        else{
            discount_to_compare=coupon_discount;
            delivery_percent=5;
            for (Deal d : this.deals_cart) {
                cost+=d.get_normal_price();
                original_cost+=d.get_normal_price(); // MIGHT CHANGE TO INDIVIDUAL PRODUCT COSTS
            }
            for(int i=0;i<this.cart.size();i++){
                p=this.cart.get(i);
                prod_quantity=this.product_quantities.get(i);
                prod_price=prod_quantity*(p.getPrice()*(100-Math.max(discount_to_compare,p.getNormal_discount())))/100;
                cost+=prod_price;
                original_cost+=p.getPrice()*prod_quantity;
            }
            delivery_charges+=original_cost*(delivery_percent)/100;
        }
        cost=cost+delivery_charges;
        cost_and_delivery.add(cost);
        cost_and_delivery.add(delivery_charges);
        cost_and_delivery.add(max_discount);
        return cost_and_delivery;       //[total cost, delivery charges, max_discount]
    }   //MIGHT CHANGE TO ALSO RETURN ORIGINAL COST

    private void GenerateCoupons(){
        Random rand=new Random();
        int number_of_coupons;
        int disc;
        if(this.status.equals("NORMAL")){
            return;
        }
        else if(this.status.equals("ELITE")){
            number_of_coupons=3+rand.nextInt(2);
        }
        else{
            number_of_coupons=1+rand.nextInt(2);
        }
        System.out.println("Congratulations! You have won "+number_of_coupons+" discount coupons!");
        System.out.println("Values of the coupons are:");
        for(int i=0;i<number_of_coupons;i++){
            disc=5+rand.nextInt(11);
            this.coupons.add(disc);
            System.out.print(disc+"%  ");
        }
        System.out.print("\n");
    }

    private void update_product_quantities(){
        Product p;
        int q;
        for(int i=0;i<this.cart.size();i++){
            p=this.cart.get(i);
            q=this.product_quantities.get(i);
            p.reduce_product_quantity(q);
        }
        for (Deal d:this.deals_cart){
            d.reduce_product_quantities();
        }
    }

    public boolean check_product_quantities(){
        int flag=0;
        for(int i=0;i<this.cart.size();i++){
            if((get_deal_quantity(this.cart.get(i))+this.product_quantities.get(i))<this.cart.get(i).getProduct_quantity()){
                flag=1;
//                this.cart.remove(i);
            }
        }
        return flag==0;     //true if products are in stock
    }
    public void print_order_details(){
        ArrayList<Float> charges=this.get_total_cost();     //[total cost, delivery charges, max_discount]
        if(this.coupons.contains(Math.round(charges.get(2)))){
            this.coupons.remove(Math.round(charges.get(2)));        //remove coupon after use
        }
        this.update_product_quantities();
        System.out.println("\n\nDelivery charges = ₹ "+charges.get(1));
        System.out.println("Total cost = ₹ "+charges.get(0));
        this.account_balance-=charges.get(0);
        Random r=new Random();
        int delivery_days;
        if(this.status.equals("ELITE")){
            System.out.println("Your order will be delivered within 2 days\n");
            if(r.nextInt(2)==1){
                System.out.println("Congratulations! You have won a surprise gift from FLIPZON!!");
            }
        }
        else if(this.status.equals("PRIME")){
            delivery_days=r.nextInt(4)+3;
            System.out.println("Your order will be delivered within "+delivery_days+ " days\n");
        }
        else {
            delivery_days=r.nextInt(4)+7;
            System.out.println("Your order will be delivered within "+delivery_days+" days\n");
        }
        if(charges.get(0)>5000){
            this.GenerateCoupons();
        }
    }
    public void deduct_money(int amount){
        this.account_balance-=amount;
    }

    public void add_money(float amount){
        this.account_balance+=amount;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
