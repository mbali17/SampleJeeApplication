package com.ali.constants;

/**
 * This class is used to define the constants used in the database access Like,
 * the database names,queries and other constants.
 */
public class DatabaseConstants {
    /**
     * Adding private constructor to avoid creation of objects.
     */
    private DatabaseConstants() {
    }

    public static final String DATASOURCENAME = "jdbc/todoapp";

    //User registration SQL
    public static final String INSERT_USER_SQL = "INSERT INTO `Todoapp`.`users`(`user_name`,`password`,`email`)" +
                                                 "VALUES(?,?,?)";
}
