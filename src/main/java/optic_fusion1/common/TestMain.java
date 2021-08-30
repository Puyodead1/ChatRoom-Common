package optic_fusion1.common;

import optic_fusion1.common.logging.ChatRoomLogger;

public class TestMain {

    public static void main(String[] args) {
        try{
            ChatRoomLogger logger = new ChatRoomLogger("ChatRoomCommon", "common.log");
            logger.info("Info");
            logger.warning("Warning");
            logger.severe("Severe");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
