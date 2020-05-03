import java.util.Stack;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Dao db = new Dao();
        Stack<Boolean> allowedToPlay = new Stack<>();
        String successfulMessage = "You are allowed to play, enjoy!";
        String notSuccessfulMessage = "You are not allowed to play!";
        /**
         db.createTable();
         db.insertIntoTable("LuuR*", 6000, 8000, 2);
         db.insertIntoTable("Tleu13", 5000, 8500, 6);
         db.insertIntoTable("Ramzes666", 10000, 6000, 0);
         db.insertIntoTable("Arteezy", 11000, 9000, 1);
         db.deleteFromTable("Arteezy");
         */
        Player player1 = new Player("LuuR*");
        try {
            player1.playRankedAndShowResult();
            allowedToPlay.add(true);
        } catch (LowPriorityException | BadBehaviourException e) {
            allowedToPlay.add(false);
            e.printStackTrace();
        } finally {
            out.println(!allowedToPlay.peek() ? notSuccessfulMessage : successfulMessage);
        }
        Player player2 = new Player("Tleu13");
        try {
            player2.playRankedAndShowResult();
            allowedToPlay.add(true);
        } catch (LowPriorityException | BadBehaviourException e) {
            allowedToPlay.add(false);
            e.printStackTrace();
        } finally {
            out.println(!allowedToPlay.peek() ? notSuccessfulMessage : successfulMessage);
        }
        Player player3 = new Player("Ramzes666");
        try {
            player3.playRankedAndShowResult();
            allowedToPlay.add(true);
        } catch (LowPriorityException | BadBehaviourException e) {
            allowedToPlay.add(false);
            e.printStackTrace();
        } finally {
            out.println(!allowedToPlay.peek() ? notSuccessfulMessage : successfulMessage);
        }
        Player player4 = new Player("TleuzhanDestroyer228");
        try {
            player4.playRankedAndShowResult();
            allowedToPlay.add(true);
        } catch (LowPriorityException | BadBehaviourException e) {
            allowedToPlay.add(false);
            e.printStackTrace();
        }
        // 1 more feature if NoSuchNickNameException is passed without exceptions. It toggles UniqueNicknameException
        db.insertIntoTable("Tleu13", 4444, 9000, 5);
    }
}
