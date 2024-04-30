
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
#include<stdio.h>
#define max 25
int main()
{
int nb,np, b[max],p[max], frag[max],i,j,temp,lowest=10000;
static int bf[max],ff[max];
printf("\nEnter the number of blocks:");
scanf("%d",&nb);
printf("Enter the number of processes:");
scanf("%d",&np);
printf("\nEnter the size of the blocks:-\n");
for(i=1;i<=nb;i++)
{
printf("Block %d:",i);
scanf("%d",&b[i]);
}
printf("Enter the size of the processes:-\n");
for(i=1;i<=np;i++)
{
printf("Process %d:",i);
scanf("%d",&p[i]);
}
for(i=1;i<=np;i++)
{
for(j=1;j<=nb;j++)
{
if(bf[j]!=1)
{
temp=b[j]-p[i];
if(temp>=0)
if(lowest>temp)
{
ff[i]=j;
lowest=temp;
}
}
}
frag[i]=lowest;
bf[ff[i]]=1;
lowest=10000;
}
printf("\nProcess_no \tProcess_size \tBlock_no \tBlock_size \tFragment");
for(i=1;i<=np && ff[i]!=0;i++)
printf("\n%d\t\t%d\t\t%d\t\t%d\t\t%d",i,p[i],ff[i],b[ff[i]],frag[i]);
return 0;
}
