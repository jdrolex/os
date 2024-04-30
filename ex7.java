
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
//best fit
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
************************
    //first fit
    int main()
{
int bno,pno,bs[20],ps[20],i,j;
printf("Enter no of Blocks.\n");
scanf("%d",&bno);
for(i=0;i<bno;i++)
{
printf("Enter the %dst Block size:",i);
scanf("%d",&bs[i]);
}
printf("Enter no of Process.\n");
scanf("%d",&pno);
for(i=0;i<pno;i++)
{
printf("Enter the size of %dst Process:",i);
scanf("%d",&ps[i]);
}
for(i=0;i<bno;i++)
{
for(j=0;j<pno;j++)
{
if(ps[j]<=bs[i])
{
printf("The Process %d allocated to %d\n",j,bs[i]);
ps[j]=10000;
break;
}
}
}
for(j=0;j<pno;j++)
{
if(ps[j]!=10000)
{
printf("The Process %d is not allocated\n",j);
}
}
return 0;
}
*****************
    // Java implementation of Best - Fit algorithm 

public class GFG 
{ 
	// Method to allocate memory to blocks as per Best fit 
	// algorithm 
	static void bestFit(int blockSize[], int m, int processSize[], 
													int n) 
	{ 
		// Stores block id of the block allocated to a 
		// process 
		int allocation[] = new int[n]; 
	
		// Initially no block is assigned to any process 
		for (int i = 0; i < allocation.length; i++) 
			allocation[i] = -1; 
	
	// pick each process and find suitable blocks 
		// according to its size ad assign to it 
		for (int i=0; i<n; i++) 
		{ 
			// Find the best fit block for current process 
			int bestIdx = -1; 
			for (int j=0; j<m; j++) 
			{ 
				if (blockSize[j] >= processSize[i]) 
				{ 
					if (bestIdx == -1) 
						bestIdx = j; 
					else if (blockSize[bestIdx] > blockSize[j]) 
						bestIdx = j; 
				} 
			} 
	
			// If we could find a block for current process 
			if (bestIdx != -1) 
			{ 
				// allocate block j to p[i] process 
				allocation[i] = bestIdx; 
	
				// Reduce available memory in this block. 
				blockSize[bestIdx] -= processSize[i]; 
			} 
		} 
	
		System.out.println("\nProcess No.\tProcess Size\tBlock no."); 
		for (int i = 0; i < n; i++) 
		{ 
			System.out.print(" " + (i+1) + "\t\t" + processSize[i] + "\t\t"); 
			if (allocation[i] != -1) 
				System.out.print(allocation[i] + 1); 
			else
				System.out.print("Not Allocated"); 
			System.out.println(); 
		} 
	} 
	
	// Driver Method 
	public static void main(String[] args) 
	{ 
		int blockSize[] = {100, 500, 200, 300, 600}; 
		int processSize[] = {212, 417, 112, 426}; 
		int m = blockSize.length; 
		int n = processSize.length; 
		
		bestFit(blockSize, m, processSize, n); 
	} 
}
