import java.io.*;  
import java.net.*;
import java.util.*;  
public class MyClient {  
public static void main(String[] args) {  
try{      
Socket s=new Socket("localhost",6666);  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
dout.writeUTF("Hello Server");  
System.out.println("Enter a string");
Scanner src = new Scanner(System.in);
String a;
a = src.nextLine();
dout.writeUTF(""+a);
dout.flush();  
dout.close();  
s.close();  
}catch(Exception e){System.out.println(e);}  
}  
}  