package org.example;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        FutureBuilder fb=new FutureBuilder();
        ArrayList<Double> CGPA;
        ArrayList<Double> sortedList;
        long start_time;
        long end_time;

        PrintWriter out=null;
        try {
            out=new PrintWriter(new FileWriter("partA_output.txt"));
            //For n=1
            out.println("For n=1");
            //Without threads
            out.println("Without threads");
            CGPA=FutureBuilder.generateArrayList(1);
            out.println("List before sorting is");
            out.println(CGPA);
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.odd_even_sort(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time without threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=1");

//        System.out.println(CGPA);
            //With threads
            out.println("\nWith threads");
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.Odd_even_sort_with_threads(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time with threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=1");

            //For n=10
            out.println("\nFor n=10");
            //Without threads
            out.println("Without threads");
            CGPA=FutureBuilder.generateArrayList(10);
            out.println("List before sorting is");
            out.println(CGPA);
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.odd_even_sort(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time without threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=10");

//        System.out.println(CGPA);
            //With threads
            out.println("\nWith threads");
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.Odd_even_sort_with_threads(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time with threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=10");



            //For n=100
            out.println("\nFor n=100");
            //Without threads
            out.println("Without threads");
            CGPA=FutureBuilder.generateArrayList(100);
            out.println("List before sorting is");
            out.println(CGPA);
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.odd_even_sort(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time without threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=100");

//        System.out.println(CGPA);
            //With threads
            out.println("\nWith threads");
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.Odd_even_sort_with_threads(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time with threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=100");


            //For n=1000
            out.println("\nFor n=1000");
            //Without threads
            out.println("Without threads");
            CGPA=FutureBuilder.generateArrayList(1000);
            out.println("List before sorting is");
            out.println(CGPA);
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.odd_even_sort(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time without threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=1000");

//        System.out.println(CGPA);
            //With threads
            out.println("\nWith threads");
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.Odd_even_sort_with_threads(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time with threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=1000");


            //For n=10000
            out.println("\nFor n=10000");
            //Without threads
            out.println("Without threads");
            CGPA=FutureBuilder.generateArrayList(10000);
            out.println("List before sorting is");
            out.println(CGPA);
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.odd_even_sort(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time without threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=10000");

//        System.out.println(CGPA);
            //With threads
            out.println("\nWith threads");
            start_time=System.nanoTime();
            sortedList=(ArrayList<Double>) FutureBuilder.Odd_even_sort_with_threads(CGPA).clone();
            end_time=System.nanoTime();
            out.println("List after sorting is");
            out.println(sortedList);
            out.println("Execution time with threads = "+((end_time-start_time)/1000000.0)+" milliseconds for n=10000");
        }finally {
            if(out!=null) {
                out.close();
            }
        }
    }
}