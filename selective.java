import java.lang.*;
import java.util.*;
import java.net.*;
import java.io.*;

class Sender{
	Socket socket = null;
	ObjectInputStream inp = null;
	ObjectOutputStream out = null;

	Sender(String address, int port){
		try{
			socket = new Socket(address, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			inp = new ObjectInputStream(socket.getInputStream());
		}
		catch(Exception e){}
	}

	public void run(int k, int n){
		// Size of window
		int mod = (int) Math.pow(2, k-1);
		// Size of header
		int cmod = (int) Math.pow(2, k);

		int start = 0, end = mod - 1, pointer = 0;
		// Packets
		int arr [] = new int[n];
		// Keep track of acknowledged packets
		boolean done[] = new boolean[n];

		for(int i = 0 ; i < n ; i ++){
			arr[i] = i%cmod;
			done[i] = false;
		}

		do{
			try{
				// Send a packet
				out.writeObject(arr[start + pointer]);
				System.out.println("Sending " + arr[start + pointer] + " : " + start + ":" + pointer);
					
				// Avoid sending packets that have had ACK
				if(done[start+pointer]){
					while(done[pointer + start]){
						pointer = (pointer + 1)%mod;
					}
				}
				else{
					pointer = (pointer + 1)%mod;
				}

				// Read ACK/NACK
				int message = (int) inp.readObject();

				if(message < 0){
					System.out.println("NACK " + Math.abs(message));
				}

				else{
					// Mark the ACK received
					int i;
					for(i = 0; i < mod ; i++){
						if(!done[start+i] && arr[start + i] == message){
							done[start + i] = true;
							System.out.println("ACK " + message);
							break;
						}
					}

					// Increment start if ACK for start of window
					// has been received
					if(i == 0){
						while(done[start]){
							start ++;
							end ++;
						}
						pointer = 0;
					}
				}
			}
			catch(Exception e){}
		} while(start < n);

		try{
			out.writeObject(-1);
			socket.close();
			out.close();
			inp.close();
		} catch(Exception e){}
	}
}


class Receiver{
	Socket socket = null;
	ObjectOutputStream out = null;
	ObjectInputStream inp = null;

	Receiver(int port){
		try{
		ServerSocket serverSocket = new ServerSocket(port);
		socket = serverSocket.accept();

		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		inp = new ObjectInputStream(socket.getInputStream());
		}
		catch(Exception e){}
	}

	public void run(int k, int n){
		int mod = (int) Math.pow(2, k-1);
		int cmod = (int) Math.pow(2, k);
		int arr[] = new int[n];
		boolean done[] = new boolean[n];

		for(int i = 0 ; i < n ; i++) {
			arr[i] = i%cmod;
			done[i] = false;
		}

		int start = 0, end = mod - 1;
		
		do{
			boolean sent = false;
			try{
				// Read message
				int message = (int) inp.readObject();
				// Indicates end of packets
				if(message < 0) break;
				
				// Randomly drop a packet
				if((int) (Math.random()*10) > 8){
					System.out.println("Dropped " +message);
					out.writeObject(-message);
					continue;
				}

				int i;

				// Mark received packet as done
				// Send acknowledgement
				for(i = 0 ; i < mod ; i ++){
					if(!done[start + i] && message == arr[start + i]){
						System.out.println("Recieved " + message);
						out.writeObject(message);
						done[start + i] = true;
						sent = true;
						break;
					}
				}

				// Increment if received beginning of window
				while(done[start] && start < n){ start++; end++; }
				
				// Break if necessary
				if(start == n) break;

				// In case Sender sends a packet not inside the window
				if(!sent) out.writeObject(-69);
			} catch(Exception e){}


		}while(true);

		try{
			socket.close();
			inp.close();
			out.close();
		} catch(Exception e){}
	}
}


class selective{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter sequence number size (bits)");
		int k = sc.nextInt();
		System.out.println("Enter number of packets");
		int n = sc.nextInt();

		System.out.println("1. Client\n2.Server");
		int choice = sc.nextInt();

		int port = 5000;
		String address = "localhost";

		if(choice == 2){
			Sender sender = new Sender(address, port);
			sender.run(k, n);
		}
		else{
			Receiver receiver = new Receiver(port);
			receiver.run(k, n);
		}
	}
}