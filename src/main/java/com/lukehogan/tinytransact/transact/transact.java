package com.lukehogan.tinytransact.transact;

import java.util.Random;
import java.lang.Math;

//Class to establish the basic methods needed for the system
public class transact {

    public static String cardCheck(long cardNum) {
        String type = "";
        String checkResult =  "";
        boolean luhnCheck;

        //Check if the card is a valid card (do Luhn checksum check)
        luhnCheck = checksum(cardNum);
        //determine card type
        type = cardType(cardNum);
        // TODO Determine if the card exists in the DB

        // TODO return string containing card type, or "invalid" tag with explanation
        return checkResult;
    }

    //Function that tests card validity using Luhn's Algorithm. If checksum is correct, card is valid.
    public static Boolean checksum(long cardNum){
        boolean valid = false;
        String cardString = Long.toString(cardNum);
        int sum = 0;
        int prodLength = 0;
        int prodHolder = 0;
        int digits = cardString.length();

        //take every other digit starting with 2nd to last digit, multiply by 2, and sum digits of resulting products.
        for(int i = 1; i < digits; i += 2){
            prodHolder = Character.getNumericValue(cardString.charAt(digits-1-i)) * 2;
            prodLength = Integer.toString(prodHolder).length();
            System.out.println(i);
            //if the length of the product is greater than 1, we need to add each of those digits to the sum
            if(prodLength > 1){
                for(int j = 0; j < prodLength; j++){
                    sum += Character.getNumericValue(Integer.toString(prodHolder).charAt(prodLength-1-j));
                    System.out.println(j);
                }
            }
            else{
                sum += prodHolder;
            }         
        }

        //add every other digit starting with first digit
        for(int i = 0; i < digits; i += 2){
            sum += Character.getNumericValue(cardString.charAt(digits-1-i));
            System.out.println(i);
        }

        //if last digit of sum is 0, the card number is valid.
        int sumLength = Integer.toString(sum).length();
        if(Character.getNumericValue(Integer.toString(sum).charAt(sumLength - 1)) == 0){
            valid = true;
        }

        return valid;
    }

    //Method to determine whether a card is Visa, Mastercard, Discover, American Express or Invalid
    public static String cardType(long cardNum){
        //get length of card number
        String cardStr = Long.toString(cardNum);
        int inputLength = cardStr.length();
        String cardType = "INVALID";
        switch (inputLength) {
            case 13:
                //if the first digit of a 13 digit card is 4, it's a Visa.
                if(Character.getNumericValue(cardStr.charAt(0)) == 4){
                    cardType = "VISA";
                }
            case 15:
                //if a 15 digit card begins with 34 or 37, it's an American Express
                int cardBeginning15 = Integer.parseInt(cardStr.substring(0,1));
                if( cardBeginning15 == 34 || cardBeginning15 == 37){
                    cardType = "AMEX";
                }
            case 16:
                int cardBeginning16 = Integer.parseInt(cardStr.substring(0,1));
                //If a 16 digit card begins with 51,52,53,54,55, it's a Mastercard.
                if(cardBeginning16 == 51 || cardBeginning16 == 52 || cardBeginning16 == 53 || cardBeginning16 == 54 || cardBeginning16 == 55){
                    cardType = "MASTERCARD";
                }
                //if a 16 digit card begins with 4, it's a Visa.
                if(Character.getNumericValue(cardStr.charAt(0)) == 4){
                    cardType = "VISA";
            }
        }
        return cardType;
    }

    
    //method to charge a bank account using an issued credit card
    public static String debit(float amount, int cardNum){
        String dummy = "incomplete";
        // TODO run a card check. return error if DNE or not issued
        String checkResult = cardCheck(cardNum);
        if(checkResult == "invalid"){
            return("invalid");
        }
        // TODO locate the associated bank account number from the card table

        // TODO update funds to add the refund

        // TODO return approved, denied, and card type, or indicate card is invalid or unissied

        return dummy;
    }

    //method to refund a bank account using an issued credit card
    public static String refund(float amount, int cardNum){
        String dummy = "incomplete";
        // TODO run a card check. return error if DNE or not issued
        
        // TODO locate the associated bank account number from the card table

        // TODO update funds to add the refund
        
        // TODO return approved, denied, and card type, or indicate card is invalid or unissied

        return dummy;
    }

    //method to deposit funds into a bank account
    public static String deposit(float amount, int accountNum){
        String dummy = "incomplete";
        // TODO check if account exists

        // TODO complete the transaction and return result.

        return dummy;
    }

    
    //method to generate a bank account number (9 digits)
    public static int generateAcctNum(){
    	double number = 0;
    	Random rand = new Random();
    	for(int i = 0; i < 9; i ++) {
    		int newDigit = rand.nextInt(10);
    		number = number + newDigit * Math.pow((double)10,(double)i);
    		
    	}

        return (int) number;
    }





}