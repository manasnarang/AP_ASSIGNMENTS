import com.example.FutureBuilder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input:- ");
        Scanner sc = new Scanner(System.in);
        FutureBuilder fb=new FutureBuilder();
        if (sc.nextLine().equals("Enter FutureBuilder")) {
            fb.startApp();
        }
    }
}