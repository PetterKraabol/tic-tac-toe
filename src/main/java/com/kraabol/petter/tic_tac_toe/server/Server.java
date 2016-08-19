/**
 * 
 */
package com.kraabol.petter.tic_tac_toe.server;

/**
 * @author Petter
 *
 */
public class Server {
	private static GameServer gameServer = new GameServer();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gui gui = new Gui(args);
		
		gameServer.run();
	}
	
}
