
// Ex : 7
/**************************************************************************************************************** */
// Contingous memory management
// import java.util.*;

// class Process {
//     String name;
//     int size;
//     int startAddress;
//     boolean allocated;

//     public Process(String name, int size) {
//         this.name = name;
//         this.size = size;
//         this.allocated = false;
//     }
// }

// public class ContiguousMemoryManager {
//     private int totalMemory;
//     private List<Process> processes;
//     private List<Integer> freeBlocks;

//     public ContiguousMemoryManager(int totalMemory) {
//         this.totalMemory = totalMemory;
//         this.processes = new ArrayList<>();
//         this.freeBlocks = new ArrayList<>();
//         this.freeBlocks.add(totalMemory);
//     }

//     public void addProcess(String name, int size) {
//         Process process = new Process(name, size);
//         for (int i = 0; i < freeBlocks.size(); i++) {
//             int blockSize = freeBlocks.get(i);
//             if (blockSize >= size) {
//                 process.startAddress = totalMemory - blockSize;
//                 freeBlocks.remove(i);
//                 if (blockSize > size) {
//                     freeBlocks.add(blockSize - size);
//                 }
//                 processes.add(process);
//                 System.out.println("Process " + name + " allocated from address " + process.startAddress +
//                         " to " + (process.startAddress + size - 1));
//                 return;
//             }
//         }
//         System.out.println("Process " + name + " cannot be allocated due to insufficient memory.");
//     }

//     public void removeProcess(String name) {
//         for (int i = 0; i < processes.size(); i++) {
//             Process process = processes.get(i);
//             if (process.name.equals(name)) {
//                 freeBlocks.add(process.size);
//                 processes.remove(i);
//                 System.out.println("Process " + name + " removed.");
//                 return;
//             }
//         }
//         System.out.println("Process " + name + " not found.");
//     }

//     public void printMemoryMap() {
//         System.out.println("\nMemory Map:");
//         System.out.println("Total Memory: " + totalMemory);
//         System.out.println("Free Blocks: " + freeBlocks);
//         System.out.println("Allocated Processes:");
//         for (Process process : processes) {
//             System.out.println("Process " + process.name + ": Address " + process.startAddress +
//                     " - " + (process.startAddress + process.size - 1));
//         }
//     }

//     public static void main(String[] args) {
//         ContiguousMemoryManager memoryManager = new ContiguousMemoryManager(100);
//         memoryManager.addProcess("P1", 20);
//         memoryManager.addProcess("P2", 30);
//         memoryManager.printMemoryMap();
//         memoryManager.removeProcess("P1");
//         memoryManager.addProcess("P3", 10);
//         memoryManager.printMemoryMap();
//     }
// }


/**************************************************************************************************************** */