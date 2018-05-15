package com.ali.dataAccess.pooling;

import org.apache.commons.lang3.StringUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Optional;

/**
 * Singleton class to get the datasource.
 */
public class ConnectionPool {
    private static DataSource dataSource;
    //Empty constructor so that the object is not created.
    private ConnectionPool(){

    }
    private static void initializeContext(Optional<String> datasourceName) throws NamingException {
        if(!datasourceName.isPresent()){
            throw new IllegalArgumentException("Datasource name cannot be blank");
        }else{
            Context context = new InitialContext();
            dataSource= (DataSource)context.lookup("java:comp/env/"+datasourceName.get());
        }
    }
    public static Optional<DataSource> getDataSource(Optional<String> datasourceName) throws NamingException {
        if(dataSource == null) {
            initializeContext(datasourceName);
            return Optional.ofNullable(dataSource);
        }else{
            return Optional.ofNullable(dataSource);
        }
    }
}
