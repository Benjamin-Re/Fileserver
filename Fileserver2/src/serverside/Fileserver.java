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
			ServiceThread st = new ServiceThread(sock, filename1);
			st.start();
		}
	}
	
	public static void main (String args []) throws IOException {
		Fileserver fs = new Fileserver();
	}
}
