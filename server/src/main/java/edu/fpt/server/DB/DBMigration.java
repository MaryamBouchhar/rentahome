package edu.fpt.server.DB;

import org.flywaydb.core.Flyway;

import static edu.fpt.server.DB.DBInfo.*;

public class DBMigration {
    public static void main(String[] args) {
        DBMigration migration = new DBMigration();
        migration.migrate();
    }

    public void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(DB_URL, DB_USER, DB_PASSWORD)
                .locations("classpath:db/migration")
                .load();
    }
}
