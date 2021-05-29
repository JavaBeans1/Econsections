package econsections;

import java.io.Serializable;
import java.lang.Math;
import java.util.Comparator;

public class LinearCurve extends Curve implements Serializable {
    
    private String name;
    private double marginalValue;
    private double surplus;
    private double total;
    private double output;
    private String unitOfMeasurement;
    private double periodOfAdjustment;      //Intervals at which quantity is increasing or decreasing
    
    LinearCurve()
    {
        this(0,0, "", false, "$");
    }
    
    LinearCurve(String name)
    {
        this.name = name;
    }
    
    LinearCurve(double a, double b, String name, boolean slope)
    {
        this(a,b,name, slope, "$");
    }
    
    LinearCurve(double a, double b, String name, boolean slope, String unitOfMeasurement)
    {
        super(a, b, slope);
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement; 
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String marginalValue(int quantity)       // cost/benefit
    {
        return "Marginal " + (slope ? "cost": "benefit") + ": " + getPrice(quantity) + "\n";
    }
    
    public double surplusAt(int quantity)
    {
        return Math.pow(quantity, 2) * super.getB() / 2.0;
    }
    
    public String surplus(int quantity)             // consumer/producer
    {
        return "Individual " + (slope ? "producer": "consumer") + ": " + surplusAt(quantity) + "\n";
    }
    
    public String totalValue(int quantity)          //Total cost/benefit
    {
        return "Total " + (slope ? "cost": "benefit") + ": " + totalValueAt(quantity) + "\n";
    }
    
    public double totalValueAt(int quantity)
    {
        double mult = 2;
        if(slope)
            mult *= (-1);
        
        return (getPrice(quantity) + quantity*super.getB()/mult) * quantity;
    }
    
    public String individualOutput(int quantity)      //Revenue/Expenditure
    {
        return (slope ? "Revenue": "Expenditure") + ": " + quantity * getPrice(quantity) + "\n";
    }

   public String print(int quantity)
    {
        return "Point of Interest: " + quantity + "\n" + (slope ? "Consumption": "Production") + " statistics for " + this.name + " --\n" + marginalValue(quantity) + surplus(quantity) + totalValue(quantity) + individualOutput(quantity) + (slope ? "" : "" ) + "\n";
        /*
        System.out.println(this.name );
        System.out.println("Marginal " + (slope ? "cost": "benefit") + ": " + marginalValue( quantity) );
        System.out.println("Individual " + (slope ? "producer": "consumer") + ": " + surplus( quantity) );
        System.out.println("Total " + (slope ? "cost": "benefit") + ": " + totalValue( quantity) );
        System.out.println("Individual " + (slope ? "Production": "Consumption") + " " + individualOutput( quantity));
        */
    }

    @Override
    public String toString()
    {
       return "Individual " + (super.slope ? "supply" : "demand") + " curve for " + this.name + ": " + super.toString();
    }
}
