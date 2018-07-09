/* Copyright (c) 2018.06 w-gao */

package wscore.engine;

import wscore.engine.data.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Engine
 *
 * @author William Gao
 */
public class Engine {

    private static Engine instance;

    private List<Service> services = new ArrayList<>();

    public static Engine getInstance() {

        if (instance == null) {
            instance = new Engine();
        }

        return instance;
    }

    /**
     * Add a Service instance
     */
    public void addServiceServer(Service service) {

        this.services.add(service);
    }

    /**
     * Get a list of all the current active Service servers
     */
    public Service[] getServices() {

        return services.toArray(new Service[0]);
    }

    /**
     * Get a list of the current active Service servers by type
     */
    public Service[] getServices(String type) {

        if (type == null) {

            return new Service[]{};
        }

        return services.stream().filter(s -> s.type.equals(type)).toArray(Service[]::new);
    }

}
