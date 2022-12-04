package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class TreeThread extends Thread{
//    private TreeSet<Long> longTreeSet;

    private BalancedBinaryTree tree;
    private ArrayList<Long> elements;
    private int start;
    private int end;
    public TreeThread(BalancedBinaryTree tree, int start, int end, ArrayList<Long> elements){
        this.tree=tree;
        this.start=start;
        this.end=end;
        this.elements=elements;
    }
    @Override
    public void run() {
        for(int i=start;i<=end;i++){
            tree.add(elements.get(i));
        }
    }
}
