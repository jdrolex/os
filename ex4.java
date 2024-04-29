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




