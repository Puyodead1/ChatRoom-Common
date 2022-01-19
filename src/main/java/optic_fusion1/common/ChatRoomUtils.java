package optic_fusion1.common;

import optic_fusion1.common.protos.ErrorPacket;
import optic_fusion1.common.protos.Packet;

public class ChatRoomUtils {
    public static Packet makeErrorPacket(ErrorPacket.Type errorType, String description) {
        ErrorPacket.Builder errorPacket = ErrorPacket.newBuilder();
        errorPacket.setErrorType(errorType);
        errorPacket.setDescription(description);

        Packet.Builder packet = Packet.newBuilder();
        packet.setPacketType(Packet.Type.ERROR);
        packet.setData(errorPacket.build().toByteString());

        return packet.build();
    }
}
