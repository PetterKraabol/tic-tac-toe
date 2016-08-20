package com.kraabol.petter.tic_tac_toe.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import com.kraabol.petter.tic_tac_toe.shared.Config;
import com.kraabol.petter.tic_tac_toe.shared.Logger;

public class GameServer implements Runnable {
	private Config config = Config.getInstance();
	private Logger log = Logger.getInstance();
	
	// Game server listener
	private ServerSocket listener;

	GameServer() {
		this.log.add("Game server running on port " + config.getProperty("game-port"));
	}
	
	public void run() {
		System.out.println("GameServer running.");
		
		// Server socket
		try {
			this.listener = new ServerSocket(Integer.parseInt(this.config.getProperty("game-port")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// Player queue
		try {
			
			// Wait for new players and start a new game once a queue is full
			while (true) {
				
				// Player queue
				ArrayList<Player> playerQueue = new ArrayList<Player>();
				
				// Wait for players
				while (playerQueue.size() <= Integer.parseInt(config.getProperty("queue-size"))) {
					playerQueue.add(new Player(listener.accept()));
				}
				
				// Initialize a new game
				Game game = new Game(playerQueue);
				
				// Start game
				game.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				listener.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
