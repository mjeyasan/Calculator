/*This class contains the Node object that stores digits of 
 * a Number*/
public class Node
 {
   protected Node prev;
   protected Node next;
   protected int data;
   
   public Node()
   {
     this.prev=null;
     this.next=null;
     this.data=0;
   }
   //set data, previous node, and next node
   public Node(int data, Node prev, Node next)
   {
     this.data=data;
     this.prev=prev;
     this.next=next;
   }
}
   