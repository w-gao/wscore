/* Copyright (c) 2018.06 w-gao */

package wscore.net;

/**
 * PongPacket
 *
 * @author William Gao
 */
public class PongPacket extends Packet {

    public byte getPacketId() {

        return CommCode.UNI_PONG;
    }

    public int getLength() {

        return 1;
    }

    public void encode() {

        super.encode();
    }
}
