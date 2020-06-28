package ru.ezhov.jpa.editor.h2db;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class H2DbService {
    private static final Logger LOG = LoggerFactory.getLogger(H2DbService.class);

    private Server server;

    public void start() throws H2DbServiceException {
        this.start(new String[]{});
    }

    public void start(String[] args) throws H2DbServiceException {
        try {
            this.server = Server.createTcpServer(args).start();
            LOG.info("H2DB server started");
        } catch (SQLException e) {
            throw new H2DbServiceException("H2DB start error", e);
        }
    }

    public void stop() {
        if (this.server != null) {
            this.server.stop();
            LOG.info("H2DB server stoped");
        }
    }
}
