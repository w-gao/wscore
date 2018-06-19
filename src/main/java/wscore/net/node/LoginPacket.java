/* Copyright (c) 2018.06 w-gao */

package wscore.net.node;

import wscore.net.CommCode;
import wscore.net.Packet;

/**
 * LoginPacket
 *
 * @author William Gao
 */
public class LoginPacket extends Packet {

    public String username;

    public byte getPacketId() {

        return CommCode.NODE_LOGIN;
    }

    public void decode() {

        super.decode();

        this.username = this.unpackString();
    }
}
