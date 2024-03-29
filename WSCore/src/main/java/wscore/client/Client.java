/* Copyright (c) 2018.06 w-gao */

package wscore.client;

import org.eclipse.jetty.websocket.api.Session;
import wscore.net.Packet;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Client
 *
 * @author William Gao
 */
public class Client {

    private final Session session;

    private String username;

    public Client(Session session, String username) {

        this.session = session;
        this.username = username;

        System.out.println("USER Logged in: " + username);
    }

    public void sendPacket(Packet packet) {

        try {
            this.session.getRemote().sendBytes(ByteBuffer.wrap(packet.buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
