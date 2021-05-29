package econsections;

public class Curve {
    
    private double a;
    private double b;
    boolean slope;
    
    Curve()
    {
        this(0, 0, false);
    }
    
    Curve(double newA, double newB, boolean newSlope)
    {
        this.a = newA;
        this.b = newB;
        this.slope = newSlope;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public boolean isSlope() {
        return slope;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setSlope(boolean slope){
        this.slope = slope;
    }
    
    public double getPrice(int quantity)
    {
        if(slope)
            quantity *= (-1);
        
        return this.a - quantity * this.b;
    }
    
    public double getQuantity(double price)
    {
        if(slope)
            return (price - this.a) / this.b;
        
        return this.a - price / this.b;
    }
    
    @Override
    public String toString() {
        return "p=" + a + (slope? "+" : "-") + b + "q";
    }
}
