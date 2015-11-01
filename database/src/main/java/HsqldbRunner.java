import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//TODO: Fix this --> ERROR StatusLogger No log4j2 configuration file found. Using default configuration: logging only errors to the console.
public class HsqldbRunner {

    private static final Logger logger = LoggerFactory.getLogger(HsqldbRunner.class);

    private void run(String[] args) {
        if (args.length < 2) {
            logger.error("Usage: HsqldbRunner <folder-name> <createFlag ('true'|'false')>");
            logger.error("Usage: For in-memory use, set folder-name to 'target'");
            System.exit(0);
        }

        String dbFolder = args[0].trim();

        dbFolder = dbFolder.replaceAll("\\\\", "/");
        if (!dbFolder.endsWith("/")) {
            dbFolder = dbFolder + "/";
        }
        dbFolder = dbFolder + "keystore";

        HsqlProperties hsqlProperties = new HsqlProperties();
        hsqlProperties.setProperty("server.dbname.0", "keystore");
        hsqlProperties.setProperty("server.port", "9001");
        hsqlProperties.setProperty("server.database.0", "file:" + dbFolder);
        hsqlProperties.setProperty("server.silent", "false");

        boolean create = args[1].trim().equalsIgnoreCase("true");
        if (create) {
            hsqlProperties.setProperty("create", "true");
            logger.info("Creating database at: " + dbFolder);
        } else {
            logger.info("Using database at: " + dbFolder);
        }

        logger.info("Starting Database...");

        Server server = new Server();
        try {
            server.setProperties(hsqlProperties);
        } catch (IOException e) {
            logger.error("Server could not be started.", e);
        } catch (ServerAcl.AclFormatException e) {
            logger.error("Server could not be started", e);
        }
        server.setLogWriter(null); //disable server message logging
        server.setErrWriter(null); //disable server message logging
        server.start();
    }

    public static void main(String[] args) {
        HsqldbRunner hsqldbRunner = new HsqldbRunner();
        hsqldbRunner.run(args);
    }

}
