package com.kraabol.petter.tic_tac_toe.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kraabol.petter.tic_tac_toe.shared.Logger;

public class Game extends Thread {
	private Logger log = Logger.getInstance();
	private List<Player> players = new ArrayList<Player>();
	private Board board = new Board();
	
	public Game(List<Player> players) {
		this.players = players;
	}
	
	private void broadcast(String message) {
		players.forEach((Player player) -> player.send(message));
	}
	
	public void run() {
		this.log.add("A new game has been started");
		
		// Let users know the game has started
		this.broadcast("START");
		
		// Game session
		while (true) {
			
			boolean gameIsOver = false;
			
			if (board.draw()) { this.broadcast("DRAW"); gameIsOver = true; }
			if (board.winner() != null) { this.broadcast("WINNER " + board.winner().getUsername()); gameIsOver = true; }
			
			// Restart or end game
			if (gameIsOver) {
				
				boolean end = false;
				int restart = 0;
				
				while (!end || restart != 2) {
					
					for (Player player: players) {
						try {
							
							String[] line = player.getInputStream().readLine().split(" ");
							
							if (line[0] == "RESTART") {
								board.reset();
								restart++;
							} else if (line[0] == "END") {
								end = true;
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
				
				// If a player wants to end, end the game
				if (end) {
					this.broadcast("END");
					break;
				} else {
					this.broadcast("RESTART");
					gameIsOver = false;
				}
			}
			
			// Change player turn
			for (Player player: this.players) {
				
				// Continue while game has not been won is resulted in a draw
				while (true) {
					
					try {
						
						String[] input = player.getInputStream().readLine().split(" ");
						
						// MARK <position>
						if (input[0] == "MARK") {
							
							int position = Integer.parseInt(input[1]);
							
							if (!board.mark(position, player)) {
								player.send("INVALID_MOVE");
							} else {
								this.broadcast("MARK " + position);
								
								// Check if someone won
								if (board.draw() || board.winner() != null) {
									break;
								} else {
									continue;
								}
							}
							
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			
			// Return to top -> check if game has been won
			
		}
	}
	
}
