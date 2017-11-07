import java.util.*;
class Crc
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		char[] msg=new char[20];
		char[] gen=new char[20];

		System.out.println("enter the message string");
		String msgs=sc.nextLine();
		System.out.println("enter the generator string");
		String gens=sc.nextLine();
		for(int i=0;i<msgs.length();i++)
			msg[i]=msgs.charAt(i);
		for(int i=0;i<gens.length();i++)
			gen[i]=gens.charAt(i);
		for(int i=msgs.length();i<msgs.length() + gens.length()-1;i++)
			msg[i]='0';
		System.out.print("Appended string is: ");
		for(int i=0;i<msgs.length() + gens.length()-1;i++)
			System.out.print(msg[i]);
		System.out.println();
		for(int i=0;i<msgs.length();i++)
		{
			if(msg[i]!='0' && msg[i]!='1')
			{
				System.out.println("entered message is wrong");
				return;
			}
			System.out.println(i);
			if(msg[i]==1){	
				for(int j=0,k=i;j<gens.length();j++,k++)
				{ 
					msg[k]=xor(msg[k],gens.charAt(j)); 
				}
		}
			for(int l=0;l<msgs.length()+gens.length()-1;l++)
				System.out.print(msg[l]);
			System.out.println();
		}

		System.out.print("Checksummed message is: " +msgs);
		for(int i=msgs.length();i<msgs.length() + gens.length()-1;i++)
			System.out.print(msg[i]);
		System.out.println();
	}
	public static char xor(char x,char y)
	{
		if((x=='1'&&y=='1')||(x=='0'&&y=='0'))
			return '0';
		else
			return '1'; }
	}
