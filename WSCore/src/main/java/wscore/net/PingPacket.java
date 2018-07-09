/* Copyright (c) 2018.06 w-gao */

package wscore.net;

/**
 * PingPacket
 *
 * @author William Gao
 */
public class PingPacket extends Packet {

    public int pingId;

    public byte getPacketId() {

        return CommCode.UNI_PING;
    }

    public void decode() {

        super.decode();

        this.pingId = this.unpackInt();
    }
}
