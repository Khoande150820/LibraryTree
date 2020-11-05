package util;

import java.util.Scanner;

public class Validate {

    public static int getIntInRange(int min, int max) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            int result = Integer.parseInt(sc.nextLine().trim());
            if (result < min || result > max) {
                System.out.println("Please enter number between " + min + " and " + max);
            } else {
                return result;
            }
        }
    }

    public static String getString() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String result = sc.nextLine().trim();
            if (result.isEmpty()){
                System.out.println("Must not enter empty value");
            } else{
                return result;
            }
        }
    }

}
