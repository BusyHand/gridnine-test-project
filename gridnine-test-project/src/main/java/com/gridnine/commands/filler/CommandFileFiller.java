package com.gridnine.commands.filler;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gridnine.commands.Command;

@Configuration
public class CommandFileFiller {

	@Bean
	CommandLineRunner fillFileData() {
		return willDo -> {
			Path path = Path.of("src", "main", "resources", "Command-Open-API.txt");
			Files.deleteIfExists(path);
			Files.createFile(path);
			try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(path, StandardCharsets.UTF_8))) {
				fillFile(printWriter);
			} catch (IOException e) {
				throw new RuntimeException("Произошла ошибка во время записи данных", e);
			}
		};
	}

	private void fillFile(PrintWriter printWriter) {
		printWriter.println("Commands:...      Description:...");
		for (Command command : Command.values()) {
			printWriter.printf("%s - %s\n", command.getName(), command.getDescription());
		}
	}

}
