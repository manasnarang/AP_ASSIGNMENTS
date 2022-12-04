BalancedBinaryTree class implements an AVL tree with recursive functions to perform insertion, search and calculate
height. It has private methods to perform left and right rotation when needed in order to make sure that the tree remains
balanced.

All 9 simulations are being run in the Main class and the outputs asked by the question are written to the text file
partB_output.txt for values of N=10, 1000 and 10^6 and T=0,2,4
N is the number of elements in the tree and T is the number of threads being used(T=0 implies non-parallelization variant)