package edu.fpt.server.DB;

public class DBMigration {
    public static void main(String[] args) {
        DBMigration migration = new DBMigration();
        migration.migrate();
    }

    public void migrate() {
        DBConnection dbConnection = new DBConnection();
        dbConnection.connect();
        dbConnection.createTable();
        dbConnection.close();
    }
}
}
