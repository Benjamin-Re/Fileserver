package serverside;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Fileserver {
	ServerSocket servsock;
	String filename1 = "Test1.txt";
	
	// Constructor
	public Fileserver() throws IOException {
		ServerSocket servsock = new ServerSocket(8181);
		// Put accept in while(true) loop to accept multiple client connections ;)
		while(true) {
			// Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
			Socket sock = servsock.accept();
			
			// Open input-stream from the file to be transmitted
			BufferedInputStream fromFile = new BufferedInputStream(new FileInputStream(filename1));
			
			// Open output-stream for file to be transmitted to
			BufferedOutputStream toClient = new BufferedOutputStream(sock.getOutputStream());
			
			// Filetransfer
			int data = -1;
			while ((data = fromFile.read()) != -1) {
				toClient.write(data);
			}
			// Flushes this output stream and forces any buffered output bytes to be written out.
			toClient.flush();
			
			// Close the socket when it's done
			System.out.println("File was transmitted to " + sock.getInetAddress());
			sock.close();
		}
	}
	
	public static void main (String args []) throws IOException {
		Fileserver fs = new Fileserver();
	}
}
