package com.kraabol.petter.tic_tac_toe.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Client extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(new StackPane());
		
		ClientManager clientManager = new ClientManager(scene, stage);
		
		stage.setScene(scene);
		stage.setTitle("Server log");
		stage.show();
		
		stage.setOnCloseRequest(e -> {
			clientManager.closeWindow(e);
			System.exit(0);
		});
	}
	
}
