package com.ali.dataAccess.interfaces;

import javax.annotation.Nullable;
import javax.naming.NamingException;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public interface DataBaseManagerInterface {
    @Nullable
    Optional<Connection> getConnection() throws SQLException, NamingException;

    void cleanUpResources(@NotNull  Optional<Connection> connection, @NotNull Optional<ResultSet> resultSet,
                          @NotNull Optional<Statement> statement);
}