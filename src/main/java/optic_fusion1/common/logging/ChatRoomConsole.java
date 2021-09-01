package optic_fusion1.common.logging;

import net.minecrell.terminalconsole.SimpleTerminalConsole;
import optic_fusion1.common.TestMain;
import org.apache.logging.log4j.LogManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

public class ChatRoomConsole extends SimpleTerminalConsole {

    @Override
    protected LineReader buildReader(LineReaderBuilder builder) {
        return super.buildReader(builder.appName(TestMain.class.getName()));
    }

    @Override
    protected boolean isRunning() {
        return true;
    }

    @Override
    protected void runCommand(String s) {
        LogManager.getLogger(TestMain.class.getName()).info(s);
    }

    @Override
    protected void shutdown() {
        System.exit(0);
    }
}
