import com.jaunt.*;
import com.jaunt.component.*;
import java.io.*;
import java.util.*;
/**
 * Write a description of class French here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class French
{
    public static void main(){
        Scanner in = new Scanner(System.in);
        String[] input;
        String noun;
        String verb;
        String language = "FR";
        while(true){
           while(true){
                try{
                System.out.println("Type noun + infinitive");
                input = in.nextLine().split(" ");
                if(input[0].equals("exit")) System.exit(0);
            
                    noun = input[0];
                    verb = input[1];
                    break;
                }catch(Exception e){
                    System.out.println("bad input");
                }
            }
            ArrayList<String> elems = new ArrayList<String>();
            
            
            String site = "http://www.wordreference.com/conj/" + language +"verbs.aspx?v=" + verb;
            try{
                UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
                userAgent.visit(site);                        //visit a url  
                Elements tables = userAgent.doc.findEach("<div class=\"aa\"").findEach("<tr>").findEach("<td>");       //find non-nested tables
                
                for(Element table : tables){                               //iterate through Results
                    String[] stuff = table.innerText().toString().split("\n");
                    elems.addAll(new ArrayList<String>(Arrays.asList(stuff)));
                    /*String stuff = table.outerHTML();      //print each element and its contents
                     String[] stuffArr = stuff.split("\n");
                     ArrayList<String> stuffList = new ArrayList<String>(Arrays.asList(stuffArr));
                     elems.addAll(stuffList);
                     */
                }
                
            }
            catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
                System.err.println(e);
            }
            /*
            for(int i=0;i<elems.size();i++)
                if(elems.get(i).contains("antiquated"))
                    elems.remove(i);
            for(int i=0;i<elems.size();i++){
                System.out.println(elems.get(i).getInnerText());
            }
            */
            int displace = 0;
            if(noun.equals("tu"))displace=1;
            if(noun.equals("il")||noun.equals("elle")||noun.equals("on"))displace=2;
            if(noun.equals("nous"))displace=3;
            if(noun.equals("vous"))displace=4;
            if(noun.equals("ils") || noun.equals("elles"))displace=5;
            int[] positions = {6,24,48,60};
            String[] names = {"imparfait:           ",
                              "passe compose:       ",
                              "subjonctif:          ",
                              "passe du subjonctif: "};
            try{
                for(int i=0;i<4;i++)
                    System.out.println(names[i] + " " + elems.get(positions[i]+displace));
            //System.out.println(elems); 
            }catch(Exception e){
                System.out.println("No such verb");
            }
        }
           
    }
}    
