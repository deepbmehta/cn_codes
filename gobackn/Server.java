import java.io.*;
import java.net.*;
import java.util.*;
class Server
{
public static void main(String args[])throws Exception
{
int k=0;
ServerSocket server=new ServerSocket(6262);
System.out.println("server established");
Socket client=server.accept();
ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
System.out.println("client is now connected");
int x=(Integer)ois.readObject();
int y=(Integer)ois.readObject();
int j=0;
int i=(Integer)ois.readObject();
boolean flag=true;
Random r=new Random(6);
int mod=r.nextInt(6);
while(mod==1||mod==0)
mod=r.nextInt(6);
while(true)
{
int c=k;
for(int h=0;h<=x;h++)
{
System.out.println("|"+c+"|");
c=(c+1)%x;
}
System.out.println();
if(k==j)
{
System.out.println("frame "+k+" recieved"+"\n"+"data:"+j);
j++;
System.out.println();
}
else
System.out.println("frames not recieved in correct order");
System.out.println();
if(j%mod==0 && flag)
{
System.out.println("error has occured.Acknowledgement not sent");
flag=!flag;
j--;
}
else if(k==j-1)
{
oos.writeObject(k);
System.out.println("acknowledgement sent");
}
System.out.println();
if(j%mod==0)
flag=!flag;
k=(Integer)ois.readObject();
if(k==-1)
break;
i=(Integer)ois.readObject();
}
System.out.println("client finished sending data");
oos.writeObject(-1);
}
}
