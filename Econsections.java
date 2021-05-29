package econsections;

import java.util.Scanner;

public class Econsections {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the market of research: ");
        String market = input.nextLine();
        
        System.out.println("\nChoose (1) Supply (2) Demand");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        
        boolean slope = false;
        if(choice == 1)
            slope = true;
        
        System.out.print("\nEnter range of interval: ");
        int interval = input.nextInt();
        
        System.out.print("\nEnter " + (slope ? "max": "min") + " quantity: "); int ext = input.nextInt();
        
        System.out.println("\nEnter the variables for the equation of the " + (slope ? "supply" : "demand") + " curve.");
        System.out.println("Equation format: p=a" + (slope ? "+": "-") + "bq");
        
        System.out.print("\nHow many individual " + (slope ? "supply": "demand") + " curves are there? "); 
        int numOfLines = input.nextInt();   
        
        SocialCurve socialCurve = new SocialCurve(numOfLines, market, slope, interval, ext);
        
        for(int count = 0; count < numOfLines; count++)
        {
            System.out.println();
            input.nextLine();
            System.out.println( (slope ? "Supply": "Demand") + " curve#" + (count+1) + "--");
            System.out.print("Enter name of the firm: "); String individual = input.nextLine();
            System.out.print("Enter a: "); double a = input.nextDouble();
            System.out.print("Enter b: "); double b = input.nextDouble();
            System.out.println();
            
            LinearCurve linea = new LinearCurve(a, b, individual, slope);
            socialCurve.add(linea);
        }
        socialCurve.print();
        
        System.out.println();
        
        System.out.println("Choose (1) Individual (2) Linear Comparison (3) Market ");
        System.out.print("Enter your choice: "); choice = input.nextInt();
        
        System.out.println();
        
        String name = "";
        if(choice == 1)
        {
            System.out.print("Enter the name of the firm: ");
            name = input.next();
        }
        
        System.out.print("\nEnter quantity: "); 
        int quantity = input.nextInt();
        
        System.out.println();
        
        socialCurve.prompt(choice, name, quantity);
    }
}
