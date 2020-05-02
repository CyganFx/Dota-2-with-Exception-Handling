import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.*;

public class Player extends Dao {
    private String nickName;
    private int mmr;
    private int behaviourRating;
    private int reportsCount;
    private int prevMmr;

    public Player(String nickName) {
        try {
            getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dota WHERE NICKNAME ='" + nickName + "';");
            nickNameCheck(resultSet, nickName);
            while (resultSet.next()) {
                this.nickName = resultSet.getString("NICKNAME");
                this.mmr = resultSet.getInt("mmr");
                this.behaviourRating = resultSet.getInt("behaviour_rating");
                this.reportsCount = resultSet.getInt("reports_count");
            }
        } catch (ClassNotFoundException | SQLException | NoSuchNickNameException e) {
            err.println(e.getClass().getName() + ": " + e.getMessage());
            exit(0);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
    }

    public void playRankedAndShowResult() throws LowPriorityException, BadBehaviourException {
        out.println("[" + nickName + " statistics:");
        lowPriorityCheck();
        behaviourCheck();
        out.println("Starting the game...");
        updateRating();
        out.print("Game has finished: ");
        showResult();
    }

    private void nickNameCheck(ResultSet resultSet, String nickName) throws SQLException, NoSuchNickNameException {
        if (!resultSet.isBeforeFirst() ) {
            throw new NoSuchNickNameException("There is no user as: " + nickName +", try to register first");
        }
    }

    private void lowPriorityCheck() throws LowPriorityException {
        if (reportsCount < 5) {
            out.println("Acceptable reportCounter");
        } else {
            throw new LowPriorityException("Dear, " + nickName +
                    ", You have " + reportsCount + " reports, stop ruining, please");
        }
    }

    private void behaviourCheck() throws BadBehaviourException {
        if (behaviourRating >= 8000) {
            out.println("Good behaviour score");
        } else {
            throw new BadBehaviourException("Dear, " + nickName +
                    " Playing RMM is restricted due to your bad behaviour score: " +
                    behaviourRating + ", stop trashtalking, please");
        }
    }

    private void updateRating() {
        prevMmr = mmr;
        int ratingGiven = (int) (Math.random() * (50 + 1)) - 25;
        mmr += ratingGiven;
    }

    private boolean hasWon() {
        return mmr > prevMmr;
    }

    private void showResult() {
        out.print(hasWon() ? "You Won" : "You Lost");
        out.println("]");
    }
}
