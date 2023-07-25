/* package whatever; // don't place package name! */
//CSCAN AND SCAN DONO H YE TODA SA HI ANTAR HA DONO M

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Practice
{
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc=new Scanner(System.in);
	   // System.out.println("Enter the headposition ");
	    int headposition=sc.nextInt();
	    int n=sc.nextInt();
	    ArrayList<Integer> left=new ArrayList<>();
	    ArrayList<Integer> right=new ArrayList<>();
	    int total_seek=0;
	    for(int i=0;i<n;i++)
	    {
	    	int num=sc.nextInt();
	    	if(num<=headposition) left.add(num);
	    	else right.add(num);
	    }
	    
	    String dir=sc.next().toLowerCase();
	    if(dir.equals("left"))
	    {
	    	left.add(0);
	    	Collections.sort(left,Collections.reverseOrder());
	    	
	    	for(int j=0;j<left.size();j++)
	    	{
	    		total_seek+=Math.abs(headposition-left.get(j));
	    		headposition=left.get(j);
	    	}
            right.add(200);
            Collections.sort(right,Collections.reverseOrder());
	    	for(int j=0;j<right.size();j++)
	    	{
	    		total_seek+=Math.abs(headposition-right.get(j));
	    		headposition=right.get(j);
	    	}
	    	
	    	System.out.println("seek count"+total_seek);
	    	System.out.println("thorughput "+(float)n/total_seek);
	    	
	    }
	    else if(dir.equals("right"))
	    {
	    	right.add(200);
	    	
	    	Collections.sort(right);
	    	for(int j=0;j<right.size();j++)
	    	{
	    		total_seek+=Math.abs(headposition-right.get(j));
	    		headposition=right.get(j);
	    	}
            left.add(0);
            Collections.sort(left);
	    	for(int j=0;j<left.size();j++)
	    	{
	    		total_seek+=Math.abs(headposition-left.get(j));
	    		headposition=left.get(j);
	    	}
	    	
	    	System.out.println("seek count"+total_seek);
	    	System.out.println("thorughput "+(float)n/total_seek);
	    	
	    }
	    else
	    {
	    	System.out.println("Invalid Input");
	    	return;
	    }
	}
}