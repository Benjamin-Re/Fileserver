package serverside;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class ServiceThread extends Thread {
	Socket sock;
	String filename1;
	
	public ServiceThread (Socket sock, String filename1) {
		this.sock = sock;
		this.filename1 = filename1;
	}
	
	public void run() {
		try {
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
			
			// Close the streams and socket when it's done
			System.out.println("File was transmitted to " + sock.getInetAddress());
			fromFile.close();
			toClient.close();
			sock.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
