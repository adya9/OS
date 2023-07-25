import java.util.Scanner;

public class SSTF_DISK {
    static int size;

    static int findClosestTrack(int arr[], int head, boolean visited[]) {
        int minDistance = Integer.MAX_VALUE;
        int closestTrack = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                int distance = Math.abs(arr[i] - head);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestTrack = i;
                }
            }
        }

        return closestTrack;
    }

    static void SSTF(int arr[], int head) {
        int seek_count = 0;
        int cur_track;
        boolean[] visited = new boolean[size];
        int[] sequence = new int[size];
        int[] see_d=new int[size];
        for (int i = 0; i < size; i++) {
            cur_track = findClosestTrack(arr, head, visited);
            visited[cur_track] = true;
            sequence[i] = arr[cur_track];

            // calculate absolute distance
            int distance = Math.abs(arr[cur_track] - head);

            // increase the total count
            seek_count += distance;
            see_d[i]=distance;
            // accessed track is now new head
            head = arr[cur_track];
        }

     
        System.out.println("\nRequest \tSeek Time");

        for (int i = 0; i < size; i++) {
            System.out.println(sequence[i]+"\t\t"+see_d[i]);
        }

        System.out.println("Total number of seek operations = " + seek_count);
        System.out.println("Throughput :" + (float) size / seek_count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of disk requests: ");
        size = scanner.nextInt();

        // Request array
        int arr[] = new int[size];
        System.out.println("Enter the disk request sequence:");

        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Enter the initial head position:");
        int head = scanner.nextInt();

        SSTF(arr, head);

        scanner.close();
    }
}
