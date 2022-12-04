package org.example;

import java.util.ArrayList;
import java.util.Random;

public class BalancedBinaryTree{
    private Node root;
    public BalancedBinaryTree(){
    }
    public void add(long element){
        this.root=insert(this.root,element);
    }
    public int getHeight(){
        return height(root);
    }
    private Node left_rotate(Node node){
        Node a=node.getRight();
        Node b=a.getLeft();
        a.setLeft(node);
        node.setRight(b);
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight()))+1);
        a.setHeight(Math.max(height(a.getLeft()),height(a.getRight()))+1);
        return a;
    }
    private Node right_rotate(Node node){
        Node a=node.getLeft();
        Node b=a.getRight();
        a.setRight(node);
        node.setLeft(b);
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight()))+1);
        a.setHeight(Math.max(height(a.getLeft()),height(a.getRight()))+1);
        return a;
    }

    private int get_balance_val(Node node) {
        if(node==null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private Node insert(Node node, long value){
        if(node==null){
            return(new Node(value));
        }
        else if(value<=node.getValue()) {
            node.setLeft(insert(node.getLeft(),value));
        }
        else if(value>node.getValue()) {
            node.setRight(insert(node.getRight(),value));
        }

        node.setHeight(1+Math.max(height(node.getLeft()),height(node.getRight())));

        int balance=get_balance_val(node);

        //Left Left
        if(balance>1 && value<node.getLeft().getValue()){
            return right_rotate(node);
        }
        // Right Right
        else if(balance<-1 && value>node.getRight().getValue()){
            return left_rotate(node);
        }
        // Left Right
        else if(balance>1 && value>node.getLeft().getValue()){
            node.setLeft(left_rotate(node.getLeft()));
            return right_rotate(node);
        }
        // Right Left
        else if(balance<-1 && value<node.getRight().getValue()){
            node.setRight(right_rotate(node.getRight()));
            return left_rotate(node);
        }
        return node;
    }


//    public boolean check_balanced(Node node){
//        int left_height, right_height;
//        if(node==null){
//            return true;
//        }
//        left_height=height(node.getLeft());
//        right_height=height(node.getRight());
//
//        return Math.abs(left_height - right_height) <= 1 && check_balanced(node.getLeft()) && check_balanced(node.getRight());
//    }

    public int height(Node node){
        if(node==null){
            return 0;
        }
        return node.getHeight();
    }

    public boolean search_element(long value){
        return find_element(value,this.root);
    }
    private boolean find_element(long value, Node node){
        if(node==null){
            return false;
        }
        else if(node.getValue()==value){
            return true;
        }
        else if(value<node.getValue()){
            return find_element(value,node.getLeft());
        }
        else{
            return find_element(value,node.getRight());
        }
    }

    public long getRoot() {
        return this.root.getValue();
    }
}
