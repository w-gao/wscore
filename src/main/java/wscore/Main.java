/* Copyright (c) 2018.06 w-gao */

package wscore;

import wscore.console.CommandReader;

/**
 * Main
 *
 * @author William Gao
 */
public class Main {

    /**
     * Entry of program
     */
    public static void main(String[] args) {

        Launcher.getLauncher()
                .addServiceServer("/service")
                .addNodeServer("/n1")
                .setPort(8080)
                .start();

        // Input handler
        new Thread(new CommandReader()).start();
    }

}
