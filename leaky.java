import java.util.*;
import java.io.*;

class leaky extends Thread{
	public static void main(String args[])throws Exception{
		int q[] = new int[10];
		int i=0,r=0;
		System.out.println("Enter number of elements");
		Scanner src = new Scanner(System.in);
		int n = src.nextInt();
		for(i=0;i<n;i++){
			System.out.print("Enter "+i+" element : ");
			if(i>=10){
				System.out.println("Buffer is full");
				System.out.println("Packet "+src.nextInt()+" is lost");
			}else{
				q[i]=src.nextInt();
			}
		}

		Thread t = new Thread();
		
		
		if (i==0){
			System.out.println("Buffer is empty");
		
		}else{
			for(int j=0;j<i;j++)
			System.out.println("Leaked packet: "+q[j]);
			try{
					t.sleep(1000000);
				}
			catch(Exception e)
				{
					
				}
		}
		
		

	}
}