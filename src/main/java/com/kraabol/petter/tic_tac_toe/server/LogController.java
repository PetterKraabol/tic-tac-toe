package com.kraabol.petter.tic_tac_toe.server;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.kraabol.petter.tic_tac_toe.shared.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class LogController extends Thread implements Initializable {
	private Logger log = Logger.getInstance();
	private int totalLines = 0;
	
	@FXML private Button clearButton;
	@FXML private Button testButton;
	@FXML private TextArea output;
	
	public LogController() {
		System.out.println(("Controller running."));
	}
	
	@FXML
	private void clearConsole(ActionEvent event) {
		this.log.clear();
		this.totalLines = 0;
		this.output.setText(null);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.clearButton.setText("Clear");
		this.testButton.setText("Log test");
	}
	
	public void initManager(final ServerManager manager) {
		this.clearButton.setOnAction(event -> this.clearConsole(event));
		this.testButton.setOnAction(event -> this.log.add("Log test"));
	}
	
	public void run() {
		System.out.println("LogController thread running");
		
		while (true) {
			List<String> lines = new ArrayList<String>();
			
			this.log.stream().forEach((String line) -> {
				lines.add(line);
			});
			
			// If there are new lines, add them
			for (; this.totalLines < lines.size(); this.totalLines++) {
				this.output.appendText(lines.get(totalLines) + "\n");
			}
			
			// Next update in 1000ms
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
