/* Copyright (c) 2018.06 w-gao */

package wscore.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wscore.Launcher;

import java.util.Scanner;

/**
 * CommandReader
 *
 * @author William Gao
 */
public class CommandReader implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(CommandReader.class);

    private Scanner scanner = new Scanner(System.in);

    public void run() {

        while (true) {

            try {
                String line = scanner.nextLine();

                String[] args = line.split(" ");
                if (args.length >= 1 && !args[0].equals("")) {

                    switch (args[0]) {

                        case "stop":
                            Launcher.getLauncher().stop();
                            return;

                        case "help":
                        case "?":
                            LOG.warn("Available commands: stop");
                            break;

                        default:
                            LOG.warn("Unknown command: " + args[0] + ". Use `help` to get more information.");
                            break;
                    }
                }

            } catch (Exception e) {
                LOG.error("Exception occurred while reading console input", e);
            }
        }
    }
}
