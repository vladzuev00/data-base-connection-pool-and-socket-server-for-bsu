package by.bsu.zuevvlad.seventhlab;

import by.bsu.zuevvlad.seventhlab.logic.sqlserver.SQLServer;
import by.bsu.zuevvlad.seventhlab.logic.sqlserver.exception.SQLServerCreatingException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class Runner
{
    public Runner()
    {
        super();
    }

    public static void main(final String... args) throws SQLServerCreatingException, IOException
    {
        final Properties SQLServerProperties = new Properties();
        final String fileOfPropertiesOfSQLServer = "data/sqlserver.properties";
        try(final InputStream inputStream = Files.newInputStream(Paths.get(fileOfPropertiesOfSQLServer)))
        {
            SQLServerProperties.load(inputStream);
        }

        final String nameOfPropertyOfURL = "database.url";
        final String nameOfPropertyOfUser = "database.user";
        final String nameOfPropertyOfPassword = "database.password";
        final String nameOfPropertyOfListenedPort = "sqlserver.listenedPort";

        final String URLOfDataBase = SQLServerProperties.getProperty(nameOfPropertyOfURL);
        final String userOfDataBase = SQLServerProperties.getProperty(nameOfPropertyOfUser);
        final String passwordOfDataBase = SQLServerProperties.getProperty(nameOfPropertyOfPassword);
        final int listenedPortOfSQLServer = Integer
                .parseInt(SQLServerProperties.getProperty(nameOfPropertyOfListenedPort));

        try(final SQLServer sqlServer = new SQLServer(
                listenedPortOfSQLServer, URLOfDataBase, userOfDataBase, passwordOfDataBase))
        {
            sqlServer.run();
        }
    }
}
