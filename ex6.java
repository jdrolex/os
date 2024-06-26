// Ex : 6
/**************************************************************************************************************** */
// Bankers Algoritham 
import java.util.*;

public class BankersAlgorithm {
    private int[][] max;
    private int[][] allocation;
    private int[][] need;
    private int[] available;
    private boolean[] finish;
    private int[] safeSequence;

    public BankersAlgorithm(int[][] max, int[][] allocation, int[] available) {
        this.max = max;
        this.allocation = allocation;
        this.available = available;
        this.finish = new boolean[max.length];
        this.safeSequence = new int[max.length];
        this.calculateNeed();
    }

    private void calculateNeed() {
        need = new int[max.length][max[0].length];
        for (int i = 0; i < max.length; i++) {
            for (int j = 0; j < max[i].length; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public boolean isSafe() {
        int[] work = Arrays.copyOf(available, available.length);
        for (int i = 0; i < finish.length; i++) {
            finish[i] = false;
        }

        int count = 0;
        while (count < finish.length) {
            boolean found = false;
            for (int i = 0; i < finish.length; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < work.length; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }
                    if (j == work.length) {
                        for (int k = 0; k < work.length; k++) {
                            work[k] += allocation[i][k];
                        }
                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }
            if (!found) {
                break;
            }
        }

        return count == finish.length;
    }

    public int[] getSafeSequence() {
        return safeSequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        System.out.print("Enter the number of resources: ");
        int m = scanner.nextInt();

        int[][] max = new int[n][m];
        int[][] allocation = new int[n][m];
        int[] available = new int[m];

        System.out.println("Enter the maximum resource matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the allocation matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the available resources:");
        for (int i = 0; i < m; i++) {
            available[i] = scanner.nextInt();
        }

        BankersAlgorithm bankersAlgorithm = new BankersAlgorithm(max, allocation, available);
        if (bankersAlgorithm.isSafe()) {
            System.out.println("Safe sequence:");
            int[] safeSequence = bankersAlgorithm.getSafeSequence();
            for (int i = 0; i < safeSequence.length; i++) {
                System.out.print("P" + safeSequence[i]);
                if (i < safeSequence.length - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        } else {
            System.out.println("System is in unsafe state.");
        }

        scanner.close();
    }
}




/**************************************************************************************************************** */
//Resource Request Algoritham
import java.util.*;

public class BankersAlgorithm {
    private int[][] max;
    private int[][] allocation;
    private int[][] need;
    private int[] available;
    private boolean[] finish;

    public BankersAlgorithm(int[][] max, int[][] allocation, int[] available) {
        this.max = max;
        this.allocation = allocation;
        this.available = available;
        this.finish = new boolean[max.length];
        this.calculateNeed();
    }

    private void calculateNeed() {
        need = new int[max.length][max[0].length];
        for (int i = 0; i < max.length; i++) {
            for (int j = 0; j < max[i].length; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public boolean isSafeState(int processId, int[] request) {
        if (processId < 0 || processId >= max.length) {
            return false;
        }

        for (int i = 0; i < request.length; i++) {
            if (request[i] > need[processId][i] || request[i] > available[i]) {
                return false;
            }
        }

        int[] tempAvailable = Arrays.copyOf(available, available.length);
        int[][] tempAllocation = new int[allocation.length][allocation[0].length];
        boolean[] tempFinish = Arrays.copyOf(finish, finish.length);

        // Try allocating resources to the process temporarily
        for (int i = 0; i < request.length; i++) {
            tempAvailable[i] -= request[i];
            tempAllocation[processId][i] += request[i];
            need[processId][i] -= request[i];
        }

        // Check if the system is still in a safe state
        boolean safe = isSafe(tempAvailable, tempAllocation, tempFinish);

        // Restore original values
        for (int i = 0; i < request.length; i++) {
            tempAvailable[i] += request[i];
            tempAllocation[processId][i] -= request[i];
            need[processId][i] += request[i];
        }

        return safe;
    }

    private boolean isSafe(int[] work, int[][] alloc, boolean[] finish) {
        int count = 0;
        while (count < finish.length) {
            boolean found = false;
            for (int i = 0; i < finish.length; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < work.length; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }
                    if (j == work.length) {
                        for (int k = 0; k < work.length; k++) {
                            work[k] += alloc[i][k];
                        }
                        finish[i] = true;
                        found = true;
                        count++;
                    }
                }
            }
            if (!found) {
                break;
            }
        }
        return count == finish.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        System.out.print("Enter the number of resources: ");
        int m = scanner.nextInt();

        int[][] max = new int[n][m];
        int[][] allocation = new int[n][m];
        int[] available = new int[m];

        System.out.println("Enter the maximum resource matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the allocation matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the available resources:");
        for (int i = 0; i < m; i++) {
            available[i] = scanner.nextInt();
        }

        BankersAlgorithm bankersAlgorithm = new BankersAlgorithm(max, allocation, available);

        System.out.print("Enter the process ID requesting resources: ");
        int processId = scanner.nextInt();

        System.out.println("Enter the resource request array:");
        int[] request = new int[m];
        for (int i = 0; i < m; i++) {
            request[i] = scanner.nextInt();
        }

        if (bankersAlgorithm.isSafeState(processId, request)) {
            System.out.println("Granting request is safe.");
        } else {
            System.out.println("Granting request is unsafe.");
        }

        scanner.close();
    }
}



/**************************************************************************************************************** */
