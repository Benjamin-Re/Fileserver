package clientside;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Fileclient extends Thread {
	
	String filename = "Test2.txt";
	Socket sock;
	
	// Constructor
	public Fileclient() throws UnknownHostException, IOException {
		sock = new Socket("localhost", 8181);
		System.out.println("Socket connected: " + sock.isConnected());
		this.start();		
	}
	
	public void run () {
		try {
			// Open Input-stream from Server
			BufferedInputStream fromServer = new BufferedInputStream(sock.getInputStream());
			
			// Open Output-stream to file
			BufferedOutputStream toFile = new BufferedOutputStream(new FileOutputStream(filename));

			// Write data to the file
			int data = -1;
			while((data = fromServer.read()) != -1) {
				toFile.write(data);
			}
			toFile.flush();
			
			sock.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	public static void main (String args []) throws UnknownHostException, IOException {
		Fileclient fc1 = new Fileclient();
		Fileclient fc2 = new Fileclient();
		Fileclient fc3 = new Fileclient();
	}
}
