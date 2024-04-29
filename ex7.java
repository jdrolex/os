
// Ex : 7
/**************************************************************************************************************** */
// Contingous memory management
import java.util.*;

class Process {
    String name;
    int size;
    int startAddress;
    boolean allocated;

    public Process(String name, int size) {
        this.name = name;
        this.size = size;
        this.allocated = false;
    }
}

public class ContiguousMemoryManager {
    private int totalMemory;
    private List<Process> processes;
    private List<Integer> freeBlocks;

    public ContiguousMemoryManager(int totalMemory) {
        this.totalMemory = totalMemory;
        this.processes = new ArrayList<>();
        this.freeBlocks = new ArrayList<>();
        this.freeBlocks.add(totalMemory);
    }

    public void addProcess(String name, int size) {
        Process process = new Process(name, size);
        for (int i = 0; i < freeBlocks.size(); i++) {
            int blockSize = freeBlocks.get(i);
            if (blockSize >= size) {
                process.startAddress = totalMemory - blockSize;
                freeBlocks.remove(i);
                if (blockSize > size) {
                    freeBlocks.add(blockSize - size);
                }
                processes.add(process);
                System.out.println("Process " + name + " allocated from address " + process.startAddress +
                        " to " + (process.startAddress + size - 1));
                return;
            }
        }
        System.out.println("Process " + name + " cannot be allocated due to insufficient memory.");
    }

    public void removeProcess(String name) {
        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            if (process.name.equals(name)) {
                freeBlocks.add(process.size);
                processes.remove(i);
                System.out.println("Process " + name + " removed.");
                return;
            }
        }
        System.out.println("Process " + name + " not found.");
    }

    public void printMemoryMap() {
        System.out.println("\nMemory Map:");
        System.out.println("Total Memory: " + totalMemory);
        System.out.println("Free Blocks: " + freeBlocks);
        System.out.println("Allocated Processes:");
        for (Process process : processes) {
            System.out.println("Process " + process.name + ": Address " + process.startAddress +
                    " - " + (process.startAddress + process.size - 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total memory size: ");
        int totalMemory = scanner.nextInt();

        ContiguousMemoryManager memoryManager = new ContiguousMemoryManager(totalMemory);
        while (true) {
            System.out.print("\nEnter 1 to add a process, 2 to remove a process, 3 to print memory map, or 0 to exit: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter process name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter process size: ");
                    int size = scanner.nextInt();
                    memoryManager.addProcess(name, size);
                    break;
                case 2:
                    System.out.print("Enter process name to remove: ");
                    name = scanner.nextLine();
                    memoryManager.removeProcess(name);
                    break;
                case 3:
                    memoryManager.printMemoryMap();
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


/**************************************************************************************************************** */
