/**
 * 
 */
package com.kraabol.petter.tic_tac_toe.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.kraabol.petter.tic_tac_toe.shared.Logger;

/**
 * @author Petter
 *
 */
public class Player extends Thread {
	private Logger log = Logger.getInstance();
	private String username;
	private PrintWriter out;
	private BufferedReader in;
	
	public Player(Socket socket) {
		
		try {
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream(), true);
			
			// Welcome user
			out.println("WAITING");
			
		} catch (IOException e) {
			this.log.add("Player (" + this.getUsername() + ") lost connection: " + e.getMessage());
			e.printStackTrace();
		}
		
		this.start();
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public BufferedReader getInputStream() {
		return this.in;
	}
	
	public void send(String message) {
		this.out.println(message);
	}
	
	public void run() {
		
		// Request user inforamtion
		while (true) {
			
			try {
				String[] line = in.readLine().split(" ");
				
				if (line.length < 10) {
					System.out.println(line[20]);
				}
				
				// Username
				if (line[0] == "USERNAME") {
					this.setUsername(line[1]);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
