// import java.util.Scanner;

// class Process {
//     int pid; // Process ID
//     int bt; // Burst Time
//     int art; // Arrival Time
//     int ct; // Completion Time

//     public Process(int pid, int bt, int art) {
//         this.pid = pid;
//         this.bt = bt;
//         this.art = art;
//     }
// }

// public class SRTF {
//     // Method to find the waiting time, turn around time, and completion time for all processes
//     static void findWaitingTime(Process proc[], int n, int wt[]) {
//         int rt[] = new int[n];

//         // Copy the burst time into rt[]
//         for (int i = 0; i < n; i++)
//             rt[i] = proc[i].bt;

//         int complete = 0, t = 0, minm = Integer.MAX_VALUE;
//         int shortest = 0, finish_time;
//         boolean check = false;

//         // Process until all processes get completed
//         while (complete != n) {

//             // Find process with minimum remaining time among the
//             // processes that arrive till the current time
//             for (int j = 0; j < n; j++) {
//                 if ((proc[j].art <= t) && (rt[j] < minm) && rt[j] > 0) {
//                     minm = rt[j];
//                     shortest = j;
//                     check = true;
//                 }
//             }

//             if (check == false) {
//                 t++;
//                 continue;
//             }

//             // Reduce remaining time by one
//             rt[shortest]--;

//             // Update minimum
//             minm = rt[shortest];
//             if (minm == 0)
//                 minm = Integer.MAX_VALUE;

//             // If a process gets completely executed
//             if (rt[shortest] == 0) {
//                 // Increment complete
//                 complete++;
//                 check = false;

//                 // Find finish time of the current process
//                 finish_time = t + 1;

//                 // Calculate waiting time
//                 wt[shortest] = finish_time - proc[shortest].bt - proc[shortest].art;

//                 if (wt[shortest] < 0)
//                     wt[shortest] = 0;

//                 // Set completion time
//                 proc[shortest].ct = finish_time;
//             }
//             // Increment time
//             t++;
//         }
//     }

//     // Method to calculate average time
//     static void findavgTime(Process proc[], int n) {
//         int wt[] = new int[n], tat[] = new int[n];
//         int total_wt = 0, total_tat = 0;

//         // Function to find waiting time, turn around time, and completion time of all processes
//         findWaitingTime(proc, n, wt);

//         // Function to calculate turn around time for all processes
//         findTurnAroundTime(proc, n, wt, tat);

//         // Display processes along with all details
//         System.out.println("Processes\tBurst time\t\tArrival time\t  \tWaiting time\t   \tTurnaround time\t    Completion time");

//         // Calculate total waiting time and total turnaround time
//         for (int i = 0; i < n; i++) {
//             total_wt = total_wt + wt[i];
//             total_tat = total_tat + tat[i];
//             System.out.println(proc[i].pid + "\t\t\t" + proc[i].bt + "\t\t\t" + proc[i].art + "\t\t\t" + wt[i] + "\t\t\t" + tat[i] + "\t\t\t" + proc[i].ct);
//         }

//         System.out.println("\nAverage waiting time = " + (float) total_wt / (float) n);
//         System.out.println("Average turnaround time = " + (float) total_tat / (float) n);
//     }

//     // Method to calculate turn around time
//     static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {
//         // calculating turnaround time by adding bt[i] + wt[i]
//         for (int i = 0; i < n; i++)
//             tat[i] = proc[i].bt + wt[i];
//     }

//     // Driver Method
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of processes: ");
//         int n = scanner.nextInt();
//         Process[] proc = new Process[n];

//         // Input arrival time and burst time for each process
//         for (int i = 0; i < n; i++) {
//             System.out.println("Enter details for Process " + (i + 1));
//             System.out.print("Arrival Time: ");
//             int arrivalTime = scanner.nextInt();
//             System.out.print("Burst Time: ");
//             int burstTime = scanner.nextInt();
//             proc[i] = new Process(i + 1, burstTime, arrivalTime);
//         }

//         findavgTime(proc, proc.length);
//     }
// }

// import java.util.*;

// public class SRTF {
//     public static void main(String args[]) {
//         Scanner sc = new Scanner(System.in);
//         System.out.println("enter no of process:");
//         int n = sc.nextInt();
//         int pid[] = new int[n]; // it takes pid of process
//         int at[] = new int[n]; // at means arrival time
//         int bt[] = new int[n]; // bt means burst time
//         int ct[] = new int[n]; // ct means complete time
//         int ta[] = new int[n];// ta means turn around time
//         int wt[] = new int[n]; // wt means waiting time
//         int f[] = new int[n]; // f means it is flag it checks process is completed or not
//         int k[] = new int[n]; // it is also stores brust time
//         int i, st = 0, tot = 0;
//         float avgwt = 0, avgta = 0;

//         for (i = 0; i < n; i++) {
//             pid[i] = i + 1;
//             System.out.println("enter process " + (i + 1) + " arrival time:");
//             at[i] = sc.nextInt();
//             System.out.println("enter process " + (i + 1) + " burst time:");
//             bt[i] = sc.nextInt();
//             k[i] = bt[i];
//             f[i] = 0;
//         }

//         while (true) {
//             int min = 99, c = n;
//             if (tot == n)
//                 break;

//             for (i = 0; i < n; i++) {
//                 if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
//                     min = bt[i];
//                     c = i;
//                 }
//             }

//             if (c == n)
//                 st++;
//             else {
//                 bt[c]--;
//                 st++;
//                 if (bt[c] == 0) {
//                     ct[c] = st;
//                     f[c] = 1;
//                     tot++;
//                 }
//             }
//         }

//         for (i = 0; i < n; i++) {
//             ta[i] = ct[i] - at[i];
//             wt[i] = ta[i] - k[i];
//             avgwt += wt[i];
//             avgta += ta[i];
//         }

//         System.out.println("pid  arrival  burst  complete turn waiting");
//         for (i = 0; i < n; i++) {
//             System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
//         }

//         System.out.println("\naverage tat is " + (float) (avgta / n));
//         System.out.println("average wt is " + (float) (avgwt / n));
//         sc.close();
//     }
// }


/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class SRTF
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt(); //no. of process
		int pid[]=new int[n];
		int at[]=new int[n];
		int bt[]=new int[n];
		int ct[]=new int[n];
		int wt[]=new int[n];
		int tat[]=new int[n];
		int f[]=new int[n];
		int k[]=new int[n];  //this will also store burst time
		int i,st=0,tot=0;
		
		for(i=0;i<n;i++)
		{
			pid[i]=i+1;
			at[i]=sc.nextInt();
			bt[i]=sc.nextInt();
			f[i]=0;
			k[i]=bt[i];
		}
		
		while(true)
		{
			int min=99,c=n;
			if(tot==n) break;
			
			for(i=0;i<n;i++)
			{
				if((at[i]<=st) && (f[i]==0) && (bt[i]<min))
				{
					min=bt[i];
					c=i;
				}
			}
			
			if(c==n)
			{
				st++;
			}
			
			else
			{
				bt[c]--;
				st++;
				if(bt[c]==0)
				{
					ct[c]=st;
					f[c]=1;
					tot++;
				}
			}
		}
		
		int totalwt=0,totaltat=0;
		
		for(i=0;i<n;i++)
		{
			tat[i]=ct[i]-at[i];
			wt[i]=tat[i]-k[i];
			totalwt+=wt[i];
			totaltat+=tat[i];
		}
		float avgwt=(float)totalwt/n;
		float avgtat=(float) totaltat/n;
		
		for(i=0;i<n;i++)
		{
		System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
		}
		
		System.out.println(avgwt);
		System.out.println(avgtat);
		
		
	}
}