package javaimpatient.ch01;

import java.util.Scanner;
/*
Write a program that reads an integer and prints it in binary, octal, and hexadecimal.
Print the reciprocal as a hexadecimal floating-point number.
 */
public class Exercise01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = scanner.nextInt();
        System.out.println("number in decimal: " + number);
        System.out.println("number in binary: " + Integer.toBinaryString(number));
        System.out.println("number in octal: " + Integer.toOctalString(number));
        System.out.println("number in hexadecimal: " + Integer.toHexString(number));

        double reciprocal = 1.0 / number;
        System.out.println("reciprocal of number: " + reciprocal);
        System.out.println("reciprocal as a hexadecimal floating-point number: " + Double.toHexString(reciprocal));
    }
}
