/*
 * Name: Siddharth Pathak	
 * Student Number: V00876495
 */

public class IntegerLinkedList implements IntegerList
{   
	IntegerNode head;
	IntegerNode tail;
	int count;
	
	public IntegerLinkedList()
	{
		head=null;
		tail=head;
		count=0;

	}

	/*
	 * PURPOSE:
	 *   Add the element x to the front of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addFront(9) returns, then l is {9,1,2,3}.
	 * If l is {} and l.addFront(3) returns, then l is {3}.
	 */
	public void addFront (int x)
	{IntegerNode a=new IntegerNode(x);
		if(head==null){
			
			head=a;
			tail=head;
			head.next=null;
			tail.prev=null;
			count++;
		}

		else{
		
		a.next=head;
		head=a;
		a.next.prev=a;
		head.prev=null;		
		count++;
		}
		
	}
	
	/*
	 * PURPOSE:
	 *   Add the element x to the back of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addBack(9) returns, then l is {1,2,3,9}.
	 * If l is {} and l.addBack(9) returns, then l is {9}.
	 */
	public void addBack (int x)
	{	IntegerNode a=new IntegerNode(x);
		if(head==null){
			
			head=a;
			tail=head;
			head.next=null;
			tail.prev=null;
			count++;
		}
		
		else{
						
			tail.next=a;
			a.next=null;
			a.prev=tail;
			tail=a;
			count++;
			
		}
		
		
		
		
	}

	/*
	 * PURPOSE:
	 *   Add the element x at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for addAt are
	 *   0, 1, 2, 3.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos <= l.size()
	 *
	 * Examples:
	 *
	 * If l is {} and l.addAt(9,0) returns, then l is {9}.
	 * If l is {1} and l.addAt(9,0) returns, then l is {9,1}.
	 * If l is {1,2} and l.addAt(9,1) returns, then l is {1,9,2}
	 * If l is {1,2} and l.addAt(9,2) returns, then l is {1,2,9}
	 */
	public void addAt (int x, int pos)
	{
		IntegerNode a=new IntegerNode(x);
		if(head==null&&pos==0){
		    head=a;
			tail=head;
			count++;		
		}
		else if(head!=null&&pos==0){
			addFront(x);
		}
		
		else if(pos==(count)){
		    addBack(x);
			
		}
		
		else{
			IntegerNode b=head;
			for(int i=0;i<pos-1;i++,b=b.next){
			}
			
		  	a.next=b.next;
			a.prev=b;
			a.next.prev=a;
			b.next=a;
            count++;		
		}
		
	}

	/*
	 * PURPOSE:
	 *	Return the number of elements in the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {7,13,22} l.size() returns 3
	 *	If l is {} l.size() returns 0
	 */
	public int size()
	{
		return count;
	}

	/*
	 * PURPOSE:
	 *   Return the element at position pos in the list.
	 *
	 * PRECONDITIONS:
	 *	pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *	If l is {67,12,13} then l.get(0) returns 67
	 *	If l is	{67,12,13} then l.get(2) returns 13
	 *	If l is {92} then the result of l.get(2) is undefined.
	 *
	 */
	public int  get (int pos)
	{   IntegerNode a=head;
	    for(int i=0;i<pos;i++){
		a=a.next;		
		}
		return a.value;
	}

	/*
	 * PURPOSE:
	 *   Remove all elements from the list.  After calling this
	 *   method on a list l, l.size() will return 0
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13} then after l.clear(), l is {}
	 *	If l is {} then after l.clear(), l is {}
	 *
	 */
	public void clear()
	{
		head=null;
		tail=head;
		count=0;

	}

	/*
	 * PURPOSE:
	 *   Remove all instances of value from the list.
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13,12} then after l.remove(12), l is {67,13}
	 *	If l is {1,2,3} then after l.remove(2), l is {1,3}
	 *	If l is {1,2,3} then after l.remove(99), l is {1,2,3}
	 */
	public void remove (int value)
	{IntegerNode b;
	 for(b=head;b!=null;b=b.next){
		if(b.value==value){
		if(b==head){
			head=head.next;
			count--;
			//head.next.prev=null;
		}
	  
		else if(b.next==null){
			tail=tail.prev;
			tail.next=null;
			count--;
		}
	  
		else {
			
			b.prev.next=b.next;
			b.next.prev=b.prev;
			count--;
			}
		  }	
		}
	
	}

	/*
	 * PURPOSE:
	 *   Remove the element at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for removeAt are
	 *   0, 1, 2.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *
	 * If l is {1} and l.removeAt(0) returns, then l is {}.
	 * If l is {1,2,3} and l.removeAt(1) returns, then l is {1,3}
	 * If l is {1,2,3} and l.removeAt(2) returns, then l is {1,2}
	 */
	public void removeAt (int pos)
	{IntegerNode b=head;
	  if(pos==0){
		head=head.next;
		count--;
		//head.next.prev=null;
	  }
	  
	  else if(pos==count-1){
	    tail=tail.prev;
		tail.next=null;
		count--;
	  }
	  
	  else {
		for(int i=0;i<pos;i++,b=b.next){
		}	
		b.prev.next=b.next;
		b.next.prev=b.prev;
		count--;
	  }
	}

	/*
	 * PURPOSE:
	 *	Return a string representation of the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {1,2,3,4} then l.toString() returns "{1,2,3,4}"
	 *	If l is {} then l.toString() returns "{}"
	 *
	 */
	public String toString()
	{   StringBuilder sb=new StringBuilder(count*100);
		sb.append("{");
		IntegerNode a=head;
		while(a!=null){
			sb.append(a.value);
			if(a.next!=null)
			sb.append(",");
			a=a.next;
		}
		sb.append("}");
		
		return sb.toString();
	}
}
