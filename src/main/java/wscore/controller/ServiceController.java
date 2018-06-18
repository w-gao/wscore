/* Copyright (c) 2018.06 w-gao */

package wscore.controller;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import wscore.data.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ServiceController
 *
 * @author William Gao
 */
public class ServiceController {

    private static List<Service> services = new ArrayList<>();

    static {
        services.add(new Service("def", "ws://localhost:8080/service", "default"));
    }

    public static Object getServices(Request req, Response res) {

        Service[] results = null;

        String gameType = req.queryParams("game_type");

        if (gameType != null) {
            results = services.stream().filter(svc -> svc.type.equals(gameType)).toArray(Service[]::new);
        }

        if (results == null) {
            results = new Service[]{};
        }

        return new Gson().toJson(results);
    }
}
