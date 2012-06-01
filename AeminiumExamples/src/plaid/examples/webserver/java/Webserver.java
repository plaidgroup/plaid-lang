package plaid.examples.webserver.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;

public class Webserver {
	public static int PORT = 8000;
	public static String ROOT = System.getProperty("user.dir") + "/www/";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			webserver();
		} catch (Exception e) {
			System.out.println("Failed to run webserver: " + e);
		}
	}

	public static void webserver() throws IOException {
		ServerSocket ss = new ServerSocket(8000);

		while (true) {
			Socket client = ss.accept();
			serveClient(client);
		}		
	}
	
	public static void serveClient(Socket clientSocket) throws IOException {
		// read first line 
		BufferedReader inReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		// get first line 
		String request = inReader.readLine();

		if ( request != null ) {
			// get path version [GET / HTTP/1.1]
			LOG("request '%s'",request);
			String[] items = request.split(" ");
			if ( items[0].toLowerCase().equals("get")) {
				if ( items[1].equals("/") ) {
					items[1] = "/index.html";
				} else if ( items[1].contains("?") ) {
					String[] params = items[1].split("\\?");
					items[1] = params[0];
				}
				File file = new File(ROOT + items[1]);
				LOG("serving file '%s'", file.getAbsoluteFile());
				transfer(clientSocket.getOutputStream(), file);
			}
		}
		clientSocket.close();
	}
	
	public static void transfer(OutputStream outStream, File file) throws IOException {	

		transferHeader(outStream, file);
		if ( file.exists() && file.isFile() ) {			
			InputStream fileStream = new BufferedInputStream(new FileInputStream(file));
			transferData(outStream, fileStream);
		}
	}
	
	public static void transferHeader(OutputStream os, File file) throws IOException {
		StringBuilder sb = new StringBuilder();
		if ( file.exists() && file.isFile() ) {
			sb.append("HTTP/1.1 200 Script output follows\n");
			if ( file.getAbsolutePath().toLowerCase().endsWith(".html") || file.getAbsolutePath().toLowerCase().endsWith(".htm")) {
				sb.append("Content-Type: text/html; charset=UTF-8\n");
			} else if ( file.getAbsolutePath().toLowerCase().endsWith(".css")) {
				sb.append("Content-Type: text/css; charset=UTF-8\n");
			}  else if ( file.getAbsolutePath().toLowerCase().endsWith(".png")) {
				sb.append("Content-Type: image/png\n");
			}  else if ( file.getAbsolutePath().toLowerCase().endsWith(".gif")) {
				sb.append("Content-Type: image/gif\n");
			}  else if ( file.getAbsolutePath().toLowerCase().endsWith(".jpeg") || file.getAbsolutePath().toLowerCase().endsWith(".jpg")) {
				sb.append("Content-Type: image/jpeg\n");
			} else if ( file.getAbsolutePath().toLowerCase().endsWith(".js")) {
				sb.append("Content-Type: text/javascript; charset=UTF-8\n");
			} else {
				LOG("unknown file type '%s'", file.getAbsoluteFile());
				sb.append("Content-Type: text/text; charset=UTF-8\n");
			}
			sb.append("Connection: close\n");
			sb.append("\n");
		} else {
			sb.append("HTTP/1.1 404 File not found\n");
		}

		os.write(sb.toString().getBytes());
	}
	
	public static void transferData(OutputStream os, InputStream is) throws IOException {
		copyFileToSocket(os, is);
	}
	
	public static void LOG(String msg, Object ... args) {
		System.out.println(String.format("LOG: " + msg, args));
	}
	
	public static void copyFileToSocket(OutputStream os, InputStream is) {
		try {
			final int BUFFER_SIZE = 4096;
			byte[] data = new byte[BUFFER_SIZE];

			while ( true ) {
				int read = is.read(data, 0, BUFFER_SIZE);
				
				if ( read == -1 ) break;
				
				os.write(data, 0, read);
			}
		} catch (Exception e) {

		}
	}
}
