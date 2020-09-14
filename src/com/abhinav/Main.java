package com.abhinav;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sourceRadix = 0;
        int targetRadix = 0;
        String radixIn = "";
        String number = "";
        String radixOut = "";
        try {
            radixIn = sc.nextLine();
            number = sc.nextLine();
            radixOut = sc.nextLine();
        } catch (Exception e) {
            System.out.println("there is an error");
        }
        if (checkString(radixIn) && checkString(radixOut)) {
            sourceRadix = Integer.parseInt(radixIn);
            targetRadix = Integer.parseInt(radixOut);
        } else {
            System.out.println("The input radix or output radix are not valid integer. There is an error");
        }
        fractionalNumbers(sourceRadix, number, targetRadix);
        //the below commented code if part of previous exercises
//        switch (targetRadix) {
//            case 1:
//                System.out.println(convertOneBase(Integer.parseInt(number), targetRadix));
//                break;
//            case 2 :
//                System.out.println(Integer.toBinaryString(Integer.parseInt(number)));
//                break;
//            case 8 :
//                System.out.println(Integer.toOctalString(Integer.parseInt(number)));
//                break;
//            case 16 :
//                System.out.println(Integer.toHexString(Integer.parseInt(number)));
//                break;
//            default :
//                if(sourceRadix == 1){
//                    System.out.println(convertOneBase(Integer.parseInt(number), targetRadix));
//                } else {
//                    System.out.println(toAnyNumberConverter(sourceRadix, Integer.parseInt(number), targetRadix));
//                }
//                break;
//        }
    }
    private static boolean checkString(String s) {
        if (s == null || s.equals("")) { // checks if the String is null
            return false;
        }
        int len = s.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            // checks whether the character is a digit
            // if it isa digit ,it will return true
            if (Character.isDigit(s.charAt(i))) {
                count++;
            }
        }
        //a if is being used to check for min and max radix in our project
        //max is 36 because there are 0-9 and a-z i.e 10+26
        //min cant be zero as atleast 1 element should exist and that will be our radix
        if(count == s.length()){
            if(Integer.parseInt(s) < 1 || Integer.parseInt(s) > 36) return  false;
        }
        //this will return true only if all the elements in the input string are digits
        return count == s.length();

    }

    private static void fractionalNumbers (int sourceRadix, String number, int targetBase) {
        String[] arrayFirst = number.split("\\.");
        if(sourceRadix == 1){
            System.out.println(convertOneBase(Integer.parseInt(number), targetBase));
        }
        //split the input into two parts
        //integer part
        boolean validInput = true;
        for (int i = 0; i < arrayFirst[0].length(); i++) {
            if (Character.digit(arrayFirst[0].charAt(i), sourceRadix) < 0) {
                validInput = false;
                break;
            }
        }
        if(validInput) {
            int integerPart = sourceRadix <= 1 ? arrayFirst[0].length() : Integer.parseInt(arrayFirst[0], sourceRadix);
            double fractionalPart = 0.0;
            if (arrayFirst.length == 2) {
                for (int i = 0; i < arrayFirst[1].length(); i++) {
                    fractionalPart += Character.getNumericValue(arrayFirst[1].charAt(i)) / Math.pow(sourceRadix, i + 1);
                }
            }

            StringBuilder stringBuilder = new StringBuilder(Integer.toString(integerPart, targetBase) + ".");
            for (int i = 0; i < 5; i++) {
                fractionalPart *= targetBase;
                int temp = (int) fractionalPart;
                if (temp >= 10) {
                    stringBuilder.append((char) (temp + 87));
                } else {
                    stringBuilder.append(temp);
                }
                fractionalPart -= temp;

            }
            System.out.println(targetBase <= 1 ? "1".repeat(integerPart) : stringBuilder.toString());
        }
    }

    private static StringBuilder convertOneBase (int number, int target) {
//        String string = "";
        StringBuilder string = new StringBuilder();
        if (target == 1) {
            while (number-- > 0) {
                string.append(1);
            }
            return string;
        } else {
            int n = 0;
//            System.out.println("in else & target " + target);
            while (number  > 0){
                n++;
                number /= 10;
            }
            return toAnyNumberConverter(10, n, target);
//            string.append(n);
        }
//        return string;
    }

    private static StringBuilder toAnyNumberConverter(int sourceRadix, int number, int newBase) {
        //this parseInt method will first convert our number to decimal base and then allow
        //conversion to a new target base
        number = Integer.parseInt(Integer.toString(number), sourceRadix);
//        System.out.println("from" + number + " " + newBase );
        //toString() method helps us to convert any decimal number to any of the bases we want

        return new StringBuilder(Integer.toString(number, newBase));
    }

    private static StringBuilder decimalToBinary(int number) {
//        StringBuilder binary = new StringBuilder("0b");
        if(number == 0){
            return new StringBuilder("0");
        }
        int count = 0;
        StringBuilder s1  = new StringBuilder();
        while(number != 0){
            if(count == 0){
                s1 = new StringBuilder(String.valueOf(number % 2));
                number /= 2;
                count++;
            } else {
                s1.append(number % 2);
                number /= 2;
            }
        }
        s1.reverse();
//        binary.append(s1);
        return s1;
    }

    private static StringBuilder decimalToOctal(int number) {
        StringBuilder binary = new StringBuilder("0");
        if(number == 0){
            return binary.append("0");
        }
        int count = 0;
        StringBuilder s1  = new StringBuilder();
        while(number != 0){
            if(count == 0){
                s1 = new StringBuilder(String.valueOf(number % 8));
                number /= 8;
                count++;
            } else {
                s1.append(number % 8);
                number /= 8;
            }
        }
        s1.reverse();
        binary.append(s1);
        return binary;
    }

    private static StringBuilder decimalToHex (int number) {
        StringBuilder binary = new StringBuilder("0x");
        if(number == 0){
            return binary.append("0");
        }
        int count = 0;
        String[] hexArray = {"0","1","2","3","4","5","6","7","8","9","A","b","C","D","E","F"};
        StringBuilder s1 = new StringBuilder();
        while(number != 0){
            if(count == 0){
                s1 = new StringBuilder(hexArray[number % 16]);
                number /= 16;
                count++;
            } else {
                s1.append(hexArray[number % 16]);
                number /= 16;
            }
        }
        s1.reverse();
        binary.append(s1);
        return binary;
    }
}
