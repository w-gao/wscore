/* Copyright (c) 2018.06 w-gao */

package wscore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import wscore.engine.Engine;
import wscore.engine.data.Service;
import wscore.route.Route;
import wscore.socket.NodeSocketHandler;
import wscore.socket.ServiceSocketHandler;
import wscore.util.SparkUtils;

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

    private String baseUrl;

    private int port;

    private boolean secure = false;

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
     * Set the base url
     * needs to set before {@code addServiceServer} and {@code addNodeServer}
     */
    public Launcher setBaseUrl(String url) {

        Launcher.requireBeforeStart();

        this.baseUrl = url;

        return this;
    }

    /**
     * Set the port that the server will listen to
     */
    public Launcher setPort(int port) {

        Launcher.requireBeforeStart();

        this.port = port;

        return this;
    }

    /**
     * Set if the webSocket requires a secure connection
     */
    public Launcher setSecure(boolean secure) {

        Launcher.requireBeforeStart();

        this.secure = secure;

        return this;
    }

    /**
     * Add a service WebSocket server
     */
    public Launcher addServiceServer(String path, String name, String type) {

        Launcher.requireBeforeStart();

        webSocket(path, ServiceSocketHandler.class);

        Engine.getInstance().addServiceServer(new Service(name, (this.secure ? "wss://" : "ws://") + this.baseUrl + ":" + this.port + path, type));

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

    public synchronized void start() {

        staticFileLocation("public");

        port(this.port);

        Route.init();

        // initialize server if not already
        init();

        Spark.awaitInitialization();
        SparkUtils.fixNotFoundRoute();

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
