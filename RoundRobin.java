import java.util.Scanner;

// public class RoundRobin {
//     private static Scanner scanner = new Scanner(System.in);

//     public static void main(String[] args) {
//         int n, tq, timer = 0, maxProcessIndex = 0;
//         float avgWait = 0, avgTT = 0;

//         System.out.print("Enter the time quantum: ");
//         tq = scanner.nextInt();
//         System.out.print("Enter the number of processes: ");
//         n = scanner.nextInt();

//         int arrival[] = new int[n];
//         int burst[] = new int[n];
//         int wait[] = new int[n];
//         int turn[] = new int[n];
//         int queue[] = new int[n];
//         int tempBurst[] = new int[n];
//         boolean complete[] = new boolean[n];

//         System.out.print("Enter the arrival time of the processes: ");
//         for (int i = 0; i < n; i++)
//             arrival[i] = scanner.nextInt();

//         System.out.print("Enter the burst time of the processes: ");
//         for (int i = 0; i < n; i++) {
//             burst[i] = scanner.nextInt();
//             tempBurst[i] = burst[i];
//         }

//         for (int i = 0; i < n; i++) { // Initializing the queue and complete array
//             complete[i] = false;
//             queue[i] = 0;
//         }

//         while (timer < arrival[0]) // Incrementing Timer until the first process arrives
//             timer++;
//         queue[0] = 1;

//         while (true) {
//             boolean flag = true;
//             for (int i = 0; i < n; i++) {
//                 if (tempBurst[i] != 0) {
//                     flag = false;
//                     break;
//                 }
//             }
//             if (flag)
//                 break;

//             for (int i = 0; (i < n) && (queue[i] != 0); i++) {
//                 int ctr = 0;
//                 while ((ctr < tq) && (tempBurst[queue[0] - 1] > 0)) {
//                     tempBurst[queue[0] - 1] -= 1;
//                     timer += 1;
//                     ctr++;

//                     // Updating the ready queue until all the processes arrive
//                     checkNewArrival(timer, arrival, n, maxProcessIndex, queue);
//                 }
//                 if ((tempBurst[queue[0] - 1] == 0) && (complete[queue[0] - 1] == false)) {
//                     turn[queue[0] - 1] = timer; // Turn currently stores exit times
//                     complete[queue[0] - 1] = true;
//                 }

//                 // Checks whether the CPU is idle or not
//                 boolean idle = true;
//                 if (queue[n - 1] == 0) {
//                     for (int k = 0; k < n && queue[k] != 0; k++) {
//                         if (complete[queue[k] - 1] == false) {
//                             idle = false;
//                         }
//                     }
//                 } else
//                     idle = false;

//                 if (idle) {
//                     timer++;
//                     checkNewArrival(timer, arrival, n, maxProcessIndex, queue);
//                 }

//                 // Maintaining the entries of processes after each preemption in the ready Queue
//                 queueMaintenance(queue, n);
//             }
//         }

//         for (int i = 0; i < n; i++) {
//             turn[i] = turn[i] - arrival[i];
//             wait[i] = turn[i] - burst[i];
//         }

//         System.out.println("\nProgram No.\tArrival Time\tBurst Time\tWait Time\tTurnaround Time\tCompletion Time");
//         for (int i = 0; i < n; i++) {
//             int completionTime = arrival[i] + turn[i];
//             System.out.println((i + 1) + "\t\t" + arrival[i] + "\t\t" + burst[i] + "\t\t" + wait[i] + "\t\t" + turn[i] + "\t\t\t" + completionTime);
//         }

//         for (int i = 0; i < n; i++) {
//             avgWait += wait[i];
//             avgTT += turn[i];
//         }

//         System.out.println("\nAverage wait time: " + (avgWait / n));
//         System.out.println("Average Turnaround Time: " + (avgTT / n));
//     }

//     public static void queueUpdation(int queue[], int timer, int arrival[], int n, int maxProcessIndex) {
//         int zeroIndex = -1;
//         for (int i = 0; i < n; i++) {
//             if (queue[i] == 0) {
//                 zeroIndex = i;
//                 break;
//             }
//         }
//         if (zeroIndex == -1)
//             return;
//         queue[zeroIndex] = maxProcessIndex + 1;
//     }

//     public static void checkNewArrival(int timer, int arrival[], int n, int maxProcessIndex, int queue[]) {
//         if (timer <= arrival[n - 1]) {
//             boolean newArrival = false;
//             for (int j = (maxProcessIndex + 1); j < n; j++) {
//                 if (arrival[j] <= timer) {
//                     if (maxProcessIndex < j) {
//                         maxProcessIndex = j;
//                         newArrival = true;
//                     }
//                 }
//             }
//             if (newArrival) // Adds the index of the arriving process(if any)
//                 queueUpdation(queue, timer, arrival, n, maxProcessIndex);
//         }
//     }

//     public static void queueMaintenance(int queue[], int n) {
//         for (int i = 0; (i < n - 1) && (queue[i + 1] != 0); i++) {
//             int temp = queue[i];
//             queue[i] = queue[i + 1];
//             queue[i + 1] = temp;
//         }
//     }
// }

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnTime;
    int waitingTime;
    int startTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        System.out.print("Enter time quantum of CPU: ");
        int tq = scanner.nextInt();

        Process[] p = new Process[n];
        int[] burstArr = new int[n];
        int totalWaiting = 0;
        int totalTurn = 0;

        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time of process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();

            System.out.print("Enter burst time of process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();

            burstArr[i] = burstTime;
            p[i] = new Process(i + 1, arrivalTime, burstTime);

            System.out.println();
        }

        Queue<Integer> queue = new LinkedList<>();
        int currentTime = 0;
        queue.offer(0);
        int completed = 0;
        int[] mark = new int[100];

        while (completed != n) {
            int index = queue.poll();

            if (burstArr[index] == p[index].burstTime) {
                p[index].startTime = Math.max(currentTime, p[index].arrivalTime);
                currentTime = p[index].startTime;
            }

            if (burstArr[index] > tq) {
                burstArr[index] -= tq;
                currentTime += tq;
            } else {
                currentTime += burstArr[index];
                p[index].completionTime = currentTime;
                p[index].turnTime = p[index].completionTime - p[index].arrivalTime;
                p[index].waitingTime = p[index].turnTime - p[index].burstTime;
                totalWaiting += p[index].waitingTime;
                totalTurn += p[index].turnTime;
                completed++;
                burstArr[index] = 0;
            }

            for (int i = 1; i < n; i++) {
                if (burstArr[i] > 0 && p[i].arrivalTime <= currentTime && mark[i] == 0) {
                    mark[i] = 1;
                    queue.offer(i);
                }
            }

            if (burstArr[index] > 0) {
                queue.offer(index);
            }

            if (queue.isEmpty()) {
                for (int i = 1; i < n; i++) {
                    if (burstArr[i] > 0) {
                        mark[i] = 1;
                        queue.offer(i);
                        break;
                    }
                }
            }
        }

        float avgWaiting = (float) totalWaiting / n;
        float avgTurn = (float) totalTurn / n;

        System.out.println();
        System.out.println("Process\t\tArrival Time\t\tBurst Time\t\tCompletion Time\t\tWaiting Time\t\tTurnaround Time");
        for (Process process : p) {
            System.out.println(process.id + "\t\t" + process.arrivalTime + "\t\t\t" + process.burstTime +
                    "\t\t\t" + process.completionTime + "\t\t\t" + process.waitingTime + "\t\t\t" + process.turnTime);
        }

        System.out.println();
        System.out.println("Average Waiting Time = " + avgWaiting);
        System.out.println("Average Turnaround Time = " + avgTurn);
    }
}
