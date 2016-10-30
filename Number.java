import java.util.*;
import java.io.*;
/*This class contains the operations that can be performed 
 * on Number objects. It implements Comparable and makes use of 
 * a DigiList to keep track of the digits*/
public class Number implements Comparable<Number>
{
  
   protected DigitList number= new DigitList(); //stores the Number's 
   protected int digitCount=0;
   protected int decimalPlaces=0;
   protected boolean negative=false;
   protected Node high; //points to high-order digit
   protected Node low; //points to low-order digit

 //intialize variables
 public Number()
 {
   
   this.digitCount=0;
   this.decimalPlaces=0;
   this.negative=false;
   this.high=number.high;
   this.low=number.low;
  
 }
 
 public Number(String str)throws Exception
 { 
   this.digitCount = 0;
  this.decimalPlaces = 0;
  this.negative = false;
  this.high=number.high;
  this.low=number.low;
  this.validate(str);  
 }
 
 /*Ensures that the input the the constructor
  * is valid*/
 public void validate(String str) throws Exception
 {
   int i;
   String str_new = str.trim();
   boolean val=true;
   boolean hasDP=false;
    for(i = str_new.length() - 1; i >= 0; i--)
      { 
        if (str_new.charAt(i) >= '0' && str_new.charAt(i) <= '9') 
          {
            val=true;
          }
        else if (str_new.charAt(i) == '-')
          {
            if(i == 0)
               val=true;
            else
          {
            val=false;
            //negative sign is improper location
            throw new Exception("INVALID INPUT");
          }
         }  
        else if (str_new.charAt(i)== '.' && !hasDP)
          {
            hasDP = true;
            val=true;
          }
        //multiple decimal points
        else if (str_new.charAt(i)== '.' && hasDP)
          {
            throw new Exception("INVALID INPUT");    
          }
        else 
           throw new Exception("INVALID INPUT");
      }
     if(val==true)
       accept(str);
  }
    
 /*Builds a list representation of the number 
  * represented by the string.*/
 public void accept(String str) throws Exception
 {
    int i;
    boolean hasDecimalPoint = false;
    String str_new = str.trim();
   
        for(i = str_new.length() - 1; i >= 0; i--)
      { 
        if (str_new.charAt(i) >= '0' && str_new.charAt(i) <= '9') 
        {
            int numm=str_new.charAt(i)-48;
           
            number.addFirst(numm);
      
            digitCount++;
       }
     else if (str_new.charAt(i) == '-')
     {
      if(i == 0)
        this.negative = true;
      }
     //first instance of decimal point
     else if (str_new.charAt(i)== '.' && !hasDecimalPoint)
     {
       this.decimalPlaces = digitCount;
       hasDecimalPoint = true;
     }
   }
   if ( hasDecimalPoint && (digitCount == decimalPlaces) )
   {
     number.addFirst(0);
     digitCount++;
   }
   trim();
  }
 

 /*Returns a Number which represents "this + n".*/
 public Number add(Number n)
 {
   //determines which operation is necessary
   //based on sign and magnitude
   int num= this.compareToAbsolute(n);
   Number x= new Number();
   if(this.negative && n.negative)
   {
     x=this.addAbsolute(n);
     //x.reverseSign();
     return x;
   }
   if(this.negative && !n.negative && num<0)
   {
     this.reverseSign();
     x=n.subtractAbsolute(this);
     return x;
   }
   if(this.negative && !n.negative && num>0)
   {
     x= this.subtractAbsolute(n);
     x.reverseSign();
     return x;
   }
   
   if(this.negative && n.negative && num==0)
   {
     x= this.addAbsolute(n);
     x.reverseSign();
     return x;
   }
   
   if(!this.negative && n.negative && num<0)
   {
     x=n.subtractAbsolute(this);
     x.reverseSign();
     return x;
   }
   
   if(!this.negative && n.negative && num>0) 
     return this.subtractAbsolute(n);
  
   if(!this.negative && n.negative && num==0)
     return this.subtractAbsolute(n);
   
   if(this.negative && !n.negative && num==0)
   {
     x=this.subtractAbsolute(n);
     x.reverseSign();
     return x;
   }
     return this.addAbsolute(n);
 }
 
 /*private method that adds absolute values*/
 private Number addAbsolute(Number n)
 {
    if(n.decimalPlaces>this.decimalPlaces)
         {
           int j=0;
           while(j<n.decimalPlaces)
           {
             number.addLast(0);
             digitCount++;
             decimalPlaces++;
             j++;
           }
         }
    
    int numm=digitCount-decimalPlaces;
    int num2= n.digitCount-n.decimalPlaces;
    
    //add leading zeroes if necessary
    if(numm<num2)
    {
      int k=0;
      while(k<num2)
      {
        number.addFirst(0);
        digitCount++;
        k++;
      }
    }
   
   Number sum = new Number();
   int carry = 0;
   Node thisPtr = number.low;
   Node nPtr = n.number.low;
   Node temp = new Node();
   int newDigit = 0;
   
   
   if(n.decimalPlaces > this.decimalPlaces)
     {
       sum.decimalPlaces = n.decimalPlaces;
       temp = nPtr;
     }
   else
     {
       sum.decimalPlaces = this.decimalPlaces;
       temp = thisPtr;
     }
   
   int num_places = Math.abs(n.decimalPlaces - this.decimalPlaces);
   
   while(num_places > 0)
   {
    sum.number.addFirst(temp.data);
    temp = temp.prev;
    num_places--;
   }
   
   if(n.decimalPlaces > this.decimalPlaces)
    nPtr = temp;
   else
    thisPtr = temp;
   
   //iterate through both numbers
   while(thisPtr!= null)
   {
    
    if(nPtr != null)
    {
      newDigit=thisPtr.data+nPtr.data+carry;
   
    }
    else
    {
      newDigit=thisPtr.data+carry;
    }
   
    sum.number.addFirst(newDigit%10);
    sum.digitCount++;
    carry = newDigit / 10;
    thisPtr=thisPtr.prev;
    if(nPtr != null)
      nPtr=nPtr.prev;
   }
  
   while(nPtr != null)
   {
     newDigit=thisPtr.data+carry;
     sum.number.addFirst(newDigit%10);
     sum.digitCount++;
     carry = newDigit / 10;
     nPtr=nPtr.prev;
     
   }
   // add carry to a node
   if ( carry != 0 ) {
     sum.number.addFirst(carry);
     sum.digitCount++;
   }
   
   int diff= sum.number.size-sum.decimalPlaces;
   
   if(negative)
    sum.negative = true;
  
  return sum;
 
 }
 
/*Method that determines which operation 
 * as well as which sign changes are necessary
 * based on input.Returns a Number which represents 
 * "this - n".*/
 public Number subtract(Number n)
 {
   int num= this.compareToAbsolute(n);
   Number x= new Number();
   if(this.negative && n.negative && num<0)
   {
     this.reverseSign();
     n.reverseSign();
     return n.subtractAbsolute(this);
   }
   if(this.negative && !n.negative && num<0)
   {
     x=n.addAbsolute(this);
     x.reverseSign();
     return x;
   }
   if(this.negative && !n.negative && num>0)
   {
     x= this.addAbsolute(n);
     return x;
   }
   
   if(this.negative && n.negative && num==0)
   {
     x= n.subtractAbsolute(this);
     return x;
   }
   
   if(!this.negative && n.negative && num<0)
   {
     x=this.addAbsolute(n);
     return x;
   }
   
   if(!this.negative && n.negative && num>0) 
   { 
     return this.addAbsolute(n);
   } 
   
   if(!this.negative && n.negative && num==0)
     return this.addAbsolute(n);
   
   if(this.negative && !n.negative && num==0)
   {
     x=this.addAbsolute(n);
     return x;
   }
   
   if(this.negative && n.negative && num>0)
   {
     this.reverseSign();
     n.reverseSign();
     x=this.subtractAbsolute(n);
     x.reverseSign();
     return x;
   }
   
   if(this.negative && n.negative && num<0)
   {
     this.reverseSign();
     n.reverseSign();
    return this.subtractAbsolute(n);
   }
   if(!this.negative && !n.negative && num<0)
   {
     x=n.subtractAbsolute(this);
     x.reverseSign();
     return x;
   }
     return this.subtractAbsolute(n);
 }
/*Subtract the absolute values of the 
 * digits contained in Number*/
 private Number subtractAbsolute(Number n)
 {
    //add trailing zeoroes if necessary
   if(n.decimalPlaces>this.decimalPlaces)
         {
           int j=0;
           while(j<n.decimalPlaces)
           {
             number.addLast(0);
             digitCount++;
             decimalPlaces++;
             j++;
           }
         }
   
   if(n.decimalPlaces>this.decimalPlaces)
         {
           int l=0;
           while(l<this.decimalPlaces)
           {
             n.number.addLast(0);
             digitCount++;
             decimalPlaces++;
             l++;
           }
         }
  
   int numm=this.digitCount-decimalPlaces;
   int num2= n.digitCount-n.decimalPlaces;
   //add leading zeroes if necessary
   if(num2<numm)
    {
      int k=0;
      while(k<numm)
      {
        number.addFirst(0);
        digitCount++;
        k++;
      }
    }
         Number difference = new Number();
         int borrow = 0;
         Node thisPtr = number.low;
         Node nPtr = n.number.low;
         Node temp=new Node();
         int newDigit = 0;
      
         if(n.decimalPlaces > this.decimalPlaces)
         {
            difference.decimalPlaces = n.decimalPlaces;
            temp = nPtr;
            
         }
         else
         {
            difference.decimalPlaces = this.decimalPlaces;
            temp = thisPtr;
         }
        
         int num1 = Math.abs(n.decimalPlaces - this.decimalPlaces);
        
         while(num1 > 0)
         {
            difference.number.addFirst(temp.data);
            temp = temp.prev;
            num1--;
         }
         //determine which pointer to use first
         if(n.decimalPlaces > this.decimalPlaces)
            nPtr = temp;
         else
            thisPtr = temp;
        
         while(thisPtr != null)
         {
            if(nPtr != null)
               newDigit = thisPtr.data - nPtr.data - borrow;
            else
               newDigit = thisPtr.data - borrow;
           
            if(newDigit<0)
            {
              newDigit+=10;
              borrow=1;
            }
            
            else
              borrow=0;
            
            difference.number.addFirst(newDigit%10);
            difference.digitCount++;
            thisPtr = thisPtr.prev;
            if(nPtr != null)
               nPtr = nPtr.prev;
         } 
         while(nPtr != null)
         {
            newDigit = thisPtr.data - borrow;
            
            if(newDigit<0)
            {
              newDigit+=10;
              borrow=1;
            }
            
            else
            borrow=0;
            
            difference.number.addFirst(newDigit%10);
            difference.digitCount++;
            nPtr = nPtr.prev;
         }
         if(negative)
            difference.negative = true;
  
  int diff= difference.number.size()-difference.decimalPlaces;
  //trim result
   while(difference.number.high.data==0 && diff>1 && difference.number.size>1)
   {
     difference.trim();
   }
   
   return difference;
   
}
/*Public compareTo methods*/
 public int compareTo(Number n)
 {
   if (this.negative && !n.negative)
       return -1;
   else if(!this.negative && n.negative)
      return 1;
   else
     //use compareToAbsolute is nums are of the same sign
     return this.compareToAbsolute(n); 
         
 }

 /*Private method that compares digits
  * stored in the Number objects*/
 private int compareToAbsolute(Number n)
 {
   int num1= digitCount-decimalPlaces;
   int num2= n.digitCount-n.decimalPlaces;
    if (num1 > num2) 
        return 1;
    
    if (num1 < num2) 
       return -1;
    //iterate       
    Node thisPtr = number.high;
    Node nPtr = n.number.high;
    for (int i = 0; i < num1; i++) 
    {
      if (thisPtr.data > nPtr.data) 
                return 1;
      else if (thisPtr.data < nPtr.data) 
                return -1;      
     }
     //iterate through decimal places if ints are same 
     while ( thisPtr.next != null && nPtr.next != null ) 
       {
           if(thisPtr.data>nPtr.data)
             return 1;
           else if(thisPtr.data<nPtr.data)
             return -1;
          thisPtr = thisPtr.next;
          nPtr = nPtr.next;
       }
     return 0;
 }
 
/*Returns a Number which represents "this * n".*/
public Number multiply(Number n)
{
         
   Number product = new Number(); //stores final result
   Node nPtr = n.number.high;
   int newDigit = 0;
   Node newNode;

 while (nPtr != null)
 {
    Number partialProduct = new Number();
    int carry=0;
    Node thisPtr = number.low;
    while (thisPtr != null)
    {
     // multiply the digits
      newDigit = (thisPtr.data * nPtr.data) + carry;
      carry = newDigit / 10;
      newDigit=newDigit%10;
      partialProduct.number.addFirst(newDigit);
      partialProduct.digitCount++;
      thisPtr = thisPtr.prev;
    }
   if (carry != 0)
    {
      partialProduct.number.addFirst(carry);
      partialProduct.digitCount++;
    }
  
   //insert a new node with 0 at the tail of product to multiply product by 10
   product.number.addLast(0);
   product.digitCount++;
   product = product.addAbsolute(partialProduct);
   nPtr = nPtr.next;
 
 }
 product.decimalPlaces = this.decimalPlaces + n.decimalPlaces;
 
//if product is negative
 if(this.negative || n.negative)
 {
      product.negative = true;
      return product;
  }

 return product;
}

/*Reverses the sign of the Number*/
public void reverseSign()
{
  String reversed="";
  if(this.negative=!this.negative)
    reversed="-"+reversed;
}

//Returns a String representation of the Number (so it can be displayed by System.out.print()).
public String toString()
{
 String result = "" ;
  trim();
  Node temp = number.low;
  int i = 0;
  
  while(temp != null)
  { 
   if(i != 0 && i == decimalPlaces)
    result = "." + result;
   
   result = temp.data + result;
   temp = temp.prev;
   i++;
  }   
 
  if(this.negative)
   result = "-" + result;
     
  if(result.indexOf("0")==0)
  {
    result=result.replace("0.",".");
  }
  return result; 
}
/*Trims the Number to get rid of leading and trailing
 * zeroes that are not needed*/
public void trim()
 {
  if(number.high.data==0 && (number.size()-decimalPlaces>1))
    number.removeFirst();
  
  for(int i = 0; i < decimalPlaces;)
  {
   if(number.low.data == 0)
   {
    number.low=number.low.prev;
    number.low.next=null; 
    decimalPlaces--;
    digitCount--;
   }
    else
    return;
  }
  
 }

}
