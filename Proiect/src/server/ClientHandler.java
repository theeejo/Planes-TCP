package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	
	public ClientHandler(Socket socket) throws IOException {
		this.socket = socket;
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.writer = new PrintWriter(socket.getOutputStream());
	}

	@Override
	public void run() {
		while (!socket.isClosed()) {
			try {
				String command = reader.readLine();
				if ("exit".equals(command.strip())) {
					socket.close();
				} else {
					System.out.println(command);
					writer.println(command.toUpperCase());
					writer.flush();
				}	
			} catch (Exception e) {
				writer.println(e.getMessage());
				writer.flush();
			}	
		}		
	}
	
}