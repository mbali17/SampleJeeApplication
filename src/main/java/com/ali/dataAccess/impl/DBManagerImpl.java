package com.ali.dataAccess.impl;

import com.ali.constants.DatabaseConstants;
import com.ali.dataAccess.interfaces.DataBaseManagerInterface;
import com.ali.dataAccess.pooling.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * Responsible for performing action related to the database.
 */
public class DBManagerImpl implements DataBaseManagerInterface{
    static final Logger LOGGER = LoggerFactory.getLogger(DBManagerImpl.class);

    @Nullable
    public Optional<Connection> getConnection() throws SQLException, NamingException {
        Optional<DataSource> dataSource = ConnectionPool.getDataSource(Optional.of(DatabaseConstants.DATASOURCENAME ));
        if(dataSource.isPresent()){
            LOGGER.debug("obtained  datasource {} and returning connection",dataSource.get().toString());
             return Optional.ofNullable(dataSource.get().getConnection());
        }
        return Optional.empty();
    }

    @Override
    public void cleanUpResources(@NotNull Optional<Connection> connection,
                                 @NotNull Optional<ResultSet> resultSet,
                                 @NotNull Optional<Statement> statement) {
        resultSet.ifPresent(passedResultSet-> {
            try {
                passedResultSet.close();
            } catch (SQLException e) {
                LOGGER.error("java.sql.SQLException while closing the result set",e);
            }
        });
        statement.ifPresent(passedStatement-> {
            try {
                passedStatement.close();
            } catch (SQLException e) {
                LOGGER.error("java.sql.SQLException while closing the statement",e);;
            }
        });
        connection.ifPresent(passedConnection-> {
            try {
                passedConnection.close();
            } catch (SQLException e) {
                LOGGER.error("java.sql.SQLException while closing the connection in clean up resources",e);
            }
        });

    }

    @Override
    public void closeConnection(@NotNull Optional<Connection> connection) {
        connection.ifPresent(passedConnection-> {
            try {
                passedConnection.close();
            } catch (SQLException e) {
                LOGGER.error("java.sql.SQLException while closing the connection",e);
            }
        });
    }


}
