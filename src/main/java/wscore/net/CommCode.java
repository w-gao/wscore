/* Copyright (c) 2018.06 w-gao */

package wscore.net;

/**
 * CommCode
 *
 * @author William Gao
 */
public interface CommCode {

    byte UNI_PING = 0x00;
    byte UNI_PONG = 0x01;

    byte SVC_CONNECT_REQUEST = 0x02;
    byte SVC_CONNECT_RESPONSE = 0x03;
    byte SVC_TRANSFER_NODE = 0x04;

    byte NODE_LOGIN = 0x10;


}
