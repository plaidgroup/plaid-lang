package plaid.examples.webserver.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
				}
				File file = new File(ROOT + items[1]);
				LOG("serving file '%s'", file.getAbsoluteFile());
				if ( file.exists() && file.isFile() ) {
					transfer(clientSocket.getOutputStream(), file);
				}
			}
		}
		clientSocket.close();
	}
	
	public static void transfer(OutputStream outStream, File file) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		Writer writer = new OutputStreamWriter(outStream);

		transferHeader(writer, file);
		transferData(writer, fileReader, file.length());
	}
	
	public static void transferHeader(Writer writer, File file) throws IOException {
		writer.append("HTTP/1.1 200 Script output follows\n");
		if ( file.getAbsolutePath().toLowerCase().endsWith(".html") || file.getAbsolutePath().toLowerCase().endsWith(".htm")) {
			writer.append("Content-Type: text/html; charset=UTF-8\n");
		} else if ( file.getAbsolutePath().toLowerCase().endsWith(".css")) {
			writer.append("Content-Type: text/css; charset=UTF-8\n");
		}  else if ( file.getAbsolutePath().toLowerCase().endsWith(".png")) {
			writer.append("Content-Type: image/png\n");
		}  else if ( file.getAbsolutePath().toLowerCase().endsWith(".gif")) {
			writer.append("Content-Type: image/gif\n");
		}  else if ( file.getAbsolutePath().toLowerCase().endsWith(".jpeg") || file.getAbsolutePath().toLowerCase().endsWith(".jpg")) {
			writer.append("Content-Type: image/jpeg\n");
		} else if ( file.getAbsolutePath().toLowerCase().endsWith(".js")) {
			writer.append("Content-Type: text/javascript; charset=UTF-8\n");
		} else {
			LOG("unknown file type '%s'", file.getAbsoluteFile());
			writer.append("Content-Type: text/text; charset=UTF-8\n");
		}
		writer.append("Connection: close\n");
		writer.append("\n");
	}
	
	public static void transferData(Writer outWriter, Reader inReader, long count) throws IOException {
		if ( count > 0 ) {
			CharBuffer cb = CharBuffer.allocate((int)Math.max(4096, count));

			int result = inReader.read(cb); 
			if ( result >= 0 ) {
				outWriter.write(cb.array());
				count -= result;
				transferData(outWriter, inReader, count);
			}			
		} else {
			outWriter.flush();
		}
	}
	
	public static void LOG(String msg, Object ... args) {
		System.out.println(String.format("LOG: " + msg, args));
	}
}
