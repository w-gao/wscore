/* Copyright (c) 2018.06 w-gao */

package wscore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import wscore.route.Route;
import wscore.socket.NodeSocketHandler;
import wscore.socket.ServiceSocketHandler;

import static spark.Spark.*;

/**
 * Launcher
 *
 * @author William Gao
 */
public class Launcher {

    private static final Logger LOG = LoggerFactory.getLogger(Launcher.class);

    /* singleton instance */
    private static Launcher launcher;

    private static boolean started = false;

    /**
     * Get or create a singleton instance of the Launcher
     */
    public static Launcher getLauncher() {

        if (launcher == null) {
            launcher = new Launcher();
        }

        return launcher;
    }

    /**
     * Add a service WebSocket server
     */
    public Launcher addServiceServer(String path) {

        Launcher.requireBeforeStart();

        webSocket(path, ServiceSocketHandler.class);

        return this;
    }

    /**
     * Add a node WebSocket server
     */
    public Launcher addNodeServer(String path) {

        Launcher.requireBeforeStart();

        webSocket(path, NodeSocketHandler.class);

        return this;
    }

    /**
     * Set the port that the server will listen to
     */
    public Launcher setPort(int port) {

        Launcher.requireBeforeStart();

        port(port);

        return this;
    }

    public synchronized void start() {

        staticFileLocation("public");

        Route.init();

        // initialize server if not already
        init();

        Launcher.started = true;

        LOG.warn("Loaded, and ready!");
    }

    public synchronized void stop() {

        LOG.warn("Stopping...");
        Spark.stop();

        Launcher.started = false;
    }

    private static void requireBeforeStart() {

        if (Launcher.started) {
            throw new IllegalStateException("server has already started");
        }
    }
}
