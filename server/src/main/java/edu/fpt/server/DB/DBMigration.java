package edu.fpt.server.DB;

import org.flywaydb.core.Flyway;

import static edu.fpt.server.DB.DBInfo.*;

public class DBMigration {
    public static void main(String[] args) {
        migrate();
    }

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(DB_URL, DB_USER, DB_PASSWORD)
                .locations("classpath:v1_db_migration.sql")
                .load();

        flyway.migrate();
    }
}
