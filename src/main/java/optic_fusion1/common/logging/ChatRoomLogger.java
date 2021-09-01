package optic_fusion1.common.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.io.IoBuilder;

import java.util.logging.Handler;
import java.util.logging.Logger;

public final class ChatRoomLogger {

    private ChatRoomLogger() {

    }

    public static Logger create() {
        org.apache.logging.log4j.Logger redirect = LogManager.getRootLogger();
        System.setOut(IoBuilder.forLogger(redirect).setLevel(Level.INFO).buildPrintStream());
        System.setErr(IoBuilder.forLogger(redirect).setLevel(Level.ERROR).buildPrintStream());

        Logger root = Logger.getLogger("");
        root.setUseParentHandlers(false);

        for(Handler handler : root.getHandlers()) {
            root.removeHandler(handler);
        }

        root.setLevel(java.util.logging.Level.ALL);
        root.addHandler(new Log4JLogHandler());

        return Logger.getLogger("ChatRoot");
    }
}
