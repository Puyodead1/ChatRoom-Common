package optic_fusion1.common.logging;

import jline.console.ConsoleReader;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CustomLogger extends Logger {

    private final LogDispatcher dispatcher = new LogDispatcher(this);

    public CustomLogger(String name, String filePattern, ConsoleReader reader) {
        super(name, null);
        setLevel(Level.ALL);

        try {
            FileHandler fileHandler = new FileHandler(filePattern, 1 << 24, 8, true);
            fileHandler.setLevel(Level.parse(System.getProperty("optic_fusion1.chatroom.client.file-log-level", "INFO")));
            fileHandler.setFormatter(new ConciseFormatter(false));
            addHandler(fileHandler);

            ColoredWriter consoleHandler = new ColoredWriter(reader);
            consoleHandler.setLevel(Level.parse(System.getProperty("optic_fusion1.chatroom.client.console-log-level", "INFO")));
            consoleHandler.setFormatter(new ConciseFormatter(true));
            addHandler(consoleHandler);
        } catch (IOException ex) {
            System.err.println("Could not register logger!");
            ex.printStackTrace();
        }

        dispatcher.start();
    }

    @Override
    public void log(LogRecord record) {
        dispatcher.queue(record);
    }

    void doLog(LogRecord record) {
        super.log(record);
    }
}
