package com.kraabol.petter.tic_tac_toe.shared;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public final class Logger {
	private static Logger instance = null;
	private String logFile = "src/main/resources/server/log.ini";
	
	public static synchronized Logger getInstance() {
		if (instance == null) instance = new Logger();
		return instance;
	}
	
	public void add(String message) {
		LocalDateTime dateTime = LocalDateTime.now();
		String line = "[" + dateTime + "] " + message + "\n";
		
		try {
		    Files.write(Paths.get(this.logFile), line.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}
	
	public Stream<String> stream() {
		try {
			return (Stream<String>)Files.lines(Paths.get(this.logFile));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void clear() {
		try {
			new PrintWriter(this.logFile).close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
