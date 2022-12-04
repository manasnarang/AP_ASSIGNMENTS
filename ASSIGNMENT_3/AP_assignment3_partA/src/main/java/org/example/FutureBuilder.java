package org.example;

import java.util.ArrayList;
import java.util.Random;

public class FutureBuilder {
    private static ArrayList<Double> CGPAlist;
    private static Random random;
    private static int size;
    public FutureBuilder(){
        random=new Random();
    }
    public static ArrayList<Double> generateArrayList(int n){
        size=n;
        CGPAlist=new ArrayList<>(size);
        for(int i=0;i<size;i++){
            CGPAlist.add((double)Math.round((random.nextDouble(10.001000)*1000))/1000.0);
        }
        return CGPAlist;
    }
    //Odd even sort without threads
    public static ArrayList<Double> odd_even_sort(ArrayList<Double> list){
        CGPAlist=(ArrayList<Double>) list.clone();
        boolean sorted=false;
        double temp_var=0;
        while(!sorted){
            sorted=true;
            // Odd Phase
            for(int i=1; i<=size-2; i+=2){
                if(CGPAlist.get(i)<CGPAlist.get(i+1)){
                    temp_var=CGPAlist.get(i);
                    CGPAlist.set(i,CGPAlist.get(i+1));
                    CGPAlist.set(i+1,temp_var);
                    sorted=false;
                }
            }
            // Even Phase
            for(int i=0; i<=size-2; i+=2){
                if(CGPAlist.get(i)<CGPAlist.get(i+1)){
                    temp_var=CGPAlist.get(i);
                    CGPAlist.set(i,CGPAlist.get(i+1));
                    CGPAlist.set(i+1,temp_var);
                    sorted=false;
                }
            }
        }
        return CGPAlist;
    }

    public static ArrayList<Double> Odd_even_sort_with_threads(ArrayList<Double> list) throws InterruptedException{
        CGPAlist=(ArrayList<Double>) list.clone();
        size=list.size();
        ArrayList<Boolean> sorted=new ArrayList<>(1);
        sorted.add(false);
        double temp_var=0;
        while(!sorted.get(0)){
            sorted.set(0,true);
            // Odd Phase
            Thread thread1=new SortingThread(CGPAlist,1,sorted,size%4==0?(size/2):((size/2)-1));
            Thread thread2=new SortingThread(CGPAlist,size%4!=0?(size/2):((size/2)+1),sorted,size-2);
            thread1.start();
            thread2.start();
            thread2.join();
            thread1.join();
//            for(int i=1; i<=size-2; i+=4){
//                Thread thread=new SortingThread(CGPAlist,i,sorted);
//                thread.start();
//                thread.join();
//            }
            // Even Phase
            Thread thread3=new SortingThread(CGPAlist,0,sorted,size%4==0?(size/2):((size/2)-1));
            Thread thread4=new SortingThread(CGPAlist,size%4==0?((size/2)+2):((size/2)+1),sorted,size-2);
            thread3.start();
            thread4.start();
            thread3.join();
            thread4.join();
//            for(int i=0; i<=size-2; i+=4){
//                Thread thread=new SortingThread(CGPAlist,i,sorted);
//                thread.start();
//                thread.join();
//            }
        }
        return CGPAlist;
    }
}
