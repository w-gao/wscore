/* Copyright (c) 2018.06 w-gao */

package wscore.net.service;

import wscore.net.CommCode;
import wscore.net.Packet;

/**
 * ConnectResponsePacket
 *
 * @author William Gao
 */
public class ConnectResponsePacket extends Packet {

    public byte status;

    public byte getPacketId() {

        return CommCode.SVC_CONNECT_RESPONSE;
    }

    public int getLength() {

        return 1 + 1;
    }

    public void encode() {

        super.encode();

        this.packByte(this.status);
    }

    public interface ConnectStatus {

        byte SUCCESS = 0x00;

        byte FAILED_OUTDATED = 0x01;
    }
}
