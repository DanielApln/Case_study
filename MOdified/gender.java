package casestudy;

import java.io.IOException;
import java.util.Scanner;

public class GenderValidation {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        
        System.out.print("Enter Gender: ");
        String gender = scn.nextLine();

        if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")) {
        	throw new IOException("Gender added successfully.");
        } else {
            System.out.println("Invalid Gender Input. Try again.");
        }   
        scn.close();
    	
    	/*System.out.print("Enter Gender: ");
        String gender = scn.nextLine();

        if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")) {
        	System.out.println("Gender added successfully");
        } else {
            System.out.println("Invalid Gender Input. Try again.");
        }   
        scn.close();*/
    }
}
    
