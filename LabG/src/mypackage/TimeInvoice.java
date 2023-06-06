/*
Abe Brege
LabG
5/7/23
*/
package mypackage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;
import java.util.Scanner;

public class TimeInvoice {

    public static void main(String[] args) {
        /*
        I really wanted to drill down on being efficient with easy to read
        code since this is my final project so I did some things like 
        not declaring all of my variables at the top, using the scanner more
        efficiently by not creading instances every time i want to use it
        and keeping it to one line for as much as I could. Just something 
        to note when you read this code since it is a bit of a change from my 
        previous code
        */
        
        Scanner input = new Scanner(System.in);
        boolean continueOrdering = true;
        int orderNumber = 1;
        
        //starting the order loop
        while (continueOrdering) {
            //getting user input
            System.out.print("Please enter shipping address line 1: ");
            String address1 = input.nextLine().trim();

            System.out.print("Please enter shipping address line 2: ");
            String street = input.nextLine().trim();

            System.out.print("Please enter shipping address line 3: ");
            String address3 = input.nextLine().trim();
            
            //I looked it up and it said decalring this object as null was best
            LocalDate deliveryDate = null;
            boolean validDeliveryDate = false;

            //checking to see if the delivery date is valid and then parsing it into deliveryDate
            while (!validDeliveryDate) {
                System.out.print("Desired delivery date for this order: ");
                String deliveryDateStr = input.nextLine().trim();
                
                //checking for exceptions
                try {
                    deliveryDate = LocalDate.parse(deliveryDateStr);
                    validDeliveryDate = deliveryDate.isAfter(LocalDate.now());
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in this format: YYYY-MM-DD");
                }

                if (!validDeliveryDate) {
                    System.out.println("Delivery date should be in the future and not today.");
                }
            }

            //parsing address1 into first name, middle name and last name
            String[] nameParts = address1.split(" ");
            String firstName = nameParts[0];
            String middleName = null;
            String lastName = nameParts[nameParts.length - 1];
            
            //loop to create the middle name output
            if (nameParts.length == 3) {
                middleName = nameParts[1];
            } else if (nameParts.length == 4) {
                middleName = nameParts[1] + " " + nameParts[2];
            }

            //parsing address3 into city, state and zip
            String[] address3Parts = address3.split(",");
            String city = address3Parts[0].trim();
            String[] stateZip = address3Parts[1].trim().split(" ");
            String state = stateZip[0];
            String zip = stateZip[1];

            //printing order summary
            System.out.println("\nORDER SUMMARY");
            System.out.println("Order Number:          " + orderNumber);
            System.out.println("Customer First Name:   " + firstName);
            System.out.println("Customer Middle Name:  " + middleName);
            System.out.println("Customer Last Name:    " + lastName);
            System.out.println("Street:                " + street);
            System.out.println("City:                  " + city);
            System.out.println("State:                 " + state);
            System.out.println("Zip:                   " + zip);
            System.out.println("Delivery Date:         " + deliveryDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + "\n");

            //prompting for another order
            System.out.print("Do you wish to process another order? (y/n): ");
            String response = input.nextLine().trim();

            continueOrdering = response.equalsIgnoreCase("y");
            orderNumber++;
        }

    }
}
