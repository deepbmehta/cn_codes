import java.io.*;
import java.util.*;
import java.net.*;
class Client
{
public static void main(String args[])throws Exception
{
Scanner sc=new Scanner(System.in);
System.out.println("enter the value of m");
int m=sc.nextInt();
int x=(int)((Math.pow(2,m))-1);
System.out.println("enter the no of frames being sent");
int count=sc.nextInt();
int data[]=new int[count];
int h=0;
for(int i=0;i<count;i++)
{
System.out.println("enter data for frame no"+h+"=>");
data[i]=sc.nextInt();
h=(h+1)%x;
}
Socket client=new Socket("localhost",6262);
ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
System.out.println("Connected with server.");
boolean flag=false;
GoBackNListener listener=new GoBackNListener(ois,x);
listener=new GoBackNListener(ois,x);
listener.t.start();
int strt=0;
h=0;
oos.writeObject(x);
do
{
 int c=h;
for(int i=h;i<count;i++)
{
System.out.print("|"+c+"|");
c=(c+1)%x;
}
System.out.println();
h=strt;
for(int i=strt;i<x;i++)
{
System.out.println("sending frame:"+h);
h=(h+1)%x;
System.out.println();
oos.writeObject(i);
oos.writeObject(data[i]);
Thread.sleep(100);
}
listener.t.join(3500);
if(listener.reply!=x-1)
{
System.out.println("No reply from server in 3.5 seconds. Resending data from frame no " + (listener.reply+1));
System.out.println();
strt=listener.reply+1;
flag=false;
}
else
{
System.out.println("all elements sent successfully");
flag=true;
}
}while(!flag);
oos.writeObject(-1);
}
}
class GoBackNListener implements Runnable
{
Thread t;
ObjectInputStream ois;
int reply,x;
GoBackNListener(ObjectInputStream o,int i)
{
t=new Thread(this);
ois=o;
reply=-2;
x=i;
}
@Override
public void run(){
try
{
int temp=0;
while(reply!=-1)
{
reply=(Integer)ois.readObject();
if(reply!=-1 && reply!=temp+1)
reply=temp;
if(reply!=-1)
{
temp=reply;
System.out.println("Acknowledgement of frame no " + (reply%x) + " recieved.");
System.out.println();
}
}
reply=temp;
}
catch(Exception e)
{
System.out.println("eaception="+e);
}
}
}
