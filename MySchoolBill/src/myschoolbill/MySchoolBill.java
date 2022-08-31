package myschoolbill;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * CSC 251
 * Lourdes Linares
 * 8.22.2022
 * This is a calculator that computes the costs of your classes based on the 
 * credits you are taking, the ACTFE Activity Fee, TECFE Technology Fee, and
 * CAPSF Required Fee for Campus Access
 */
public class MySchoolBill
{
    private static String allInfo;
    
    public static void main(String[] args)
    {
        String input;
        String studentName;
        String inOrOut;
        double numHours;
        final double crhrRate = 76.0;
        final double crhrRateOS = 268.0; //out of state
        final double ACTFE = 35.0;
        final double CAPSF = 15.0;
        final double TECFE = 16.0;
        double tuitionRawTotal;
        double fees;
        double totalBill;        
        //Get name
        studentName = JOptionPane.showInputDialog("Enter your name: ");        
        //Ask how many credit hours you are taking        
        input = JOptionPane.showInputDialog("Enter number of credit hours: ");        
        //Store number of credit hours in variable
        numHours = Double.parseDouble(input);
        // if credit hours are above 16, don't count any extra credits
        if(numHours > 16)
        {
            numHours = 16;
        }
        else 
        {
            numHours = Double.parseDouble(input);
        }        
        inOrOut = JOptionPane.showInputDialog("Are you in state? (y/n)");                
        //Credit Hour rate is $76.00/hr create const variable TOP OF PAGE DONE
        
        // <editor-fold desc="Make this into a method to add this all together and return Fees cost"> BOTTOM OF PAGE DONE
        //ACTFE Activity Fee is $35.00 const var
        //CAPSF Required Fee for Campus Access is $15.00 const var
        //TECFE Technology Fee is $16.00 const var
        //return value as fees
        // </editor-fold>
        fees = AddFees(ACTFE, CAPSF, TECFE);        
        //Make this into a method BTTOM OF PAGE DONE
        //Multiply number of credit hours by 76
        // return value as raw total
        if (inOrOut.equalsIgnoreCase("y"))
        {
         tuitionRawTotal = TuitionTotal(crhrRate, numHours);
        }
        else 
        {
            tuitionRawTotal = TuitionTotal(crhrRateOS, numHours);
        }
        //Make method BOTTOM OF PAGE DONE
        //Add the Fees to the product of tuition
        
        //Call TuitionTotal(double crhrRate, double numHours) and AddFees(double ACTFE, double CAPSF, double TECFE) DONE
        totalBill = FeesAndTuition(tuitionRawTotal, fees);        
        //Make display method
        //Display Total of Credit Hours cost alone
        //Display Fees 
        //Display Total Bill
        //Print to .txt document        
        allInfo = "Hello " + studentName + ", this is your itemized bill:\r\n" + 
                "----------------------------------------------------------------" + " \r\n" + 
                "Credit Hour Charge for " + numHours + " Credit Hours: $" + 
                tuitionRawTotal + "0" + "\r\n" + "Credit Hour Rate: $" + crhrRate + "0" + " \r\n" + 
                "ACTFE Activity Fee: $" + ACTFE + "0" + " \r\n" +
                "CAPSF Required Fee for Campus Access: $"+ CAPSF + "0" + "\r\n" +
                "TECFE Technology Fee: $" + TECFE + "0" + " \r\n" +
                "==============================================" + " \r\n" +
                "Your Total Bill: $" + totalBill + "0" + " \r\n";        
        try 
        {
            JOptionPane.showMessageDialog(null, allInfo);
            printSchoolCosts(studentName);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MySchoolBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public static double FeesAndTuition(double tuitionRawTotal, double fees)
    {
        double totalCost = tuitionRawTotal + fees;
        return totalCost;
    }    
    public static double TuitionTotal(double crhrRate, double numHours)
    {
        double tuition = crhrRate * numHours;        
        return tuition;
    }    
    public static double AddFees(double ACTFE, double CAPSF, double TECFE)
    {
        double totalFees = ACTFE + CAPSF + TECFE;
        return totalFees;
    }               
    public static void printSchoolCosts(String studentName) throws IOException
    {
        try (PrintWriter outputFile = new PrintWriter(studentName + ".txt")) {
            outputFile.println(allInfo);
        }
    }
}
