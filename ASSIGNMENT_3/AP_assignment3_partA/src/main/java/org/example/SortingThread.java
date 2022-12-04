package org.example;

import java.util.ArrayList;

public class SortingThread extends Thread{
    private ArrayList<Double> list;
    private int index;
    private ArrayList<Boolean> sorted;
    private int end;
    public SortingThread(ArrayList<Double> List, int index,ArrayList<Boolean> sorted,int end){
        this.list=List;
        this.index=index;
        this.sorted=sorted;
        this.end=end;
    }
    private void swap(){
        double temp_var;
        int i=index;
        while(i<(list.size()-1) && i<=end){
            if (list.get(i)<list.get(i+1)) {
                temp_var=list.get(i);
                list.set(i,list.get(i+1));
                list.set(i+1,temp_var);
                sorted.set(0,false);
            }
            i+=2;
        }
    }
    @Override
    public void run(){
        swap();
//        for(int i=index;i<=size-2;i+=2){
//            swap(i);
//        }
    }
}
