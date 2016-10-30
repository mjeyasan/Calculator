import java.util.NoSuchElementException;
import java.util.ListIterator;
/*The DigiList manages the list of nodes that contain digits of 
 * a Number. NO dummy nodes were used. DigitList Iterator was not required 
 for my section, so I implemented the project without it*/
public class DigitList {
  protected Node high;//=new Node(0,null,null);  // reference to dummy node at the front
  protected Node low;//=new Node(0,null,null);  // reference to dummy node at the end
  protected int size;   // number of nodes
  protected int modCount=1;
  private boolean empty=true;
 
  // Workhorse constructor. Initialize variables: head and tail to dummy 
  // nodes, size to 0, modCount to 1. Initially, head.next references  
  // the tail node; tail.prev references the head node. An empty list
  // (having size 0) has only the two dummy nodes in it.
  DigitList(){
   high=low=new Node();
   size=0;
   modCount=1;
  }
 
  // Add digit x to the front of the list. 
  // Target Complexity: O(1)
  public void addFirst(int x){
   
    if(empty==true){
     high.data=low.data=x;
     empty=false;
    }
    else{
      Node newNode= new Node(x, null, high);
      high.prev=newNode;
      high=newNode;
    }
    size++;
  }
 
  // Returns the high-order digit (at the front of the list). 
  // Throws NoSuchElementException if the list is empty
  // Target Complexity: O(1)
  public int getFirst(){
    if(high==null)
      throw new NoSuchElementException();
    else
      return high.data;
  }
 
  // Removes the high-order digit at the front of the list. 
  // Throws NoSuchElementException if the list is empty
  // Target Complexity: O(1)
  public void removeFirst(){
    if(high==null)
      throw new NoSuchElementException();
    else{
      high=high.next;
      high.prev=null;
    }
    size--;
  }
 
  // Add digit x to the end of the list. 
  // Target Complexity: O(1)
  public void addLast(int x){
    if(low==null)
    {
      high=low=new Node(x, null, null);
      empty=false;
    }
    else{
      Node newNode= new Node(x, low, null);
      low.next=newNode;
      low=newNode;
    }
    size++;
  }
 
  // Returns the low-order digit (at the end of the list). 
  // Throws NoSuchElementException if the list is empty
  // Target Complexity: O(1)
  public int getLast(){
    if(high==null)
      throw new NoSuchElementException();
    else{
      return low.data;
    }
  }
 
  // Removes the low-order digit at the end of the list. 
  // Throws NoSuchElementException if the list is empty
  // Target Complexity: O(1)
  public void removeLast(){
    if(high==null)
      throw new NoSuchElementException();
    else{
      low=low.prev;
    }
    size--;
  }
 
  // Returns the current size of the list
  // Target Complexity: O(1)
  public int size(){
    return size;
  }

}