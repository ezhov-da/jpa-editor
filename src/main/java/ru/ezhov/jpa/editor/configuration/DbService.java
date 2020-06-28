package ru.ezhov.jpa.editor.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DbService {
    private static final Logger LOG = LoggerFactory.getLogger(DbService.class);
    private static final String APPLICATION_DIRECTORY = System.getProperty("user.home") + File.separator + ".jpa-editor";

    public void copyDbToUser() throws DbServiceException {
        final File directory = new File(APPLICATION_DIRECTORY);
        directory.mkdirs();
        final File file = new File(APPLICATION_DIRECTORY, "sample.mv.db");
        if (file.exists()) {
            LOG.debug("File '{}' already exists", file.getAbsolutePath());
        } else {
            try (
                    InputStream inputStream = this.getClass().getResourceAsStream("/sample.mv.db");
                    FileOutputStream fileOutputStream = new FileOutputStream(file)
            ) {
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                fileOutputStream.write(bytes);
                fileOutputStream.flush();

                LOG.debug("File '{}' copied", file.getAbsolutePath());
            } catch (Exception e) {
                throw new DbServiceException("Error copy DB to '" + APPLICATION_DIRECTORY + "'", e);
            }
        }
    }
}
