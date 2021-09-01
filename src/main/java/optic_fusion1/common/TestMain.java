package optic_fusion1.common;

import optic_fusion1.common.logging.ChatRoomConsole;
import optic_fusion1.common.logging.ChatRoomLogger;
import org.fusesource.jansi.AnsiConsole;

import java.util.logging.Logger;

public class TestMain {

    private static Logger logger = null;

    public static void main(String[] args) {
        System.setProperty("library.jansi.version", "ChatRoom");
        AnsiConsole.systemInstall();

        logger = ChatRoomLogger.create();
        Thread thread = new Thread(() -> new ChatRoomConsole().start());
        thread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(thread::interrupt));

        logger.info("TEST");
        logger.severe("TEST");
        logger.warning("TEST");
    }
}
