package com.mitrais.rms.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides MySQL datasource to be used to connect to database.
 * It implements singleton pattern See <a href="http://www.oodesign.com/singleton-pattern.html">Singleton Pattern</a>
 */
public class DataSourceFactory
{
    private final DataSource dataSource;

    DataSourceFactory()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        String rootPath =
                Thread.currentThread().getContextClassLoader().getResource("database.properties").getPath();

        try (InputStream input = new FileInputStream(rootPath)){
            Properties prop = new Properties();
            prop.load(input);

            dataSource.setDatabaseName(prop.getProperty("database"));
            dataSource.setServerName(prop.getProperty("serverName"));
            dataSource.setPort(Integer.parseInt(prop.getProperty("port")));
            dataSource.setUser(prop.getProperty("user"));
            dataSource.setPassword(prop.getProperty("password"));

        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Other IO error");
            e.printStackTrace();
        }

        this.dataSource = dataSource;
    }

    /**
     * Get a data source to database
     *
     * @return DataSource object
     */
    public static Connection getConnection() throws SQLException
    {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper
    {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
