/* Copyright (c) 2018.06 w-gao */

package wscore.net.service;

import wscore.net.CommCode;
import wscore.net.Packet;

/**
 * ConnectRequestPacket
 *
 * @author William Gao
 */
public class ConnectRequestPacket extends Packet {

    public String version;

    public byte getPacketId() {

        return CommCode.SVC_CONNECT_REQUEST;
    }

    public void decode() {

        super.decode();

        this.version = this.unpackString();
    }

}
