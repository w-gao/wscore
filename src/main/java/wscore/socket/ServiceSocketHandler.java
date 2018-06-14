/* Copyright (c) 2018.06 w-gao */

package wscore.socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import wscore.net.CommCode;
import wscore.net.Packet;
import wscore.net.PingPacket;
import wscore.net.PongPacket;
import wscore.util.Binary;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * ServiceSocketHandler
 *
 * The Service WebSocket is to provide generic information for the client when a client
 * requests to connect. Usually Ping packets will be sent by the client to determine the
 * best Node WebSocket to connect to. If specific information is requested (for example:
 * game ids), the Service WebSocket will process and respond with appropriate data.
 *
 * @author William Gao
 */
@WebSocket
public class ServiceSocketHandler {

    @OnWebSocketMessage
    public void onBinaryMessage(Session session, byte[] buffer, int offset, int length) {

        System.out.println(session.getRemoteAddress() + " -]-- Received binary data: ");
        Binary.hexDump(buffer);

        Packet pk;

        switch (buffer[0]) {

            case CommCode.UNI_PING:
                pk = new PingPacket();
                pk.buffer = buffer;
                pk.decode();

                // respond with pong
                Packet pongPacket = new PongPacket();
                pongPacket.encode();

                this.sendPacket(session, pongPacket);
                break;
        }

    }

    private void sendPacket(Session session, Packet packet) {

        try {
            session.getRemote().sendBytes(ByteBuffer.wrap(packet.buffer));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
