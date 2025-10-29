package oopFinalsCaseStudy;

import java.util.Scanner;
import java.io.IOException;

public class NameAddressValidationMFM {
    
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
		System.out.println("First Name: " + firstName.toUpperCase());
		System.out.println("Middle Name: " + middleName.toUpperCase());
		System.out.println("Last Name: " + lastName.toUpperCase());
		System.out.println("Father's Name: " + fatherName.toUpperCase());
		System.out.println("Mother's Name: " + motherName.toUpperCase());
		System.out.println("Address: " + address.toUpperCase());
		
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
				throw new IllegalArgumentException("Middle Name cannot be empty. Please try again.");
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
		        	throw new IllegalArgumentException("Middle Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void lastNameValidation (String input) throws IOException{
			if(input == null || input.isBlank()) {
				throw new IllegalArgumentException("Last Name cannot be empty. Please try again.");
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
		        	throw new IllegalArgumentException("Last Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void fatherNameValidation (String input) throws IOException{
			if(input == null || input.isBlank()) {
				throw new IllegalArgumentException("Father's Name cannot be empty. Please try again.");
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
		        	throw new IllegalArgumentException("Father's Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void motherNameValidation (String input) throws IOException {
			if(input == null || input.isBlank()) {
				throw new IllegalArgumentException("Mother's Name cannot be empty. Please try again.");
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
		        	throw new IllegalArgumentException("Mother's Name cannot contain any special character."); 
		        }
		    }		
		}
		
		public static void addressValidation (String input) throws IOException {
			if(input == null || input.trim().isEmpty()) {
				throw new IllegalArgumentException("Address cannot be empty. Please try again.");
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
		        	throw new IllegalArgumentException("Address cannot contain some special character."); 
		        }
		    }
		}
	}
}