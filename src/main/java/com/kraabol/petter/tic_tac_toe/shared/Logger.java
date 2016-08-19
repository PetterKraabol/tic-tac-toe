/**
 * 
 */
package com.kraabol.petter.tic_tac_toe.shared;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Petter
 *
 */
public final class Logger {
	private static Logger instance = null;
	private String logFile = "src/main/resources/server/log.ini";
	
	public static synchronized Logger getInstance() {
		if (instance == null) instance = new Logger();
		return instance;
	}
	
	public void add(String message) {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.logFile))) {
			
			LocalDateTime dateTime = LocalDateTime.now();
			String line = "[" + dateTime + "] " + message;
			
			writer.write(line);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(this.logFile));
			
			LocalDateTime dateTime = LocalDateTime.now();
			lines.add("[" + dateTime + "] " + message);
			
			Files.write(Paths.get(this.logFile), lines);
			
		} catch (IOException e) {
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
			PrintWriter printWriter = new PrintWriter(this.logFile);
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
