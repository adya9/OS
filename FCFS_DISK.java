import java.util.Scanner;

public class FCFS_DISK {
    static int size;

    static void FCFS(int arr[], int head) {
        int seek_count = 0;
        int distance, cur_track;
        int see_d[]=new int[size];
        for (int i = 0; i < size; i++) {
            cur_track = arr[i];

            // calculate absolute distance
            distance = Math.abs(cur_track - head);
             
            // increase the total count
            seek_count += distance;
            see_d[i]=distance;
            // accessed track is now new head
            head = cur_track;
        }

        

        // Seek sequence would be the same as request array sequence
        System.out.println("\nRequest \tSeek Time");

        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]+"\t\t"+see_d[i]);
        }
        System.out.println("Total number of seek operations = " + seek_count);
        System.out.println("Throughput :" + (float) size / seek_count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Request array
        System.out.println("Enter the size of request sequence:");
        size = scanner.nextInt();
        int arr[] = new int[size];
        System.out.println("Enter the request sequence:");

        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Enter the initial head position:");
        int head = scanner.nextInt();

        FCFS(arr, head);

        scanner.close();
    }
}
