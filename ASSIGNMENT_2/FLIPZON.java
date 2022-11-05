package com.example;

import java.lang.reflect.Array;
import java.util.*;

public class FLIPZON implements Application{
    private static HashMap<String,Admin> admins;        // hashmap with username,Admin object
    private static HashMap<Integer,Category> categories;        //Hashmap with category id, category object
    private static HashMap<Float,Product> products;         //Hashmap with product id, Product object
    private static ArrayList<Product> product_catalog;      //Arraylist with all product objects
    private static ArrayList<Deal> deals;           //Arraylist with all deal objects
    private static HashMap<String,Customer> customers;      //Hashmap with username, customer object
    public FLIPZON(){
        categories=new HashMap<>();
        admins=new HashMap<>();
        products=new HashMap<>();
        deals=new ArrayList<>();
        product_catalog=new ArrayList<>();
        customers=new HashMap<>();
        //Hard coded admin usernames and passwords
        Admin beff_jezos=new Admin("Beff Jezos","password1");
        Admin gill_bates=new Admin("Gill Bates","password2");
        admins.put("Beff Jezos",beff_jezos);
        admins.put("Gill Bates",gill_bates);
    }
    public static void main_menu(){
        System.out.println("1) Enter as Admin\n" +
                "2) Explore the Product Catalog\n" +
                "3) Show Available Deals\n" +
                "4) Enter as Customer\n" +
                "5) Exit the Application");
    }
    public static void invalid_choice_msg(){
        System.out.println("Invalid input! Enter your choice again!");
    }
    public static void StartApp(){          //main menu
        System.out.println("WELCOME TO FLIPZON\n");
        Scanner sc=new Scanner(System.in);
        int main_menu_input;
        int admin_menu_input;
        int customer_menu_input;
        int inner_customer_menu_input;
        String username;
        String password;
        String dummy_var;       //Dummy variable that scans next line after every int or float input to make sure
                                    // that scanner moves to next line
        Admin admin;
        Customer customer;
        while (true){
            main_menu();
            main_menu_input= sc.nextInt();
            dummy_var=sc.nextLine();
            if (main_menu_input==1){        //enter as admin
                System.out.println("Dear Admin,\n" +
                        "Please enter your username and password");
                username=sc.nextLine();
                password=sc.nextLine();
                if(admins.containsKey(username)){
                    if(validate_admin(username,password)){
                        admin=admins.get(username);
                    }
                    else {
                        System.out.println("Wrong password! Unable to login as admin, redirecting to main menu...");
                        continue;
                    }
                }
                else {
                    System.out.println("Admin not found! Please enter correct details!");
                    continue;
                }
                System.out.println("Welcome "+admin.getUsername()+"!!!!!"+"\n");
                while (true) {
                    print_admin_menu();
                    admin_menu_input = sc.nextInt();
                    dummy_var = sc.nextLine();
                    if (admin_menu_input == 1) {        //add category
                        add_new_category(admin);

                    } else if (admin_menu_input == 2) {       //delete category
                        delete_category(admin);
                    } else if (admin_menu_input == 3) {       //add product
                        add_new_product(admin);
                    } else if (admin_menu_input == 4) {       //delete product
                        delete_product(admin);
                    } else if (admin_menu_input == 5) {       //set discount on product
                        set_discount_on_product(admin);
                    } else if (admin_menu_input == 6) {       //add giveaway deal
                        add_giveaway_deal(admin);
                    } else if (admin_menu_input == 7) {       //Back
                        break;
                    } else {
                        invalid_choice_msg();
                    }
                }
            }
            else if(main_menu_input==2){        //Explore product catalog
                print_product_catalog();
            }
            else if(main_menu_input==3){        //Show available deals
                print_available_deals();
            }
            else if(main_menu_input==4){        //Enter as customer
                while (true) {
                    print_customer_outer_menu();
                    customer_menu_input = sc.nextInt();
                    dummy_var = sc.nextLine();
                    if (customer_menu_input == 1) {     //Sign Up
                        sign_up_customer();
                    } else if (customer_menu_input == 2) {      //Log in
                        System.out.println("Enter name");
                        username = sc.nextLine();
                        System.out.println("Enter password");
                        password = sc.nextLine();
                        if (customers.containsKey(username)){
                            if(validate_customer(username,password)){
                                customer=customers.get(username);
                            }
                            else {
                                System.out.println("Wrong password! Unable to login as customer, redirecting to main menu...");
                                continue;
                            }
                        }
                        else {
                            System.out.println("Customer not found! Please enter correct details or sign up!");
                            continue;
                        }
                        System.out.println("Welcome "+customer.getUsername()+"!!");
                        while (true) {
                            print_customer_inner_menu();
                            inner_customer_menu_input = sc.nextInt();
                            dummy_var = sc.nextLine();
                            if (inner_customer_menu_input == 1) {       //Browse Products
                                print_product_catalog();
                            } else if (inner_customer_menu_input == 2) {    //Browse Deals
                                print_deals(customer);
                            } else if (inner_customer_menu_input == 3) {    //Add product to cart
                                add_product_to_cart(customer);
                            }
                            else if(inner_customer_menu_input==4){      //Add products in deal to cart
                                add_products_in_deal_to_cart(customer);
                            }
                            else if(inner_customer_menu_input==5){      //View coupons
                                show_coupons(customer);
                            }
                            else if(inner_customer_menu_input == 6){    //Check account balance
                                show_account_balance(customer);
                            }
                            else if(inner_customer_menu_input==7){      //View Cart
                                show_cart(customer);
                            }
                            else if(inner_customer_menu_input==8){      //Empty cart
                                empty_cart(customer);
                            }
                            else if(inner_customer_menu_input==9){      //Checkout cart
                                proceed_to_checkout(customer);
                            }
                            else if(inner_customer_menu_input==10){     //Upgrade customer status
                                upgrade_status(customer);
                            }
                            else if(inner_customer_menu_input==11){     //Add amount to wallet
                                add_money_to_wallet(customer);
                            }
                            else if(inner_customer_menu_input==12){     //Back
                                System.out.println("Bye "+customer.getUsername()+"! Thank you for using FLIPZON!");
                                break;
                            }
                            else {
                                invalid_choice_msg();
                            }
                        }
                    }
                    else if(customer_menu_input==3){        //Back
                        break;
                    }
                    else {
                        invalid_choice_msg();
                    }
                }
            }
            else if(main_menu_input==5){        //Exit the app
                System.out.println("Thank you for using FLIPZON!");
                break;
            }
            else {
                invalid_choice_msg();
            }
        }

    }
    public static void AddCategory(int cat_id,Category c){
        categories.put(cat_id,c);
    }
    public static void product_delete(float product_id){
        System.out.println(products.containsKey(product_id));
        delete_deals(products.get(product_id));
        product_catalog.remove(products.get(product_id));
        products.remove(product_id);
    }
    public static void DelProd(int category_id, float product_id,Admin admin){
        Scanner s=new Scanner(System.in);
        int choice;
        String dummy;
        Category c=categories.get(category_id);
        delete_deals(products.get(product_id));
        c.delProduct(product_id);
        product_catalog.remove(products.get(product_id));
        products.remove(product_id);
        int len=c.getProductslen();
        System.out.println("Product deleted successfully!");
        if(len==0){
            while (true) {
                System.out.println("Category is now empty! Please choose one fo the following options:\n" +
                        "1. Add new product\n" +
                        "2. Delete category");
                choice = s.nextInt();
                dummy = s.nextLine();
                if(choice==1){
                    add_new_product(admin);
                    break;
                }
                else if(choice==2){
                    categories.remove(category_id);
                    System.out.println("Category deleted successfully!");
                    break;
                }
                else {
                    invalid_choice_msg();
                }
            }
        }
    }
    public static void DelCategory(int category_id){
        ArrayList<Float> category_prods=new ArrayList<>();
        category_prods=categories.get(category_id).get_product_ids();
        Product p;
        for(int i=0;i<category_prods.size();i++){
            p=products.get(category_prods.get(i));
            delete_deals(p);
            product_catalog.remove(p);
            products.remove(category_prods.get(i),p);
        }
        categories.get(category_id).Delete_all_prods();
        categories.remove(category_id);
    }
    public static void delete_deals(Product p){
        ArrayList<Deal> prod_deals;
        prod_deals=p.getProduct_deals();
        for(Deal d:prod_deals){
            deals.remove(d);
        }
    }
    public static void AddProduct(int category_id, float product_id, Product p){
        categories.get(category_id).addProduct(product_id);
        products.put(product_id,p);
        product_catalog.add(p);
    }

    //Admin functions
    public static boolean validate_admin(String username, String password){
        return admins.get(username).getPassword().equals(password);
    }
    public static void print_admin_menu(){
        System.out.println("Please choose any one of the following actions:\n" +
                "1) Add category\n" +
                "2) Delete category\n" +
                "3) Add Product\n" +
                "4) Delete Product\n" +
                "5) Set Discount on Product\n" +
                "6) Add giveaway deal\n" +
                "7) Back");
    }

    public static void add_new_category(Admin admin){
        int cat_id;
        String cat_name;
        String dummy;
        float prod_id;
        float prod_price;
        StringBuilder prod_details= new StringBuilder();
        int prod_quantity;
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("Add category ID");
            cat_id=scanner.nextInt();
            dummy=scanner.nextLine();
            System.out.println("Add name of the category");
            cat_name=scanner.nextLine();
            if(categories.containsKey(cat_id)){
                System.out.println("Category with this category id already exists. Category id should be unique. Enter details again!");
            }
            else {
                break;
            }
        }
        admin.AddCategory(cat_name,cat_id);
        System.out.println("Category "+cat_name+" added successfully!");
        while (true) {
            System.out.println("Add a product under this category as category cannot be empty");
            System.out.println("Enter product id");
            prod_id = scanner.nextFloat();
            dummy = scanner.nextLine();
            System.out.println("Enter price of the product(in ₹)");
            prod_price = scanner.nextFloat();
            dummy = scanner.nextLine();
            System.out.println("Enter product quantity");
            prod_quantity=scanner.nextInt();
            dummy=scanner.nextLine();
            System.out.println("Enter product name and other details");
            dummy= scanner.nextLine();
            do {
                prod_details.append(dummy);
                prod_details.append("\n");
                dummy=scanner.nextLine();
            } while (!dummy.isEmpty());
            if(categories.get(cat_id).HasProd(prod_id)){
                System.out.println("Product with this product id already exists under this category. Product id should be unique, enter details again!");
            }
            else if(products.containsKey(prod_id)){
                System.out.println("Product with this product id already exists! Product id should be unique, enter details again!");
            }
            else {
                break;
            }
        }
        admin.AddProduct(cat_id,prod_id,prod_price, prod_details.toString(),prod_quantity);
        System.out.println("Product added successfully!");
    }
    public static void delete_category(Admin admin){
        Scanner scanner=new Scanner(System.in);
        int cat_id;
        String cat_name;
        String dummy;
        while (true){
            System.out.println("Add category ID");
            cat_id=scanner.nextInt();
            dummy=scanner.nextLine();
            System.out.println("Add name of the category");
            cat_name=scanner.nextLine();
            if(!categories.containsKey(cat_id)){
                System.out.println("Category with this category id does not exist. Enter correct details!");
            }
            else {
                break;
            }
        }
        admin.DeleteCategory(cat_id);
        System.out.println("Category "+cat_name+" deleted successfully");
    }

    public static void add_new_product(Admin admin){
        Scanner scanner=new Scanner(System.in);
        int cat_id;
        float prod_id;
        float prod_price;
        int prod_quantity;
        String dummy;
        StringBuilder prod_details= new StringBuilder();
        while (true){
            System.out.println("Enter category ID");
            cat_id=scanner.nextInt();
            dummy=scanner.nextLine();
            if(!categories.containsKey(cat_id)){
                System.out.println("Category with this category id does not exist. Enter correct details!");
            }
            else {
                break;
            }
        }
        while (true) {
            System.out.println("Enter product id");
            prod_id = scanner.nextFloat();
            dummy = scanner.nextLine();
            System.out.println("Enter price of the product(in ₹)");
            prod_price = scanner.nextFloat();
            dummy = scanner.nextLine();
            System.out.println("Enter product quantity");
            prod_quantity=scanner.nextInt();
            dummy=scanner.nextLine();
            System.out.println("Enter product name and other details");
            dummy=scanner.nextLine();
            do {
                prod_details.append(dummy).append("\n");
                dummy=scanner.nextLine();
            } while (!dummy.isEmpty());
            if(categories.get(cat_id).HasProd(prod_id)){
                System.out.println("Product with this product id already exists under this category. Product id should be unique, enter details again!");
            }
            else if(products.containsKey(prod_id)){
                System.out.println("Product with this product id already exists! Product id should be unique, enter details again!");
            }
            else {
                break;
            }
        }
        admin.AddProduct(cat_id,prod_id,prod_price, prod_details.toString(),prod_quantity);
        System.out.println("Product added successfully!");
    }

    public static void delete_product(Admin admin){
        int cat_id;
        float prod_id;
        String dummy;
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("Enter category ID");
            cat_id=scanner.nextInt();
            dummy=scanner.nextLine();
            if(!categories.containsKey(cat_id)){
                System.out.println("Category with this category id does not exist. Enter correct details!");
            }
            else {
                break;
            }
        }
        while (true){
            System.out.println("Enter product id");
            prod_id=scanner.nextFloat();
            dummy=scanner.nextLine();
            if(!categories.get(cat_id).HasProd(prod_id)){
                System.out.println("Product with product id "+prod_id+" does not exist in the category. Enter correct product id !");
            }
            else {
                break;
            }
        }
        admin.DeleteProduct(cat_id,prod_id);
    }

    public static void set_discount_on_product(Admin admin){
        Scanner scanner=new Scanner(System.in);
        float prod_id;
        String dummy;
        Product p;
        float elite_discount;
        float prime_discount;
        float normal_discount;
        System.out.println("Dear Admin give the Product ID you want to add discount for\n");
        while (true){
            System.out.println("Enter product id");
            prod_id=scanner.nextFloat();
            dummy=scanner.nextLine();
            if(!products.containsKey(prod_id)){
                System.out.println("Product with product id "+prod_id+" does not exist. Enter correct product id !");
            }
            else {
                p=products.get(prod_id);
                break;
            }
        }
        System.out.println("Enter discount for Elite, Prime and Normal customers respectively (in % terms)\n");
        elite_discount=scanner.nextFloat();
        prime_discount=scanner.nextFloat();
        normal_discount=scanner.nextFloat();
        dummy=scanner.nextLine();
        admin.SetDiscounts(p,elite_discount,prime_discount,normal_discount);
    }   //ADMIN CAN GIVE FLOAT VALUES AS DISCOUNTS BUT AUTO GENERATED COUPONS WILL BE INT

    public static void add_giveaway_deal(Admin admin){
        Scanner scanner=new Scanner(System.in);
        float prod_id_1;
        float prod_id_2;
        String dummy;
        float elite_price;
        float prime_price;
        float normal_price;
        ArrayList<Float> prices=new ArrayList<>(3);
        Product p1;
        Product p2;
        System.out.println("Dear Admin give the Product IDs you want to combine and giveaway a deal for\n");
        while (true){
            System.out.println("Enter the first Product ID :");
            prod_id_1=scanner.nextFloat();
            dummy=scanner.nextLine();
            System.out.println("Enter the second Product ID:");
            prod_id_2=scanner.nextFloat();
            dummy=scanner.nextLine();
            if(!(products.containsKey(prod_id_1) && products.containsKey(prod_id_2))){
                System.out.println("Please enter valid product ids!");
            }
            else {
                p1=products.get(prod_id_1);
                p2=products.get(prod_id_2);
                break;
            }
        }
        System.out.println("Enter the combined price in ₹ for Elite, Prime and Normal customers respectively(Should be less than their combined price):\n");
        elite_price=scanner.nextFloat();
        prime_price=scanner.nextFloat();
        normal_price=scanner.nextFloat();
        dummy=scanner.nextLine();
        prices.add(elite_price);
        prices.add(prime_price);
        prices.add(normal_price);
        Deal d=new Deal(p1,p2,prices); //prices in the order elite,prime,normal
        admin.SetCombinedDealPrices(p1,p2,d);
        deals.add(d);
    }

    // Function for browse products/ print product catalog
    public static void print_product_catalog(){
        if(product_catalog.size()==0){
            System.out.println("Dear User, there are no available products right now!");
        }
        else {
            System.out.println("The following products are available: ");
            for (int i = 0; i < product_catalog.size(); i++) {
                System.out.print((i + 1) + ". " + product_catalog.get(i));
            }
        }
    }

    // Function to show available deals
    public static void print_available_deals(){
        if(deals.size()==0){
            System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.");
        }
        else {
            System.out.println("The following deals are available:");
            for (int i = 0; i < deals.size(); i++) {
                System.out.println((i + 1) + ". " + deals.get(i));
            }
        }
    }


    // Customer functions
    public static void print_customer_outer_menu(){
        System.out.println("1) Sign up\n" +
                "2) Log in\n" +
                "3) Back");
    }

    public static void sign_up_customer(){
        Scanner scanner=new Scanner(System.in);
        String name;
        String password;
        Customer customer;
        while (true){
            System.out.println("Enter name");
            name=scanner.nextLine();
            System.out.println("Enter password");
            password=scanner.nextLine();
            if(customers.containsKey(name)){
                System.out.println("Customer with name "+name+" already exists. Enter details again! ");
            }
            else {
                break;
            }
        }
        customer=new Customer(name,password);
        customers.put(name,customer);
        System.out.println("Customer registered successfully!!!");
    }

    public static boolean validate_customer(String name, String password){
        return customers.get(name).getPassword().equals(password);
    }

    public static void print_customer_inner_menu(){
        System.out.println("1) browse products\n" +
                "2) browse deals\n" +
                "3) add a product to cart\n" +
                "4) add products in deal to cart\n" +
                "5) view coupons\n" +
                "6) check account balance\n" +
                "7) view cart\n" +
                "8) empty cart\n" +
                "9) checkout cart\n" +
                "10) upgrade customer status\n" +
                "11) Add amount to wallet\n" +
                "12) back");
    }

    public static void print_deals(Customer customer){
        if(deals.size()==0){
            System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.");
        }
        else {
            String status = customer.getStatus();
            System.out.println("The following deals are available:");
            if (status.equals("ELITE")) {
                for (int i = 0; i < deals.size(); i++) {
                    System.out.println((i + 1) + ". " + deals.get(i).get_elite_deal());
                }
            } else if (status.equals("PRIME")) {
                System.out.println("The following deals are available:");
                for (int i = 0; i < deals.size(); i++) {
                    System.out.println((i + 1) + ". " + deals.get(i).get_prime_deal());
                }
            } else {
                System.out.println("The following deals are available:");
                for (int i = 0; i < deals.size(); i++) {
                    System.out.println((i + 1) + ". " + deals.get(i).get_normal_deal());
                }
            }
        }

    }

    public static void add_product_to_cart(Customer customer){
        float prod_id;
        int quantity;
        String dummy;
        Scanner scanner=new Scanner(System.in);
        Product p;
        while (true) {
            System.out.println("Enter product ID and quantity");
            prod_id = scanner.nextFloat();
            quantity = scanner.nextInt();
            dummy = scanner.nextLine();
            if(!products.containsKey(prod_id)){
                System.out.println("Incorrect product id! Product does not exist! Enter details again!");
            }
            else {
                p=products.get(prod_id);
                if((p.getProduct_quantity()-customer.get_cart_quantity(p)-customer.get_deal_quantity(p))<quantity){
                    System.out.println("Only "+(p.getProduct_quantity()-customer.get_cart_quantity(p)-customer.get_deal_quantity(p))+" units of this product are available. Enter quantity accordingly!");
                }
                else {
                    break;
                }
            }
        }
        customer.Add_to_Cart(p,quantity);
        System.out.println("Item successfully added to cart");
    }

    public static void add_products_in_deal_to_cart(Customer customer){
        if(deals.size()==0){
            System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.");
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int deal_choice;
            String dummy;
            Deal d;
            print_deals(customer);
            while (true) {
                System.out.println("\nEnter number corresponding to the deal you want");
                deal_choice = scanner.nextInt();
                dummy = scanner.nextLine();
                if ((deal_choice - 1) >= deals.size()) {
                    System.out.println("Please enter valid number!");
                } else {
                    d = deals.get(deal_choice - 1);
                    if(d.check_product_quantities(customer)){
                        break;
                    }
                    else {
                        System.out.println("Cannot add deal! Products out of stock!");
                    }
                }
            }
            customer.add_deal_to_cart(d);
            System.out.println("Deal added to cart successfully!");
        }
    }

    public static void show_coupons(Customer customer){
        customer.displayCoupons();
    }

    public static void show_account_balance(Customer customer){
        System.out.println("Current account balance is ₹ "+customer.getAccount_balance());
    }

    public static void show_cart(Customer customer){
        customer.print_cart();
    }

    public static void empty_cart(Customer customer){
        customer.empty_carts();
        System.out.println("Cart successfully emptied");
    }

    public static void proceed_to_checkout(Customer customer){
        ArrayList<Float> costs=customer.get_total_cost();
        if(customer.check_if_cart_is_empty()){
            System.out.println("Cart is empty! Cannot place order!");
        }
        else if(costs.get(0)>customer.getAccount_balance()){
            System.out.println("Insufficient balance!! Please try again");
        }
        else if(!customer.check_product_quantities()){
            System.out.println("Sorry, cannot place order! Products are out of stock!");
        }
        else{
            System.out.println("Your order is placed successfully. Details:");
            show_cart(customer);
            customer.print_order_details();
            customer.empty_carts();
        }
    }

    public static void upgrade_status(Customer customer){
        String current_status=customer.getStatus();
        System.out.println("Current status: "+current_status);
        Scanner scanner=new Scanner(System.in);
        String new_status;
        if(current_status.equals("ELITE")){
            System.out.println("Cannot upgrade further!");
        }
        else if(current_status.equals("PRIME")){
            while (true){
                System.out.print("Choose new status: ");
                new_status=scanner.nextLine();
                if(new_status.equals("ELITE")){
                    if(customer.getAccount_balance()>100){
                        customer.deduct_money(100);
                        customer.setStatus("ELITE");
                        System.out.println("Status updated to ELITE");
                    }
                    else{
                        System.out.println("Insufficient balance! Cannot upgrade to ELITE!");
                    }
                    break;
                }
                else{
                    System.out.println("Invalid input! You can only upgrade to ELITE!");
                }
            }
        }
        else{
            while (true){
                System.out.print("Choose new status: ");
                new_status=scanner.nextLine();
                if(new_status.equals("NORMAL")){
                    System.out.println("Invalid input! You can only upgrade to ELITE or PRIME!");
                }
                else if(new_status.equals("ELITE")){
                        if(customer.getAccount_balance()>300){
                            customer.deduct_money(300);
                            customer.setStatus("ELITE");
                            System.out.println("Status updated to ELITE");
                        }
                        else{
                            System.out.println("Insufficient balance! Cannot upgrade to ELITE!");
                        }
                    break;
                }
                else {
                    if(customer.getAccount_balance()>200){
                        customer.deduct_money(200);
                        customer.setStatus("PRIME");
                        System.out.println("Status updated to PRIME");
                    }
                    else{
                        System.out.println("Insufficient balance! Cannot upgrade to PRIME!");
                    }
                    break;
                }
            }
        }
    }

    public static void add_money_to_wallet(Customer customer){
        Scanner scanner=new Scanner(System.in);
        float amount;
        String dummy;
        System.out.println("Enter amount to add");
        amount=scanner.nextFloat();
        dummy=scanner.nextLine();
        customer.add_money(amount);
        System.out.println("Amount successfully added");
    }
}
