package MOdified;

import java.io.IOException;
import java.util.Scanner;

class ContactException extends IOException
{
    public ContactException(String msg) 
    {
        super(msg);
    }
}

public class contactNo
{
    public static void main(String[] args) 
    {
        Scanner scn = new Scanner(System.in);

        try
        {
            System.out.print("Contact Number: ");
            String cno = scn.nextLine();

            contactValidation(cno);
            System.out.println("Your contact number is registered successfully!");
        } 
        catch (IOException e)
        {
            System.out.println("Invalid! " + e.getMessage());
        }
    }

    public static void contactValidation(String num) throws ContactException 
    {
        for (int i = 0; i < num.length(); i++) 
        {
            if (!Character.isDigit(num.charAt(i)))
            {
                throw new ContactException("Contact Number must only contain numbers.");
            }
        }

        if (num.length() != 11)
        {
            throw new ContactException("Contact Number must be exactly 11 digits.");
        }
    }
}
