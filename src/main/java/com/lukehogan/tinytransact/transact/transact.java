package com.lukehogan.tinytransact.transact;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Class to establish the basic methods needed for the system
public class transact {


    //Method that tests card validity using Luhn's Algorithm. If checksum is correct, card is valid.
    public static Boolean luhnCheck(long cardNum){
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
    
    
    //Method for validating a given card
    public static String cardType(long cardNum){
        //get length of card number
        String cardStr = Long.toString(cardNum);
        int inputLength = cardStr.length();
        String cardType = "INVALID";
        switch (inputLength) {
        	case 14:
        		//if a 14 digit card begins with 7, it's a funcard.
        		int cardBeginning14 = Character.getNumericValue(cardStr.charAt(0));
        		if(cardBeginning14 == 7 && luhnCheck(cardNum)) {
        			cardType = "FunCard";
        		}
        	case 15:
                //if a 15 digit card begins with 68, it's a PoshCard
                int cardBeginning15 = Integer.parseInt(cardStr.substring(0,1));
                if( cardBeginning15 == 68 && luhnCheck(cardNum)){
                    cardType = "PoshCard";
                }
            case 16:
                int cardBeginning16 = Integer.parseInt(cardStr.substring(0,1));
                //If a 16 digit card begins with 23, it's a ZapCard
                if(cardBeginning16 == 23 && luhnCheck(cardNum)){
                    cardType = "ZapCard";
                }
                //if a 16 digit card begins with 8, it's a FusionCard.
                if(Character.getNumericValue(cardStr.charAt(0)) == 8 && luhnCheck(cardNum)){
                    cardType = "FusionCard";
            }
        }
        return cardType;
    }
    
    //method to generate a new card number that conforms to Luhn's algorithm (UNCHECKED)
    public static long generateCardNum(String type) {
    	List<Integer> newCard = new ArrayList<>();
    	Random rand = new Random();
    	if(type == "FusionCard") {
    		//FusionCard is 16 digits, begins with 8.
    		//set 8 as the first digit
    		newCard.add(8);
    		//generate the remaining 14 digits
    		for(int i = 0; i < 14; i++) {
    			newCard.add(rand.nextInt());
    		}
    		
    		//calculate and add the check digit to satisfy Luhn's algorithm (break logic into another private static method)
    		//https://en.wikipedia.org/wiki/Luhn_algorithm
    		newCard.add(generateCheckDigit(newCard));
    		
    		// TODO get the list into a long. A
    		
    	}
    	
    }
    
    //Code to calculate check digit given an arraylist. (UNCHECKED)
    private static int generateCheckDigit(ArrayList input) {
    	//Work from rightmost digit, doubling every other digit.
    	//imports are messed up, so for now we'll pseudocode this
    	int prodHolder = 0;
    	int prodLength = 0;
    	int sum = 0;
    	listSize = input.size();
    	//start from right, double every other, sum the digits of the resulting products.
    	for(int i = listSize - 1; i >= 0; i -= 2) {
    		prodHolder = input.get(i) * 2;
    		prodLength = Integer.toString(prodHolder).length();
    		if(prodLength > 1) {
    			for(int j = 0; j < prodLength; j++){
                    sum += Character.getNumericValue(Integer.toString(prodHolder).charAt(prodLength-1-j));
                }
    		}	
    	}
    	//Sum the other digits
    	for(int i = listSize - 2; i >= 0; i -= 2) {
    		sum += input.get(i);
    	}
    	//Calculate the check digit using the calculated sum
    	int checkDigit = 10 - (sum % 10);
    	
    	return checkDigit;
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