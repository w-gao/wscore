/* Copyright (c) 2018.06 w-gao */

package wscore.route;

import wscore.controller.ServiceController;

import static spark.Spark.*;

/**
 * Route
 *
 * @author William Gao
 */
public class Route {

    public static void init() {

        get("/services", ServiceController::getServices);

//        notFound((req, res) -> {
//            res.redirect("/");
//            return "404 Not found.. Redirecting";
//        });
    }
}
