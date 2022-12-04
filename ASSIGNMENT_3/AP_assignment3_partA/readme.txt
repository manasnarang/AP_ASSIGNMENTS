FutureBuilder class has static methods that can be used to generate arraylists of CGPAs of size n. It has 2 sorting
functions:- odd_even_sort(ArrayList<Double> list) and Odd_even_sort_with_threads(ArrayList<Double> list)
odd_even_sort sorts the arraylist in decreasing order by performing odd-even transposition sort without use of threads
and Odd_even_sort_with_threads performs the sorting using 2 threads each for the odd and even phase of the transposition sort.
The 2 threads run at the same time performing a maximum of n/2 swaps each

Testcases have been hardcoded for the cases n=1, n=10, n=100, n=1000 and n=10000
The code writes the unsorted arraylist randomly generated and its sorted form to the text file partA_output.txt for each case along
with the time taken to execute the sorting with and without threads.

Generics have not been incorporated in this code since there was no need for generic programming in this question
as the values of list were of fixed type(Double)