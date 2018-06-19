/* Copyright (c) 2018.06 w-gao */

package wscore.socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import wscore.client.Client;
import wscore.net.CommCode;
import wscore.net.Packet;
import wscore.net.PingPacket;
import wscore.net.PongPacket;
import wscore.net.node.LoginPacket;
import wscore.util.Binary;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * NodeSocketHandler
 *
 * @author William Gao
 */
@WebSocket
public class NodeSocketHandler {

    private final Map<InetSocketAddress, Client> sessions = new HashMap<>();

    @OnWebSocketMessage
    public void onBinaryMessage(Session session, byte[] buffer, int offset, int length) {

        System.out.println(session.getRemoteAddress() + " -]-- Received binary data: ");
        Binary.hexDump(buffer);

        Packet pk;
        byte id = buffer[0];

        if (id <= 0x10) {

            switch (id) {

                case CommCode.UNI_PING:
                    pk = new PingPacket();
                    pk.buffer = buffer;
                    pk.decode();

                    System.out.println("Ping received: " + ((PingPacket) pk).pingId);

                    // respond with pong
                    Packet pongPacket = new PongPacket();
                    pongPacket.encode();

                    this.sendPacket(session, pongPacket);
                    break;

                case CommCode.NODE_LOGIN:

                    pk = new LoginPacket();
                    pk.buffer = buffer;
                    pk.decode();

                    this.sessions.put(session.getRemoteAddress(), new Client(session, ((LoginPacket) pk).username));

                    break;
            }
        } else {

            Client client = this.sessions.get(session.getRemoteAddress());

            if (client == null) {
                System.out.println("trying to send node packet before login. ");
                return;
            }

            switch (id) {


            }
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
