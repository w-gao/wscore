/* Copyright (c) 2018.06 w-gao */

package wscore.controller;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import wscore.engine.Engine;
import wscore.engine.data.Service;


/**
 * ServiceController
 *
 * @author William Gao
 */
public class ServiceController {

    public static Object getServices(Request req, Response res) {

        String gameType = req.queryParams("game_type");
        Service[] results = Engine.getInstance().getServices(gameType);

        return new Gson().toJson(results);
    }
}
