/* Copyright (c) 2018.06 w-gao */

package wscore.engine.data;

/**
 * Service
 *
 * Represents a Service record in the current program.
 *
 * @author William Gao
 */
public class Service {

    public String name;
    public String url;
    public String type;

    public Service(String name, String url, String type) {

        this.name = name;
        this.url = url;
        this.type = type;
    }
}
