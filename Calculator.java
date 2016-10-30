import java.util.*;
/*Gets user input and performs
 * the requested calculations.*/
public class Calculator{
  public static void main(String[] args)
  {
    Scanner input= new Scanner(System.in);
    Number num = new Number(); 
    Number result = new Number();
 //print initial message
  System.out.print("enter a value: e  add: a\nsubtract: s       multiply: m\nreverse sign: r   clear: c\nquit: q\n");

    
  String choice;
  choice = input.next();
  while(!choice.equals("q"))
  {
   //enter a value
   if(choice.equals("e"))
   {
     try
     {
    choice = getChoice("value: ");
    result = new Number(choice);
    System.out.println(result);
    }
    catch (Exception e)
    {
    }
   }
   // clear the calculator
   if(choice.equals("c"))
   {
     try
     {
    result = new Number("0");
    System.out.println(result);
     }
     catch(Exception e)
     {
     }
   }  
   // add two values together
   if(choice.equals("a"))
   {
 
    try{
     choice = getChoice("value: ");
     num = new Number(choice);
     result = result.add(num);
     System.out.println(result);
    }
    catch(Exception e)
    {System.out.println("ERROR!"); 
    }
   } 
   // reverse the sign 
   if(choice.equals("r"))
   {
    result.reverseSign();
    System.out.println(result);
   }
   
   //subtract a number from another
   if(choice.equals("s"))
   {
    
    try
    {
    choice = getChoice("value: ");
    num = new Number(choice);
    result = result.subtract(num);
    System.out.println(result); 
    }
    catch (Exception e)
    {
    }
   }    
   // multiply numbers together
   if(choice.equals("m"))
   {
     try
     {
    choice = getChoice("value: ");
    num = new Number(choice);
    result = result.multiply(num);
    System.out.println(result); 
    }
    catch(Exception e)
    {
    }
   }    
   // display the menu
   System.out.print("enter a value: e  add: a\nsubtract: s       multiply: m\nreverse sign: r   clear: c\nquit: q\n");
   choice = input.next();

  }
  System.exit(0);
  
  }
 // continue prompting user for choice until they press q
  private static String getChoice(String q)
    {
        System.out.print(q);
        return new Scanner(System.in).next();
    }
 
}
