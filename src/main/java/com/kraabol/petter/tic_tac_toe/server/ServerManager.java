package com.kraabol.petter.tic_tac_toe.server;

import java.io.IOException;

import com.kraabol.petter.tic_tac_toe.shared.Logger;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerManager {
	private Logger log = Logger.getInstance();
	private Scene scene;
	private Stage stage;

	public ServerManager(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
		
		this.showConsole();
	}
	
	public void closeWindow(Event event) {
		this.clearConsole();
	}
	
	public void showConsole() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/server/views/main.fxml"));
			this.scene.setRoot((Parent) loader.load());
			
			stage.setTitle("Server console");
			stage.sizeToScene();
			
			// ConsoleController
			LogController controller = loader.<LogController>getController();
			controller.initManager(this);
			controller.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearConsole() {
		this.log.clear();
	}
	
}
