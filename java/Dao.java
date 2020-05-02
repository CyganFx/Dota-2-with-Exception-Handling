import java.sql.*;

import static java.lang.System.*;

public class Dao {
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "duman070601");
        out.println("Opened database successfully");
    }

    public void createTable() {
        try {
            getConnection();
            statement = connection.createStatement();
            String sql = """
                    CREATE TABLE dota
                    (
                     ID SERIAL PRIMARY KEY NOT NULL,
                     NICKNAME VARCHAR,
                     MMR INT,
                     BEHAVIOUR_RATING INT,
                     REPORTS_COUNT INT
                     )""";
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            err.println(e.getClass().getName() + ": " + e.getMessage());
            exit(0);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        out.println("Table created successfully");
    }

    public void insertIntoTable(String nickName, int mmr, int behaviourRating, int reports_count) {
        try {
            getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dota WHERE NICKNAME ='" + nickName + "';");
            if (resultSet.next()) {
                throw new UniqueNickNameException("Nickname: " + nickName + " already exists");
            }
                    String sql = "INSERT INTO dota (NICKNAME, MMR, BEHAVIOUR_RATING, REPORTS_COUNT) "
                            + "VALUES ('" + nickName + "', " + mmr + ", '" + behaviourRating + "', " + reports_count + ");";
                    statement.executeUpdate(sql);
                    connection.commit();
        } catch (ClassNotFoundException | SQLException | UniqueNickNameException e) {
            err.println(e.getClass().getName() + ": " + e.getMessage());
            exit(0);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        out.println("Records created successfully");
    }

    public void deleteFromTable(String nickname) throws Exception {
        try {
            getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "DELETE from dota where NICKNAME = '" + nickname + "';";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            err.println(e.getClass().getName() + ": " + e.getMessage());
            exit(0);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        out.println("DELETED successfully");
    }
}




