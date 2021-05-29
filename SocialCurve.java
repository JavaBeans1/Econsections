package econsections;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Scanner;

public class SocialCurve implements Serializable {

    //private LinearCurve rootLine;
    private ArrayList<LinearCurve> lines; 
    int numOfLines;
    String title;
    private double marginalSocialValue;
    private double marketSurplus;
    private double totalSocialValue;
    private double marketOutput;
    private boolean slope;
    private int interval;      //Interval of common increase or decrease in quantity for Linear comparison
    private double ext; 
    
    SocialCurve()
    {
        this(null);
    }
    
    SocialCurve(ArrayList<LinearCurve> lines)
    {
        this.lines = lines;
    }
    
    SocialCurve(ArrayList<LinearCurve> lines, boolean slope)
    {
        this.lines = lines;
        this.slope = slope;
    }
    
    SocialCurve(int numOfLines, String title, boolean slope)
    {
        this(numOfLines, title, slope, 0, 0.0);
    }
    
    SocialCurve(int numOfLines, String title, boolean slope, int interval, double ext)
    {
        this.title = title;
        this.numOfLines = numOfLines;
        this.slope = slope;
        this.interval = interval;
        this.ext = ext;
        lines = new ArrayList<LinearCurve>();
    }
    
    public void add(LinearCurve line)
    {
        lines.add(line);
    }
    
    @Override
    public String toString() {
        return "SocialCurve{" + "lines=" + lines + ", numOfLines=" + numOfLines + '}';
    }
    
    public ArrayList<LinearCurve> getLines() {
        return lines;
    }

    public int getNumOfLines() {
        return numOfLines;
    }

    public String getTitle() {
        return title;
    }
    
    public double marginalSocialValue(int quantity)
    {
        if(quantity < interval * numOfLines)
            return 0.0;
        
        int count = 0;
        for(LinearCurve line: this.lines)
            if((count++) == numOfLines / 2)
                marginalSocialValue = line.getPrice(quantity/numOfLines);
                
        return marginalSocialValue;
    }
    
    public double marketSurplus(int quantity)
    {   
        double pr = marginalSocialValue(quantity);
        if( (this.slope && (pr > this.ext) ) || ( !this.slope && (pr < this.ext)) )
            return 0.0;
        
        quantity /= this.numOfLines;
        quantity -= ( (this.numOfLines-1) / 2 * this.interval );
       
        this.marketSurplus = 0.0;
                
        for(LinearCurve line: this.lines)
        {
            this.marketSurplus += (line.surplusAt(quantity));
            quantity += 5;
        }
        
        return marketSurplus;
    }
    
    public double totalSocialValue(int quantity)
    {
        int mult = 1;
        if(this.slope)
            mult *= (-1);
        
        totalSocialValue = quantity * marginalSocialValue(quantity) + marketSurplus(quantity) * mult;   
        return totalSocialValue;
    }
    
    public double marketOutput(int quantity)
    {
        this.marketOutput = quantity * marginalSocialValue(quantity);
        return this.marketOutput;
    }
    
    public void print(int quantity)
    {
        //return 
        System.out.println("Market: " + this.title);
        System.out.println("Point of Interest: " + quantity);
        System.out.println("Marginal social " + (this.slope ? "cost": "benefit") + ": " + marginalSocialValue(quantity));
        System.out.println("Market " + (this.slope ? "producer": "consumer") + " surplus: " + marketSurplus(quantity));
        System.out.println("Total social " + (this.slope ? "cost": "benefit") + ": " + totalSocialValue(quantity));
        System.out.println("Market " + (this.slope ? "Production Revenue": "Consumption Expenditure") + ": " + marketOutput(quantity));
    }
    
    public void print()                 //Linear traversal Required
    {
        for(LinearCurve line: lines)
            System.out.println(line);   
    }
    
    public void print(String name)      //Linear traversal for iteration
    {
        for( LinearCurve Line: lines)
            if(name.equals( Line.getName() ) )
                System.out.println( Line );
    }
    
    public void prompt(int choice, String name, int quantity)
    {
        switch(choice)
        {
            case 1:
                for( LinearCurve Line: getLines() )
                    if(name.equals( Line.getName() ) )
                        System.out.println( Line.print(quantity) );
                
                break;
            
            case 2:
                for(LinearCurve line: getLines())
                    System.out.println(line.print(quantity));
                
                break;
                
            case 3:
                print(quantity);
                break;
                
            default:
                System.out.println("Sorry! There is an error in your choice of input. ");
        }
        //Report Format#1 Ends
        System.out.println();
    }
    
    /*
    public void insert(LinearCurve line)
    {
        this.rootLine = insert(rootLine, line);
    }
    
    public LinearCurve insert(LinearCurve rootLine, LinearCurve line)
    {
        if(rootLine == null)
            rootLine = line;
        else
        {
            if(line.getB() < rootLine.getB())
                rootLine.leftLine = line;
            else
                rootLine.rightLine = line;
        }
        return rootLine;
    }
    
    public void inorder()
    {
        inorder(rootLine);
    }
   
    private void inorder(LinearCurve rLine)
    {
        if(rLine != null)
        {
            inorder(rLine.leftLine);
            System.out.println("Line: " + rLine);
            inorder(rLine.rightLine);
        }
    }
    
    public void postorder()
    {
        postorder(rootLine);
    }
    
    private void postorder(LinearCurve rLine)
    {
        if(rLine != null)
        {
            postorder(rLine.leftLine);
            postorder(rLine.rightLine);
            System.out.println("Line: " + rLine);
        }
    }
    
    public void preorder()
    {
        preorder(rootLine);
    }
    
    private void preorder(LinearCurve rLine)
    {
        System.out.println("Line: " + rLine);
        postorder(rLine.leftLine);
        postorder(rLine.rightLine);
    }
    */
}
