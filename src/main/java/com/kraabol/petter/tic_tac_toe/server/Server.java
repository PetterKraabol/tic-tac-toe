package com.kraabol.petter.tic_tac_toe.server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Server extends Application {
	private static Thread gameServerThread = new Thread(new GameServer());

	public static void main(String[] args) {
		gameServerThread.start();
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(new StackPane());
		
		ServerManager serverManager = new ServerManager(scene, stage);
		
		stage.setScene(scene);
		stage.setTitle("Server log");
		stage.show();
		
		stage.setOnCloseRequest(e -> {
			serverManager.closeWindow(e);
			System.exit(0);
		});
	}
	
}
