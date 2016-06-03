import java.util.*;
import java.io.*;
public class FrequencyCalculator
{
    public static double[] inductors = {1000,10000,100000,2200,22000,220000,3300,33000,330000,4700,47000,
                                    470000,5600,56000,560000,6800,68000,680000,8200,82000,820000,1000000};//inductor values in nH
    public static double[] capacitors = {4700,15000,120,56,180,68,27,4,47,560,140,220,2200,5,33000,6800,330,18,10000,82,22000,68000,
                                    1,15,820,22,1000,1500,100000,8,3300,6,470,39,47000,20000,3,2,9,50,30,10,100,33,2000,150,300,680,20};//capacitor values in pF
    public static void main(double[] args) throws IOException{        
        double frequency = args[0]*1000000;
        double range = args[1]*1000000;
        double upperBound = frequency+range;
        double lowerBound = frequency-range;
        for(int i = 0;i<inductors.length;i++){
            inductors[i] *= 1.0E-9;
        }
        for(int i = 0;i<capacitors.length;i++){
            capacitors[i] *= 1.0E-12;
        }
        System.out.println("Freq = " + frequency + ";Max = " + upperBound);
        for(int i = 0;i<inductors.length;i++){
            for(int c = 0; c<capacitors.length;c++){
                double curFreq = computeFreq(inductors[i],capacitors[c]);
                
                if(curFreq>=frequency && curFreq<=upperBound){
                //if(curFreq>.001){
                    System.out.print("Capacitor: " + (int)(capacitors[c]*1.0E12) + "pF,");
                    System.out.print("Inductor: " + (int)(inductors[i]*1.0E9) + "nH,");
                    System.out.println("Frequency: " + curFreq/1000000 + "MHz");
                /*
                }else{
                    System.err.print("Capacitor: " + capacitors[c] + "pF,");
                    System.err.print("Inductor: " + inductors[i] + "nH,");
                    System.err.println("Frequency: " + curFreq + "MHz");
                */
                }
            }
        }
        //out.close();
        System.out.println("Done");
    }
    public static double computeFreq(double inductance,double capacitance){
        return 1.0/(Math.sqrt(inductance * capacitance) * Math.PI * 2.0);
    }
    public static ArrayList<Integer> doublechecker(int[] inarr){
        ArrayList<Integer> doubles = new ArrayList<Integer>();
        int length = inarr.length;
        for(int checkingNow = 0;checkingNow<length;checkingNow++){
            for(int compareTo = 0; compareTo<length;compareTo++){
                if(checkingNow!=compareTo){
                    if(inarr[checkingNow]==inarr[compareTo]) doubles.add(inarr[checkingNow]);
                }
            }
        }
        return doubles;
    }
}
