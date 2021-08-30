package optic_fusion1.common.logging;

import jline.console.ConsoleReader;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatRoomLogger {

    public ConsoleReader consoleReader;
    public Logger logger;

    public ChatRoomLogger(String name, String filePattern) throws IOException {
        System.setProperty("library.jansi.version", name);

        AnsiConsole.systemInstall();
        consoleReader = new ConsoleReader();
        consoleReader.setExpandEvents(false);
        consoleReader.setHandleUserInterrupt(true);

        logger = new CustomLogger(name, filePattern, consoleReader);
        JDK14LoggerFactory.LOGGER = logger;
        System.setErr(new PrintStream(new LoggingOutputStream(logger, Level.SEVERE), true));
        System.setOut(new PrintStream(new LoggingOutputStream(logger, Level.INFO), true));
    }

    public ConsoleReader getConsoleReader() {
        return consoleReader;
    }

    public Logger getLogger() {
        return logger;
    }
}
