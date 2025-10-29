package oopFinalsCaseStudy;

import java.util.Scanner;
import java.io.IOException;

public class NameAddressValidationMFM {
	
    private static String toTitleCase(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        
        String processedInput = input.trim().toLowerCase();
        
        String[] words = processedInput.split(" ");
        StringBuilder titleCaseString = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                
                titleCaseString.append(capitalizedWord).append(" ");
            }
        }
        
        return titleCaseString.toString().trim();
    }
    
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		
		String firstName = "";
		String middleName = "";
		String lastName = "";
		String fatherName = "";
		String motherName = "";
		String address = "";
		
		while (true) 
		{
			try 
			{
				System.out.print("Enter First Name: ");
				firstName = scan.nextLine();
				
				validation.firstNameValidation(firstName);
				break;
			}
			catch (IOException e) {
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}
		
		while (true) 
		{
			try 
			{
				System.out.print("Enter Middle Name: ");
				middleName = scan.nextLine();
				
				validation.middleNameValidation(middleName);
				break;
			}
			catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}
		
		while (true) 
		{
			try 
			{
				System.out.print("Enter Last Name: ");
				lastName = scan.nextLine();
				
				validation.lastNameValidation(lastName);
				break;
			}
			catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}
		
		while (true) 
		{
			try 
			{
				System.out.print("Enter Father's Name: ");
				fatherName = scan.nextLine();
				
				validation.fatherNameValidation(fatherName);
				break;
			}
			catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}
		
		while (true) 
		{
			try 
			{
				System.out.print("Enter Mother's Name: ");
				motherName = scan.nextLine();
				
				validation.motherNameValidation(motherName);
				break;
			}
			catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}
		
		while (true) 
		{
			try 
			{
				System.out.print("Enter Address: ");
				address = scan.nextLine();
				
				validation.addressValidation(address);
				break;
			}
			catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}
		
		System.out.println("\n=== Account Information Successfully Added! ===");
		System.out.println("First Name: " + toTitleCase(firstName));
		System.out.println("Middle Name: " + toTitleCase(middleName));
		System.out.println("Last Name: " + toTitleCase(lastName));
		System.out.println("Father's Name: " + toTitleCase(fatherName));
		System.out.println("Mother's Name: " + toTitleCase(motherName));
		System.out.println("Address: " + toTitleCase(address));
		
		scan.close();
	}
	
	class validation {
		public static void firstNameValidation (String input) throws IOException{
			if (input == null || input.isBlank()) {
				throw new IOException("First Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("First Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void middleNameValidation (String input) throws IOException{
			if(input == null || input.isBlank()) {
				throw new IOException("Middle Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Middle Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void lastNameValidation (String input) throws IOException{
			if(input == null || input.isBlank()) {
				throw new IOException("Last Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Last Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void fatherNameValidation (String input) throws IOException{
			if(input == null || input.isBlank()) {
				throw new IOException("Father's Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Father's Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void motherNameValidation (String input) throws IOException {
			if(input == null || input.isBlank()) {
				throw new IOException("Mother's Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Mother's Name cannot contain any special character."); 
		        }
		    }		
		}
		
		public static void addressValidation (String input) throws IOException {
			if(input == null || input.trim().isEmpty()) {
				throw new IOException("Address cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetterOrDigit(ch)) {
		        	continue;
		        }
		        else if(ch == ' ' || ch == '#' || ch == ',') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Address cannot contain some special character."); 
		        }
		    }
		}
	}
}
