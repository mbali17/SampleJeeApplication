package com.ali.dataAccess.impl;

import com.ali.dataAccess.interfaces.DataBaseManagerInterface;
import com.ali.dataAccess.pooling.ConnectionPool;

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
 * Responsible for creating the connection pool to MySQL database.
 */
public class DBManagerImpl implements DataBaseManagerInterface{
    @Nullable
    public Optional<Connection> getConnection() throws SQLException, NamingException {
        Optional<DataSource> dataSource = ConnectionPool.getDataSource(Optional.of("jdbc/todoapp"));
        if(dataSource.isPresent()){
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
                e.printStackTrace();
            }
        });
        statement.ifPresent(passedStatement-> {
            try {
                passedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        connection.ifPresent(passedConnection-> {
            try {
                passedConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }


}
