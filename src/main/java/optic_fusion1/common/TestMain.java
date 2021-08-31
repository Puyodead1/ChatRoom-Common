package optic_fusion1.common;

import optic_fusion1.common.logging.ChatRoomLogger;

public class TestMain {

    public static void main(String[] args) {
        try{
            ChatRoomLogger logger = new ChatRoomLogger("ChatRoomCommon", "common.log");
            logger.info("Info");
            logger.warning("Warning");
            logger.severe("Severe");

            // hack so stuff prints. without this the program terminates before anything is printed
            logger.getConsoleReader().readLine("Press any key to exit");
            logger.shutdown();

            System.exit(0);
        } catch (Exception e) {
            System.err.println("Failed to create logger!");
            e.printStackTrace();
        }
    }
}
