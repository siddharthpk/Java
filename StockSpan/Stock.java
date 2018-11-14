/*
*	Siddharth Pathak 
*	V00876495
*	CSC225 Assignment_1
*/
import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;

public class Stock{

	public static int[] CalculateSpan(int[] p){
		
	int[] span = new int[p.length];

    for (int i = 0; i < p.length; i++) {
        int index = i - 1;
        span[i] = 1;
        while (index >= 0 && p[index] <= p[i]) {
            // previous member is the same or smaller price.
            // that member, plus all of its span, are also smaller.
            span[i] += span[index];
            // we can skip the span and check if the next span is smaller too.
            index -= span[index];
        }
    }
	
    return span;
	
	}
	public static int[] readInput(Scanner s){
		Queue<Integer> q = new LinkedList<Integer>();
		int n=0;
		if(!s.hasNextInt()){
			return null;
		}
		int temp = s.nextInt();
		while(temp>=0){
			q.offer(temp);
			temp = s.nextInt();
			n++;
		}
		int[] inp = new int[q.size()];
		for(int i=0;i<n;i++){
			inp[i]= q.poll();
		}
		return inp;
	}
	public static void main(String[] args){
		Scanner s;
        Stock m = new Stock();
        if (args.length > 0){
        	try{
        		s = new Scanner(new File(args[0]));
        	} catch(java.io.FileNotFoundException e){
        		System.out.printf("Unable to open %s\n",args[0]);
        		return;
        	}
        	System.out.printf("Reading input values from %s.\n", args[0]);
        }else{
        	s = new Scanner(System.in);
        	System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
        }
            
        int[] price = m.readInput(s);
        System.out.println("The stock prices are:");
        for(int i=0;i<price.length;i++){
        	System.out.print(price[i]+ (((i+1)==price.length)? ".": ", "));
        }

        if(price!=null){
        	int[] span = m.CalculateSpan(price);
        	if(span!=null){
        		System.out.println("The spans are:");
        		for(int i=0;i<span.length;i++){
        			System.out.print(span[i]+ (((i+1)==span.length)? ".": ", "));
        		}
        	}
        }
    }
}