//package com.fpt.rentahome.DB;
//
//import org.flywaydb.core.Flyway;
//
//import static com.fpt.rentahome.DB.DBInfo.*;
//
//public class DBMigration {
//    public static void main(String[] args) {
//        Flyway flyway = Flyway.configure()
//                .dataSource(DB_URL, DB_USER, DB_PASSWORD)
//                .locations("filesystem:src/main/java/edu/fpt/server/DB/migrations")
//                .load();
//
//        flyway.migrate();
//    }
//}
