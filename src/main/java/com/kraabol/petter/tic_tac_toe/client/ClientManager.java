package com.kraabol.petter.tic_tac_toe.client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.kraabol.petter.tic_tac_toe.shared.Config;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientManager {
	private Config config = Config.getInstance();
	private Scene scene;
	private Stage stage;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public ClientManager(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
	}
	
	public void closeWindow(Event event) {
		
	}
	
}
