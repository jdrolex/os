// JAVA CODE :-
// Ex : 4 
/**************************************************************************************************************** */
// First Come First Serve
// import java.util.*;

// class Process {
//     int id;
//     int arrivalTime;
//     int burstTime;

//     public Process(int id, int arrivalTime, int burstTime) {
//         this.id = id;
//         this.arrivalTime = arrivalTime;
//         this.burstTime = burstTime;
//     }
// }

// public class FCFS {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of processes: ");
//         int n = scanner.nextInt();

//         Process[] processes = new Process[n];
//         for (int i = 0; i < n; i++) {
//             System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
//             int arrivalTime = scanner.nextInt();
//             int burstTime = scanner.nextInt();
//             processes[i] = new Process(i + 1, arrivalTime, burstTime);
//         }

//         Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

//         int currentTime = 0;
//         double totalWaitTime = 0;
//         System.out.println("\nProcess Execution Order:");
//         for (Process process : processes) {
//             if (currentTime < process.arrivalTime) {
//                 currentTime = process.arrivalTime;
//             }
//             totalWaitTime += currentTime - process.arrivalTime;
//             System.out.println("Process " + process.id + " executes from " + currentTime + " to " + (currentTime + process.burstTime));
//             currentTime += process.burstTime;
//         }

//         double averageWaitTime = totalWaitTime / n;
//         System.out.println("\nAverage Waiting Time: " + averageWaitTime);

//         scanner.close();
//     }
// }




/**************************************************************************************************************** */
// Shortest Job First
// import java.util.*;

// class Process {
//     int id;
//     int arrivalTime;
//     int burstTime;
//     int completionTime;
//     int waitingTime;
//     int turnaroundTime;

//     public Process(int id, int arrivalTime, int burstTime) {
//         this.id = id;
//         this.arrivalTime = arrivalTime;
//         this.burstTime = burstTime;
//     }
// }

// public class SJF {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of processes: ");
//         int n = scanner.nextInt();

//         Process[] processes = new Process[n];
//         for (int i = 0; i < n; i++) {
//             System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
//             int arrivalTime = scanner.nextInt();
//             int burstTime = scanner.nextInt();
//             processes[i] = new Process(i + 1, arrivalTime, burstTime);
//         }

//         Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

//         int currentTime = 0;
//         double totalWaitTime = 0;
//         System.out.println("\nProcess Execution Order:");
//         PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.burstTime));
//         for (Process process : processes) {
//             queue.offer(process);

//             while (!queue.isEmpty()) {
//                 Process currentProcess = queue.poll();
//                 currentTime = Math.max(currentTime, currentProcess.arrivalTime);
//                 currentProcess.waitingTime = currentTime - currentProcess.arrivalTime;
//                 currentProcess.completionTime = currentTime + currentProcess.burstTime;
//                 currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;

//                 totalWaitTime += currentProcess.waitingTime;
//                 System.out.println("Process " + currentProcess.id + " executes from " + currentTime + " to " + currentProcess.completionTime);
//                 currentTime = currentProcess.completionTime;
//             }
//         }

//         double averageWaitTime = totalWaitTime / n;
//         System.out.println("\nAverage Waiting Time: " + averageWaitTime);

//         scanner.close();
//     }
// }




/**************************************************************************************************************** */
// Round Robin 
// import java.util.*;

// class Process {
//     int id;
//     int arrivalTime;
//     int burstTime;
//     int remainingTime;
//     int completionTime;
//     int turnaroundTime;
//     int waitingTime;

//     public Process(int id, int arrivalTime, int burstTime) {
//         this.id = id;
//         this.arrivalTime = arrivalTime;
//         this.burstTime = burstTime;
//         this.remainingTime = burstTime;
//     }
// }

// public class RoundRobin {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of processes: ");
//         int n = scanner.nextInt();

//         System.out.print("Enter the time quantum: ");
//         int quantum = scanner.nextInt();

//         Queue<Process> queue = new LinkedList<>();
//         for (int i = 0; i < n; i++) {
//             System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
//             int arrivalTime = scanner.nextInt();
//             int burstTime = scanner.nextInt();
//             queue.offer(new Process(i + 1, arrivalTime, burstTime));
//         }

//         int currentTime = 0;
//         double totalWaitTime = 0;
//         int totalProcesses = n;

//         System.out.println("\nProcess Execution Order:");
//         while (!queue.isEmpty()) {
//             Process currentProcess = queue.poll();
//             if (currentProcess.remainingTime > quantum) {
//                 System.out.println("Process " + currentProcess.id + " executes from " + currentTime + " to " + (currentTime + quantum));
//                 currentTime += quantum;
//                 currentProcess.remainingTime -= quantum;
//                 queue.offer(currentProcess);
//             } else {
//                 System.out.println("Process " + currentProcess.id + " executes from " + currentTime + " to " + (currentTime + currentProcess.remainingTime));
//                 currentTime += currentProcess.remainingTime;
//                 currentProcess.remainingTime = 0;
//                 currentProcess.completionTime = currentTime;
//                 currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
//                 currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
//                 totalWaitTime += currentProcess.waitingTime;
//             }
//         }

//         double averageWaitTime = totalWaitTime / totalProcesses;
//         System.out.println("\nAverage Waiting Time: " + averageWaitTime);

//         scanner.close();
//     }
// }




/**************************************************************************************************************** */
// Multilevel Queue
// import java.util.*;

// class Process {
//     int id;
//     int arrivalTime;
//     int burstTime;
//     int priority;
//     int completionTime;
//     int turnaroundTime;
//     int waitingTime;

//     public Process(int id, int arrivalTime, int burstTime, int priority) {
//         this.id = id;
//         this.arrivalTime = arrivalTime;
//         this.burstTime = burstTime;
//         this.priority = priority;
//     }
// }

// public class MultilevelQueue {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of processes: ");
//         int n = scanner.nextInt();

//         List<Queue<Process>> queues = new ArrayList<>();
//         for (int i = 0; i < 3; i++) {
//             queues.add(new LinkedList<>());
//         }

//         for (int i = 0; i < n; i++) {
//             System.out.print("Enter arrival time, burst time, and priority (1, 2, or 3) for process " + (i + 1) + ": ");
//             int arrivalTime = scanner.nextInt();
//             int burstTime = scanner.nextInt();
//             int priority = scanner.nextInt();
//             Process process = new Process(i + 1, arrivalTime, burstTime, priority);
//             queues.get(priority - 1).offer(process);
//         }

//         int currentTime = 0;
//         double totalWaitTime = 0;
//         int totalProcesses = n;

//         System.out.println("\nProcess Execution Order:");
//         for (int i = 0; i < 3; i++) {
//             Queue<Process> queue = queues.get(i);
//             while (!queue.isEmpty()) {
//                 Process currentProcess = queue.poll();
//                 if (currentTime < currentProcess.arrivalTime) {
//                     currentTime = currentProcess.arrivalTime;
//                 }
//                 currentProcess.waitingTime = currentTime - currentProcess.arrivalTime;
//                 currentProcess.completionTime = currentTime + currentProcess.burstTime;
//                 currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;

//                 totalWaitTime += currentProcess.waitingTime;
//                 System.out.println("Process " + currentProcess.id + " executes from " + currentTime + " to " + currentProcess.completionTime);
//                 currentTime = currentProcess.completionTime;
//             }
//         }

//         double averageWaitTime = totalWaitTime / totalProcesses;
//         System.out.println("\nAverage Waiting Time: " + averageWaitTime);

//         scanner.close();
//     }
// }


/**************************************************************************************************************** */
// Multi level Feedback Queue
// import java.util.*;

// class Process {
//     int id;
//     int arrivalTime;
//     int burstTime;
//     int priority;
//     int completionTime;
//     int turnaroundTime;
//     int waitingTime;

//     public Process(int id, int arrivalTime, int burstTime, int priority) {
//         this.id = id;
//         this.arrivalTime = arrivalTime;
//         this.burstTime = burstTime;
//         this.priority = priority;
//     }
// }

// public class MultiLevelFeedbackQueue {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of processes: ");
//         int n = scanner.nextInt();

//         List<Queue<Process>> queues = new ArrayList<>();
//         for (int i = 0; i < 3; i++) {
//             queues.add(new LinkedList<>());
//         }

//         for (int i = 0; i < n; i++) {
//             System.out.print("Enter arrival time, burst time, and priority (1, 2, or 3) for process " + (i + 1) + ": ");
//             int arrivalTime = scanner.nextInt();
//             int burstTime = scanner.nextInt();
//             int priority = scanner.nextInt();
//             Process process = new Process(i + 1, arrivalTime, burstTime, priority);
//             queues.get(priority - 1).offer(process);
//         }

//         int currentTime = 0;
//         double totalWaitTime = 0;
//         int totalProcesses = n;

//         System.out.println("\nProcess Execution Order:");
//         for (int i = 0; i < 3; i++) {
//             Queue<Process> queue = queues.get(i);
//             while (!queue.isEmpty()) {
//                 Process currentProcess = queue.poll();
//                 if (currentTime < currentProcess.arrivalTime) {
//                     currentTime = currentProcess.arrivalTime;
//                 }
//                 currentProcess.waitingTime = currentTime - currentProcess.arrivalTime;
//                 currentProcess.completionTime = currentTime + currentProcess.burstTime;
//                 currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;

//                 totalWaitTime += currentProcess.waitingTime;
//                 System.out.println("Process " + currentProcess.id + " executes from " + currentTime + " to " + currentProcess.completionTime);
//                 currentTime = currentProcess.completionTime;
//             }
//         }

//         double averageWaitTime = totalWaitTime / totalProcesses;
//         System.out.println("\nAverage Waiting Time: " + averageWaitTime);

//         scanner.close();
//     }
// }



/**************************************************************************************************************** */


import java.util.Scanner;

class Main {
    static class Process {
        char name;
        int AT, BT, WT, TAT, RT, CT;
    }

    static Process[] Q1, Q2, Q3;
    static int n;

    static void sortByArrival() {
        Process temp;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Q1[i].AT > Q1[j].AT) {
                    temp = Q1[i];
                    Q1[i] = Q1[j];
                    Q1[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i, j, k = 0, r = 0, time = 0, tq1 = 5, tq2 = 8, flag = 0;
        char c;
        System.out.print("Enter no of processes:");
        n = scanner.nextInt();
        Q1 = new Process[n];
        Q2 = new Process[n];
        Q3 = new Process[n];
        for (i = 0, c = 'A'; i < n; i++, c++) {
            Q1[i] = new Process();
            Q1[i].name = c;
            System.out.print("\nEnter the arrival time and burst time of process " + Q1[i].name + ": ");
            Q1[i].AT = scanner.nextInt();
            Q1[i].BT = scanner.nextInt();
            Q1[i].RT = Q1[i].BT; /*save burst time in remaining time for each process*/
        }
        sortByArrival();
        time = Q1[0].AT;
        System.out.println("Process in first queue following RR with qt=5");
        System.out.println("Process\t\tRT\t\tWT\t\tTAT\t\t");
        for (i = 0; i < n; i++) {
            if (Q1[i].RT <= tq1) {
                time += Q1[i].RT; /*from arrival time of first process to completion of this process*/
                Q1[i].RT = 0;
                Q1[i].WT = time - Q1[i].AT - Q1[i].BT; /*amount of time process has been waiting in the first queue*/
                Q1[i].TAT = time - Q1[i].AT; /*amount of time to execute the process*/
                System.out.println(Q1[i].name + "\t\t" + Q1[i].BT + "\t\t" + Q1[i].WT + "\t\t" + Q1[i].TAT);
            } else /*process moves to queue 2 with qt=8*/ {
                Q2[k] = new Process();
                Q2[k].WT = time;
                time += tq1;
                Q1[i].RT -= tq1;
                Q2[k].BT = Q1[i].RT;
                Q2[k].RT = Q2[k].BT;
                Q2[k].name = Q1[i].name;
                k = k + 1;
                flag = 1;
            }
        }
        if (flag == 1) {
            System.out.println("Process in second queue following RR with qt=8");
            System.out.println("Process\t\tRT\t\tWT\t\tTAT\t\t");
        }
        for (i = 0; i < k; i++) {
            if (Q2[i].RT <= tq2) {
                time += Q2[i].RT; /*from arrival time of first process +BT of this process*/
                Q2[i].RT = 0;
                Q2[i].WT = time - tq1 - Q2[i].BT; /*amount of time process has been waiting in the ready queue*/
                Q2[i].TAT = time - Q2[i].AT; /*amount of time to execute the process*/
                System.out.println(Q2[i].name + "\t\t" + Q2[i].BT + "\t\t" + Q2[i].WT + "\t\t" + Q2[i].TAT);
            } else /*process moves to queue 3 with FCFS*/ {
                Q3[r] = new Process();
                Q3[r].AT = time;
                time += tq2;
                Q2[i].RT -= tq2;
                Q3[r].BT = Q2[i].RT;
                Q3[r].RT = Q3[r].BT;
                Q3[r].name = Q2[i].name;
                r = r + 1;
                flag = 2;
            }
        }
        if (flag == 2) {
            System.out.println("Process in third queue following FCFS ");
        }
        for (i = 0; i < r; i++) {
            if (i == 0)
                Q3[i].CT = Q3[i].BT + time - tq1 - tq2;
            else
                Q3[i].CT = Q3[i - 1].CT + Q3[i].BT;
        }
        for (i = 0; i < r; i++) {
            Q3[i].TAT = Q3[i].CT;
            Q3[i].WT = Q3[i].TAT - Q3[i].BT;
            System.out.println(Q3[i].name + "\t\t" + Q3[i].BT + "\t\t" + Q3[i].WT + "\t\t" + Q3[i].TAT);
        }
    }
}

} 
///////////
Priority Scheduling with at:

import java.util.*;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();

        int p[] = new int[n];
        System.out.println("Enter the process numbers:");
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }

        int at[] = new int[n];
        System.out.println("Enter the respective arrival time:");
        for (int i = 0; i < n;
             i++) {
            at[i] = sc.nextInt();
        }

        int bt[] = new int[n];
        System.out.println("Enter the respective burst time:");
        for (int i = 0; i < n; i++) {
            bt[i] = sc.nextInt();
        }

        int btcopy[] = Arrays.copyOf(bt, n);

        int pri[] = new int[n];
        System.out.println("Enter the priorities:");
        for (int i = 0; i < n; i++) {
            pri[i] = sc.nextInt();
        }

        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        priority(p, at, bt, ct, pri);
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
        }
        for (int i = 0; i < n; i++) {
            wt[i] = tat[i] - btcopy[i];
        }
        int tat_avg = 0, wt_avg = 0;
        for (int i = 0; i < n; i++) {
            tat_avg += tat[i];
            wt_avg += wt[i];
        }
        tat_avg/=n;
        wt_avg/=n;
        for (int i = 0; i < n; i++) {
            System.out.println(p[i] + "|" + at[i] + "|" + btcopy[i] + "|" + ct[i] + "|" + tat[i] + "|" + wt[i] + "|");
        }
        System.out.println("Average Turn Around Time: " + tat_avg);
        System.out.println("Average Waiting Time: " + wt_avg);
    }

    public static void priority(int p[], int at[], int bt[], int ct[], int pri[]) {
        int time = 0;
        int completed = 0;
        while (completed < p.length) {
            int maxPriorityIndex = -1;
            int maxPriority = Integer.MIN_VALUE;
            for (int i = 0; i < p.length; i++) {
                if (at[i] <= time && pri[i] > maxPriority && bt[i] > 0) {
                    maxPriority = pri[i];
                    maxPriorityIndex = i;
                }
            }

            if (maxPriorityIndex == -1) {
                time++;
            } else {
                int procIndex = p[maxPriorityIndex] - 1;
                time += bt[procIndex];
                ct[procIndex] = time;
                bt[procIndex] = 0;
                completed++;
            }
        }
        System.out.println("\nProcess\tCompletion Time");
        for (int i = 0; i < ct.length; i++) {
            System.out.println(p[i] + "\t\t" + ct[i]);
        }
    }
          }


