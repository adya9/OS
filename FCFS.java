import java.util.*;
 
public class FCFS {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of process: ");
        int num_p = sc.nextInt();
        int proc_id[] = new int[num_p];   // process ids
        int arrival_t[] = new int[num_p];     // arrival times
        int burst_t[] = new int[num_p];     // burst or execution times
        int comp_t[] = new int[num_p];     // completion times
        int turn_t[] = new int[num_p];     // turn around times
        int wait_t[] = new int[num_p];     // waiting times
        int temp;
        float avg_wt=0,avg_turn=0;
 
        for(int i = 0; i < num_p; i++)
        {
            System.out.println("enter process " + (i+1) + " arrival time: ");
            arrival_t[i] = sc.nextInt();
            System.out.println("enter process " + (i+1) + " brust time: ");
            burst_t[i] = sc.nextInt();
            proc_id[i] = i+1;
        }
            
//sorting according to arrival times //bubble sort 
        for(int i = 0 ; i <num_p; i++)
        {
            for(int  j=0;  j < num_p-(i+1) ; j++)
            {
                if( arrival_t[j] > arrival_t[j+1] )
                {
                    temp = arrival_t[j];
                    arrival_t[j] = arrival_t[j+1];
                    arrival_t[j+1] = temp;
                    temp = burst_t[j];
                    burst_t[j] = burst_t[j+1];
                    burst_t[j+1] = temp;
                    temp = proc_id[j];
                    proc_id[j] = proc_id[j+1];
                    proc_id[j+1] = temp;
                }
            }
        }
// finding completion times
        for(int  i = 0 ; i < num_p; i++)
        {
            if( i == 0)
            {
                comp_t[i] = arrival_t[i] + burst_t[i];
            }
            else
            {
                if( arrival_t[i] > comp_t[i-1])
                {
                    comp_t[i] = arrival_t[i] + burst_t[i];
                }
                else
                    comp_t[i] = comp_t[i-1] + burst_t[i];
            }
            turn_t[i] = comp_t[i] - arrival_t[i] ;          // turnaround time= completion time- arrival time
            wait_t[i] = turn_t[i] - burst_t[i] ;          // waiting time= turnaround time- burst time
            avg_wt += wait_t[i] ;               // total waiting time
            avg_turn += turn_t[i] ;               // total turnaround time
        }
        System.out.println("\npid  arrival  burst  complete turn waiting");
        for(int  i = 0 ; i< num_p;  i++)
        {
            System.out.println(proc_id[i] + "  \t " + arrival_t[i] + "\t" + burst_t[i] + "\t" + comp_t[i] + "\t" + turn_t[i] + "\t"  + wait_t[i] ) ;
        }
        float thr=(float)num_p/(comp_t[num_p-1]);
        System.out.println("\naverage waiting time: "+ (avg_wt/num_p));     // printing average waiting time.
        System.out.println("average turnaround time:"+(avg_turn/num_p));    // printing average turnaround time.
        System.out.println("Throughput :"+thr); 
    }
}