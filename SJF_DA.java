// import java.util.*;

// class Process {
//     int pid; // process ID
//     int arrivalTime; // arrival time of process
//     int burstTime; // burst time of process

//     public Process(int pid, int arrivalTime, int burstTime) {
//         this.pid = pid;
//         this.arrivalTime = arrivalTime;
//         this.burstTime = burstTime;
//     }
// }

// class SJF_DA {
//     public static void findWaitingTime(Process[] proc, int[] wt, int[] st) {
//         int n = proc.length;
//         int[] remainingTime = new int[n];
//         for (int i = 0; i < n; i++) {
//             remainingTime[i] = proc[i].burstTime;
//         }

//         int complete = 0, t = 0, minBurstTime = Integer.MAX_VALUE, shortest = 0;
//         boolean flag = false;

//         while (complete != n) {
//             for (int i = 0; i < n; i++) {
//                 if (proc[i].arrivalTime <= t && remainingTime[i] < minBurstTime && remainingTime[i] > 0) {
//                     minBurstTime = remainingTime[i];
//                     shortest = i;
//                     flag = true;
//                 }
//             }

//             if (!flag) {
//                 t++;
//                 continue;
//             }

//             remainingTime[shortest]--;
//             minBurstTime = remainingTime[shortest];

//             if (minBurstTime == 0) {
//                 minBurstTime = Integer.MAX_VALUE;
//             }

//             if (remainingTime[shortest] == 0) {
//                 complete++;
//                 flag = false;

//                 int endTime = t + 1;
//                 wt[shortest] = endTime - proc[shortest].burstTime - proc[shortest].arrivalTime;
//                 if (wt[shortest] < 0) {
//                     wt[shortest] = 0;
//                 }

//                 st[shortest] = endTime - proc[shortest].burstTime;
//             }

//             t++;
//         }
//     }

//     public static void findTurnAroundTime(Process[] proc, int[] wt, int[] tat) {
//         for (int i = 0; i < proc.length; i++) {
//             tat[i] = proc[i].burstTime + wt[i];
//         }
//     }  

//     static void findAvgTime(Process[] proc) {
//         int n = proc.length;
//         int[] wt = new int[n];
//         int[] tat = new int[n];
//         int[] ct = new int[n];
//         int[] st = new int[n];

//         findWaitingTime(proc, wt,st);
//         findTurnAroundTime(proc, wt, tat);

//         System.out.println("pid"+"\t\t"+"AT"+"\t\t"+ "BT"+"\t\t"+"ST"+"\t\t"+"CT"+"\t\t"+"WAT"+"\t\t"+"TAT");

//         int total_wt = 0, total_tat = 0;
//         for (int i = 0; i < n; i++) {
//             ct[i] = tat[i] + proc[i].arrivalTime;

//             total_wt += wt[i];
//             total_tat += tat[i];
//             System.out.printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", proc[i].pid,proc[i].arrivalTime, proc[i].burstTime,st[i],ct[i], wt[i], tat[i]);
//         }

//         System.out.printf("Average waiting time = %.2f\n", (float)total_wt / (float)n);
//         System.out.printf("Average turnaround time = %.2f\n", (float)total_tat / (float)n);
//     }

//     public static void main(String[] args) {
//      //   Process[] proc = { new Process(1, 0, 5), new Process(2, 1, 4), new Process(3, 3, 7), new Process(4, 5, 2), new Process(5, 7, 1), new Process(6, 9, 8) };
//         Scanner sc=new Scanner(System.in);

//         System.out.println("Enter the number of process :");
//         int n=sc.nextInt();
//         Process[] proc=new Process[n];
//         for(int i=0;i<n;i++)
//         {
//             System.out.println("Enter the Process ID of process no. "+(i+1));
//             int pid=sc.nextInt();
//             System.out.println("Enter the Arrival time of process no. "+(i+1));
//             int at=sc.nextInt();
//             System.out.println("Enter the Burst Time of process no. "+(i+1));
//             int bt=sc.nextInt();
//             proc[i]=new Process(pid,at,bt);
//         }
//         findAvgTime(proc);
//     }
// }

import java.util.*;

public class SJF_DA {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of process:");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n]; // at means arrival time
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n]; // wt means waiting time
        int f[] = new int[n]; // f means it is flag it checks process is completed or not
        int st = 0, tot = 0;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("enter process " + (i + 1) + " brust time:");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
            f[i] = 0;
        }
        boolean a = true;
        while (true) {
            int c = n, min = 999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i = 0; i < n; i++) {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }
            /*
             * If c==n means c value can not updated because no process arrival time< system
             * time so we increase the system time
             */
            if (c == n)
                st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }
        System.out.println("\npid  arrival brust  complete turn waiting");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }
        System.out.println("\naverage tat is " + (float) (avgta / n));
        System.out.println("average wt is " + (float) (avgwt / n));
        sc.close();
    }
}
