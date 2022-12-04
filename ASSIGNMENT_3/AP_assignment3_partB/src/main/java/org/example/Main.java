package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        PrintWriter out=null;
        long start_time;
        long end_time;
        double bound =( (2 * Math.pow(10, 9)) + 1);
        Random random=new Random();
        int N;
        ArrayList<Long> tree_elements=new ArrayList<>();
        boolean search;
        try{
            out=new PrintWriter(new FileWriter("partB_output.txt"));
            //N=10
            N=10;
            out.println("For N=10");
            //Constructing tree
            out.println("Constructing the tree");
            for(int i=0;i<N;i++){
                tree_elements.add((long)(random.nextLong((long) bound) - Math.pow(10, 9)));
            }
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            BalancedBinaryTree tree=new BalancedBinaryTree();
            for(int i=0;i<N;i++){
                tree.add(tree_elements.get(i));
            }
            end_time=System.nanoTime();
            out.println("Time taken for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            BalancedBinaryTree tree_T1=new BalancedBinaryTree();
            Thread thread1=new TreeThread(tree_T1,0,(N/2)-1,tree_elements);
            Thread thread2=new TreeThread(tree_T1,N/2,N-1,tree_elements);
            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
            end_time=System.nanoTime();
            out.println("Time taken(T=2) for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            BalancedBinaryTree tree_T2=new BalancedBinaryTree();
            Thread thread_T2_1=new TreeThread(tree_T2,0,(N/4)-1,tree_elements);
            Thread thread_T2_2=new TreeThread(tree_T2,N/4,(N/2)-1,tree_elements);
            Thread thread_T2_3=new TreeThread(tree_T2,N/2,(3*(N/4))-1,tree_elements);
            Thread thread_T2_4=new TreeThread(tree_T2,(3*(N/4)),N-1,tree_elements);
            thread_T2_1.start();
            thread_T2_1.join();
            thread_T2_2.start();
            thread_T2_2.join();
            thread_T2_3.start();
            thread_T2_3.join();
            thread_T2_4.start();
            thread_T2_4.join();
            end_time=System.nanoTime();
            out.println("Time taken(T=4) for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //Calculating Height
            out.println("Calculating Height of the tree");
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            int height=tree.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            int height_T1=tree_T1.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height_T1);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            int height_T2=tree_T2.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height_T2);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //Searching for Element
            out.println("Searching for an element in the tree");
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            search= tree.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            search= tree_T1.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            search= tree_T2.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //End of N=10



            //N=1000
            N=1000;
            out.println("For N=1000");
            //Constructing tree
            out.println("Constructing the tree");
            tree_elements.clear();
            for(int i=0;i<N;i++){
                tree_elements.add((long)(random.nextLong((long) bound) - Math.pow(10, 9)));
            }
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            BalancedBinaryTree tree_1000_0=new BalancedBinaryTree();
            for(int i=0;i<N;i++){
                tree_1000_0.add(tree_elements.get(i));
            }
            end_time=System.nanoTime();
            out.println("Time taken for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            BalancedBinaryTree tree_1000_2=new BalancedBinaryTree();
            Thread thread_N2_T1_1=new TreeThread(tree_1000_2,0,(N/2)-1,tree_elements);
            Thread thread_N2_T1_2=new TreeThread(tree_1000_2,N/2,N-1,tree_elements);
            thread_N2_T1_1.start();
            thread_N2_T1_1.join();
            thread_N2_T1_2.start();
            thread_N2_T1_2.join();
            end_time=System.nanoTime();
            out.println("Time taken(T=2) for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            BalancedBinaryTree tree_N2_T2=new BalancedBinaryTree();
            Thread thread_N2_T2_1=new TreeThread(tree_N2_T2,0,(N/4)-1,tree_elements);
            Thread thread_N2_T2_2=new TreeThread(tree_N2_T2,N/4,(N/2)-1,tree_elements);
            Thread thread_N2_T2_3=new TreeThread(tree_N2_T2,N/2,(3*(N/4))-1,tree_elements);
            Thread thread_N2_T2_4=new TreeThread(tree_N2_T2,(3*(N/4)),N-1,tree_elements);
            thread_N2_T2_1.start();
            thread_N2_T2_1.join();
            thread_N2_T2_2.start();
            thread_N2_T2_2.join();
            thread_N2_T2_3.start();
            thread_N2_T2_3.join();
            thread_N2_T2_4.start();
            thread_N2_T2_4.join();
            end_time=System.nanoTime();
            out.println("Time taken(T=4) for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //Calculating Height
            out.println("Calculating Height of the tree");
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            int height_n2_0=tree_1000_0.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height_n2_0);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            int height_n2_2=tree_1000_2.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height_n2_2);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            int height_n2_4=tree_N2_T2.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height_n2_4);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //Searching for Element
            out.println("Searching for an element in the tree");
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            search= tree_1000_0.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            search= tree_1000_2.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            search= tree_N2_T2.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //End of N=1000

            //N=10^6
            N=1000000;
            out.println("For N=10^6");
            //Constructing tree
            out.println("Constructing the tree");
            tree_elements.clear();
            for(int i=0;i<N;i++){
                tree_elements.add((long)(random.nextLong((long) bound) - Math.pow(10, 9)));
            }
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            BalancedBinaryTree tree_N3_0=new BalancedBinaryTree();
            for(int i=0;i<N;i++){
                tree_N3_0.add(tree_elements.get(i));
            }
            end_time=System.nanoTime();
            out.println("Time taken for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            BalancedBinaryTree tree_N3_2=new BalancedBinaryTree();
            Thread thread_N3_2_1=new TreeThread(tree_N3_2,0,(N/2)-1,tree_elements);
            Thread thread_N3_2_2=new TreeThread(tree_N3_2,N/2,N-1,tree_elements);
            thread_N3_2_1.start();
            thread_N3_2_1.join();
            thread_N3_2_2.start();
            thread_N3_2_2.join();
            end_time=System.nanoTime();
            out.println("Time taken(T=2) for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            BalancedBinaryTree tree_N3_4=new BalancedBinaryTree();
            Thread thread_N3_4_1=new TreeThread(tree_N3_4,0,(N/4)-1,tree_elements);
            Thread thread_N3_4_2=new TreeThread(tree_N3_4,N/4,(N/2)-1,tree_elements);
            Thread thread_N3_4_3=new TreeThread(tree_N3_4,N/2,(3*(N/4))-1,tree_elements);
            Thread thread_N3_4_4=new TreeThread(tree_N3_4,(3*(N/4)),N-1,tree_elements);
            thread_N3_4_1.start();
            thread_N3_4_1.join();
            thread_N3_4_2.start();
            thread_N3_4_2.join();
            thread_N3_4_3.start();
            thread_N3_4_3.join();
            thread_N3_4_4.start();
            thread_N3_4_4.join();
            end_time=System.nanoTime();
            out.println("Time taken(T=4) for constructing tree = "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //Calculating Height
            out.println("Calculating Height of the tree");
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            int height_n3_0=tree_N3_0.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height_n3_0);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            int height_n3_2=tree_N3_2.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height_n3_2);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            int height_n3_4=tree_N3_4.getHeight();
            end_time=System.nanoTime();
            out.println("Height= "+height_n3_4);
            out.println("Height was calculated in "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //Searching for Element
            out.println("Searching for an element in the tree");
            //T=0
            out.println("T=0(Without Parallelization)");
            start_time=System.nanoTime();
            search= tree_N3_0.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");

            //T=2
            out.println("T=2");
            start_time=System.nanoTime();
            search= tree_N3_2.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");


            //T=4
            out.println("T=4");
            start_time=System.nanoTime();
            search= tree_N3_4.search_element(10000);
            end_time=System.nanoTime();
            out.println(search?"Element 10000 exists":"Element 10000 does not exist");
            out.println("Search was done in "+((end_time-start_time)/1000000.0)+" milliseconds\n");
            //End of N=10^6
        }finally {
            if (out!=null){
                out.close();
            }
        }
    }
}
