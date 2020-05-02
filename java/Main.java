public class Main {
    public static void main(String[] args) throws Exception {
        Dao db = new Dao();
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
        } catch (LowPriorityException | BadBehaviourException e) {
            e.printStackTrace();
        }
        Player player2 = new Player("Tleu13");
        try {
            player2.playRankedAndShowResult();
        } catch (LowPriorityException | BadBehaviourException e) {
            e.printStackTrace();
        }
        Player player3 = new Player("Ramzes666");
        try {
            player3.playRankedAndShowResult();
        } catch (LowPriorityException | BadBehaviourException e) {
            e.printStackTrace();
        }
        Player player4 = new Player("TleuzhanDestroyer228");
        try {
            player4.playRankedAndShowResult();
        } catch (LowPriorityException | BadBehaviourException e) {
            e.printStackTrace();
        }
        // 1 more feature if NoSuchNickNameException is passed without exceptions. It restricts same nickNames
        db.insertIntoTable("Tleu13", 4444, 9000, 5);
    }
}
