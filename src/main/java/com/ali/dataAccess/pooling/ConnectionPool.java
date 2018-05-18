package com.ali.dataAccess.pooling;

import com.ali.constants.DatabaseConstants;
import org.apache.commons.lang3.StringUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Optional;

/**
 * Obtains the datasource created using the tomcat API.
 */
public class ConnectionPool {
    private static DataSource dataSource;
    /**
     *Empty constructor so that the object is not created. AS this is a singleton.
     */
    private ConnectionPool(){

    }

    /**
     * Initializes the context and lookup the JNDI table to ge the datasource registered.
     * @param datasourceName
     * @throws NamingException
     */
    private static void initializeContext(Optional<String> datasourceName) throws NamingException {
        if(!datasourceName.isPresent()){
            throw new IllegalArgumentException("Datasource name cannot be blank");
        }else{
            Context context = new InitialContext();
            dataSource= (DataSource)context.lookup(DatabaseConstants.JNDI_PREFIX+datasourceName.get());
        }
    }

    /**
     * Returns an existing  datasource if initialized otherwise initializes new object before returning.
     * @param datasourceName
     * @return {@Code } Optional<DataSource> populated using the JNDI lookup.
     * @throws NamingException
     */
    public static Optional<DataSource> getDataSource(Optional<String> datasourceName) throws NamingException {
        if(dataSource == null) {
            initializeContext(datasourceName);
            return Optional.ofNullable(dataSource);
        }else{
            return Optional.ofNullable(dataSource);
        }
    }
}
