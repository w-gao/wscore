/* Copyright (c) 2018.06 w-gao */

package wscore.engine;

import wscore.data.node.Node;

/**
 * ServiceManager
 *
 * @author William Gao
 */
public class ServiceManager {

    private static ServiceManager instance;

    public static ServiceManager getInstance() {

        if (instance == null) {
            instance = new ServiceManager();
        }

        return instance;
    }

    public void addNodeServer(Node node) {

    }

}
