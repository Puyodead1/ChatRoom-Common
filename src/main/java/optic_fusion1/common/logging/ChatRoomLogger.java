package optic_fusion1.common.logging;

import jline.console.ConsoleReader;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.*;

public class ChatRoomLogger extends Logger {

    private final LogDispatcher dispatcher = new LogDispatcher(this);
    private final ConsoleReader consoleReader;

    public ChatRoomLogger(String name, String filePattern) throws IOException {
        super(name, null);
        setLevel(Level.ALL);

        System.setProperty("library.jansi.version", name);

        AnsiConsole.systemInstall();
        consoleReader = new ConsoleReader();
        consoleReader.setExpandEvents(false);
        consoleReader.setHandleUserInterrupt(true);

        JDK14LoggerFactory.LOGGER = this;
        System.setErr(new PrintStream(new LoggingOutputStream(this, Level.SEVERE), true));
        System.setOut(new PrintStream(new LoggingOutputStream(this, Level.INFO), true));

        try {
            FileHandler fileHandler = new FileHandler(filePattern, 1 << 24, 8, true);
            fileHandler.setLevel(Level.parse(System.getProperty("optic_fusion1.chatroom.client.file-log-level", "INFO")));
            fileHandler.setFormatter(new ConciseFormatter(false));
            addHandler(fileHandler);

            ColoredWriter consoleHandler = new ColoredWriter(consoleReader);
            consoleHandler.setLevel(Level.parse(System.getProperty("optic_fusion1.chatroom.client.console-log-level", "INFO")));
            consoleHandler.setFormatter(new ConciseFormatter(true));
            addHandler(consoleHandler);
        } catch (IOException ex) {
            System.err.println("Could not register logger!");
            ex.printStackTrace();
        }

        dispatcher.start();
    }
    /**
     * Interrupts threads and stuff
     */
    public void shutdown() {
        dispatcher.interrupt();
        for (Handler handler : getHandlers()) {
            handler.close();
        }
    }

    @Override
    public void log(LogRecord record) {
        dispatcher.queue(record);
    }

    void doLog(LogRecord record) {
        super.log(record);
    }

    public ConsoleReader getConsoleReader() {
        return consoleReader;
    }
}
