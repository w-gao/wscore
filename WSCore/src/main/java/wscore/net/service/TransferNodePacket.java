/* Copyright (c) 2018.06 w-gao */

package wscore.net.service;

import wscore.net.CommCode;
import wscore.net.Packet;

/**
 * TransferNodePacket
 *
 * @author William Gao
 */
public class TransferNodePacket extends Packet {

    public String url;

    public byte getPacketId() {

        return CommCode.SVC_TRANSFER_NODE;
    }

    public int getLength() {

        return 1 + 2 + 2 * this.url.length();
    }

    public void encode() {

        super.encode();

        this.packString(this.url);
    }
}
