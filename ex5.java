// Ex : 5
/**************************************************************************************************************** */
//Two way communication uing pipe

#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
int main()
{
int fd[2],n;
char buffer[100];
pid_t p;
pipe(fd); //creates a unidirectional pipe with two end fd[0] and fd[1]
p=fork();
if(p>0) //parent
{
printf("Parent Passing value to child\n");
write(fd[1],"hello\n",6); //fd[1] is the write end of the pipe
wait();
}
else // child
{
printf("Child printing received value\n");
n=read(fd[0],buffer,100); //fd[0] is the read end of the pipe
write(1,buffer,n);
}
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
using namespace std;
  
int main()
{
    // ftok to generate unique key
    key_t key = ftok("shmfile",65);
  
    // shmget returns an identifier in shmid
    int shmid = shmget(key,1024,0666|IPC_CREAT);
  
    // shmat to attach to shared memory
    char *str = (char*) shmat(shmid,(void*)0,0);
  
    gets(str);
  
    printf("Data written in memory: %s\n",str);
      
    //detach from shared memory 
    shmdt(str);
  
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
