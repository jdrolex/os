// Ex : 5
/**************************************************************************************************************** */
//Two way communication uing pipe

#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
int main()
{
    int fd[2], n;
    char buffer[100];
    pid_t p;
    pipe(fd); //creates a unidirectional pipe with two ends fd[0] and fd[1]
    p=fork();
    if(p>0) //parent
    {
        printf("Parent Passing value to child\n");
        write(fd[1],"hello\n",6); //fd[1] is the write end of the pipe
        close(fd[1]); // Close the write end of the pipe
        wait(NULL); // Wait for the child process to finish
    }
    else // child
    {
        close(fd[1]); // Close the write end of the pipe
        printf("Child printing received value\n");
        n=read(fd[0],buffer,100); //fd[0] is the read end of the pipe
        write(1,buffer,n);
    }
    return 0;
}



//MESSAGE QUEUE FOR WRITER PROCESS 
// C Program for Message Queue (Writer Process)
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#define MAX 10
  
// structure for message queue
struct mesg_buffer {
    long mesg_type;
    char mesg_text[100];
} message;
  
int main()
{
    key_t key;
    int msgid;
  
    // ftok to generate unique key
    key = ftok("progfile", 65);
  
    // msgget creates a message queue
    // and returns identifier
    msgid = msgget(key, 0666 | IPC_CREAT);
    message.mesg_type = 1;
  
    printf("Write Data : ");
    fgets(message.mesg_text,MAX,stdin);
  
    // msgsnd to send message
    msgsnd(msgid, &message, sizeof(message), 0);
  
    // display the message
    printf("Data to send is : %s \n", message.mesg_text);
  
    return 0;
}

MESSAGE QUEUE FOR READER PROCESS 
// C Program for Message Queue (Reader Process)
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
  
// structure for message queue
struct mesg_buffer {
    long mesg_type;
    char mesg_text[100];
} message;
  
int main()
{
    key_t key;
    int msgid;
  
    // ftok to generate unique key
    key = ftok("progfile", 65);
  
    // msgget creates a message queue
    // and returns identifier
    msgid = msgget(key, 0666 | IPC_CREAT);
  
    // msgrcv to receive message
    msgrcv(msgid, &message, sizeof(message), 1, 0);
  
    // display the message
    printf("Data Received is : %s \n", 
                    message.mesg_text);
  
    // to destroy the message queue
    msgctl(msgid, IPC_RMID, NULL);
  
    return 0;
}





//SHARED MEMORY FOR WRITER PROCESS
#include <iostream>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
using namespace std;

int main()
{
    // ftok to generate unique key
    key_t key = ftok("shmfile", 65);
    if (key == -1) {
        perror("ftok");
        return 1;
    }

    // shmget returns an identifier in shmid
    int shmid = shmget(key, 1024, 0666 | IPC_CREAT);
    if (shmid == -1) {
        perror("shmget");
        return 1;
    }

    // shmat to attach to shared memory
    char *str = (char*) shmat(shmid, nullptr, 0);
    if (str == (char*)(-1)) {
        perror("shmat");
        return 1;
    }

    // Read input from user
    cout << "Enter a string: ";
    fgets(str, 1024, stdin);
    // Remove newline character if present
    str[strcspn(str, "\n")] = '\0';

    printf("Data written in memory: %s\n", str);

    // Detach from shared memory
    if (shmdt(str) == -1) {
        perror("shmdt");
        return 1;
    }

    return 0;
}


SHARED MEMORY FOR READER PROCESS
#include <iostream>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
using namespace std;
  
int main()
{
    // ftok to generate unique key
    key_t key = ftok("shmfile",65);
  
    // shmget returns an identifier in shmid
    int shmid = shmget(key,1024,0666|IPC_CREAT);
  
    // shmat to attach to shared memory
    char *str = (char*) shmat(shmid,(void*)0,0);
  
    printf("Data read from memory: %s\n",str);
      
    //detach from shared memory 
    shmdt(str);
    
    // destroy the shared memory
    shmctl(shmid,IPC_RMID,NULL);
     
    return 0;
}

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int fd1[2], fd2[2];
    pid_t p;
    char buffer[100];

    // Create first pipe
    if (pipe(fd1) == -1) {
        perror("pipe");
        return 1;
    }

    // Create second pipe
    if (pipe(fd2) == -1) {
        perror("pipe");
        return 1;
    }

    p = fork();
    if (p < 0) {
        perror("fork");
        return 1;
    }

    if (p > 0) { // Parent process
        close(fd1[0]); // Close read end of first pipe
        close(fd2[1]); // Close write end of second pipe
        
        printf("Parent passing value to child\n");
        write(fd1[1], "hello\n", 6); // Write to first pipe

        printf("Parent waiting for response from child\n");
        read(fd2[0], buffer, sizeof(buffer)); // Read from second pipe
        printf("Parent received response from child: %s\n", buffer);

        wait(NULL);
    } else { // Child process
        close(fd1[1]); // Close write end of first pipe
        close(fd2[0]); // Close read end of second pipe

        read(fd1[0], buffer, sizeof(buffer)); // Read from first pipe
        printf("Child received value from parent: %s", buffer);

        printf("Child passing value to parent\n");
        write(fd2[1], "world\n", 6); // Write to second pipe
    }

    return 0;
}
