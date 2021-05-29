package econsections;

import java.io.Serializable;

public class LinearCurveToo extends Curve implements Serializable {
    
    private String name;
    private double marginalValue;
    private double surplus;
    private double total;
    private double output;
    private String unitOfMeasurement;
    protected LinearCurve leftLine;
    protected LinearCurve rightLine;
    
    LinearCurveToo()
    {
        this(0,0, "", false, false, "$");
    }
    
    LinearCurveToo(String name)
    {
        this.leftLine = null;
        this.rightLine = null;
        
        this.name = name;
    }
    
    LinearCurveToo(double a, double b, String name, boolean slope, boolean bounded)
    {
        this(a,b,name, slope, bounded, "$");
    }
    
    LinearCurveToo(double a, double b, String name, boolean slope, boolean bounded, String unitOfMeasurement)
    {
        super(a, b, slope);
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.leftLine = null;
        this.rightLine = null; 
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
    
    public String surplus(int quantity)             // consumer/producer
    {
        return "Individual " + (slope ? "producer": "consumer") + ": " + Math.pow(quantity, 2) / 2.0 * super.getB() + "\n";
    }
    
    public String totalValue(int quantity)          //Total cost/benefit
    {
        double mult = 1;
        if(slope)
            mult *= (-1);
        
        return "Total " + (slope ? "cost": "benefit") + ": " + ( super.getB() / 2.0 * quantity + getPrice(quantity) ) * quantity * mult + "\n";
    }
    
    public String individualOutput(int quantity)      //Revenue/Expenditure
    {
        return (slope ? "Revenue": "Expenditure") + ": " + quantity * getPrice(quantity) + "\n";
    }

   public String print(int quantity)
    {
        return (slope ? "Consumption": "Production") + " statistics for " + this.name + " --\n" + marginalValue(quantity) + surplus(quantity) + totalValue(quantity) + individualOutput(quantity) + (slope ? "" : "" ) + "\n";
        /*
        public void print(
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