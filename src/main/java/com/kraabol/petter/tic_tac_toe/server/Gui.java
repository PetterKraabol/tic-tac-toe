/**
 * 
 */
package com.kraabol.petter.tic_tac_toe.server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Petter
 *
 */
public class Gui extends Application {
	
	public Gui(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button button = new Button();
		button.setText("Click me");
		button.setOnAction(event -> System.out.println("Hello world"));
		
		StackPane root = new StackPane();
		root.getChildren().add(button);
		
		Scene scene = new Scene(root, 300, 250);
		
		primaryStage.setTitle("Server Console");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
