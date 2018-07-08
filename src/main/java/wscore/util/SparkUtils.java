/* Copyright (c) 2018.06 w-gao */

package wscore.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import spark.Service;
import spark.Spark;
import spark.embeddedserver.jetty.EmbeddedJettyServer;
import spark.http.matching.MatcherFilter;
import spark.route.Routes;
import spark.staticfiles.StaticFilesConfiguration;

/**
 * SparkUtils
 *
 * @author William Gao
 */
public class SparkUtils {

    /**
     * https://gist.github.com/Concision/f175bb5dd42c524bedd633af80903b9f
     */
    public static void fixNotFoundRoute() {
        try {
            Method getInstanceMethod = Spark.class.getDeclaredMethod("getInstance");
            getInstanceMethod.setAccessible(true);
            Service service = (Service) getInstanceMethod.invoke(null);

            Field serverField = Service.class.getDeclaredField("server");
            serverField.setAccessible(true);
            Object embeddedServer = serverField.get(service);

            if (!(embeddedServer instanceof EmbeddedJettyServer)) {

                throw new UnsupportedOperationException("Only EmbeddedJettyServer is supported");
            }
            EmbeddedJettyServer embeddedJettyServer = (EmbeddedJettyServer) embeddedServer;

            Field jettyServerField = EmbeddedJettyServer.class.getDeclaredField("server");
            jettyServerField.setAccessible(true);
            Server server = (Server) jettyServerField.get(embeddedJettyServer);

            HandlerList handler = (HandlerList) server.getHandler();
            Handler[] handlers = handler.getHandlers();

            if (2 <= handlers.length) {
                Handler websocketHandler = handlers[1];
                ServletContextHandler websocketContextHandler = (ServletContextHandler) websocketHandler;
                websocketContextHandler.addFilter(
                        new FilterHolder(new MatcherFilter(Routes.create(), new StaticFilesConfiguration(), false, false)),
                        "/*",
                        EnumSet.of(DispatcherType.REQUEST)
                );
            }
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("failed to inject 404 route handling", exception);
        }
    }
}
