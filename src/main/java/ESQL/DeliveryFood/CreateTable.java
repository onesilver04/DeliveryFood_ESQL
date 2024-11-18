package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateTable {

    private boolean isTableExists(Connection conn, String tableName) throws SQLException {
        String checkTableSQL = "SHOW TABLES LIKE '" + tableName + "'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkTableSQL)) {
            return rs.next(); // 테이블이 존재하면 true 반환
        }
    }

    public void createConsumerTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS consumer (" +
                "Cid INT PRIMARY KEY CHECK (Cid BETWEEN 1000 AND 9999), " +
                "Cname VARCHAR(10) NOT NULL, " +
                "Clocation VARCHAR(20), " +
                "Ccontact INT," +
                "Cpw INT NOT NULL CHECK (Cpw BETWEEN 1000 AND 9999))";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            if (isTableExists(conn, "consumer")) {
                System.out.println("consumer 테이블이 이미 존재합니다.");
                return;
            }

            stmt.execute(createTableSQL);
            System.out.println("consumer 테이블이 성공적으로 생성되었습니다.");
        } catch (SQLException e) {
            System.out.println("consumer 테이블 생성 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
        }
    }

    public void createOwnerTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS owner (" +
                "Oid INT PRIMARY KEY CHECK (Oid BETWEEN 1000 AND 9999), " +
                "Oname VARCHAR(10) NOT NULL, " +
                "Olocation VARCHAR(20), " +
                "Ocontact INT," +
                "Opw INT NOT NULL CHECK (Opw BETWEEN 1000 AND 9999))";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            if (isTableExists(conn, "owner")) {
                System.out.println("owner 테이블이 이미 존재합니다.");
                return;
            }

            stmt.execute(createTableSQL);
            System.out.println("owner 테이블이 성공적으로 생성되었습니다.");
        } catch (SQLException e) {
            System.out.println("owner 테이블 생성 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
        }
    }

    public void createMenuTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS menu (" +
                "Mid INT PRIMARY KEY AUTO_INCREMENT, " +
                "Oid INT, " +
                "Mname VARCHAR(50) NOT NULL, " +
                "Mprice INT NOT NULL, " +
                "FOREIGN KEY (Oid) REFERENCES owner(Oid))";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            if (isTableExists(conn, "menu")) {
                System.out.println("menu 테이블이 이미 존재합니다.");
                // is_active 열이 있는지 확인하고, 없으면 추가
                String checkColumnSQL = "SHOW COLUMNS FROM menu LIKE 'is_active'";
                try (ResultSet rs = stmt.executeQuery(checkColumnSQL)) {
                    if (!rs.next()) { // is_active 열이 없으면 추가
                        String addColumnSQL = "ALTER TABLE menu ADD COLUMN is_active BOOLEAN DEFAULT TRUE";
                        stmt.executeUpdate(addColumnSQL);
                        System.out.println("menu 테이블에 'is_active' 열이 성공적으로 추가되었습니다.");
                    } else {
                        System.out.println("menu 테이블에 'is_active' 열이 이미 존재합니다.");
                    }
                }
                return;
            }

            // 테이블 생성
            stmt.execute(createTableSQL);
            System.out.println("menu 테이블이 성공적으로 생성되었습니다.");

            // is_active 열 추가
            String addColumnSQL = "ALTER TABLE menu ADD COLUMN is_active BOOLEAN DEFAULT TRUE";
            stmt.executeUpdate(addColumnSQL);
            System.out.println("menu 테이블에 'is_active' 열이 성공적으로 추가되었습니다.");
        } catch (SQLException e) {
            System.out.println("menu 테이블 생성 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
        }
    }

    public void createOrdersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS orders (" +
                "Rid INT PRIMARY KEY AUTO_INCREMENT, " +
                "Cid INT, " +
                "Mid INT, " +
                "quantity INT NOT NULL, " +
                "order_date DATE NOT NULL, " +
                "FOREIGN KEY (Cid) REFERENCES consumer(Cid), " +
                "FOREIGN KEY (Mid) REFERENCES menu(Mid))";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            if (isTableExists(conn, "orders")) {
                System.out.println("orders 테이블이 이미 존재합니다.");
            } else {
                stmt.execute(createTableSQL);
                System.out.println("orders 테이블이 성공적으로 생성되었습니다.");
            }
            // 기존 외래 키 제거 및 새 외래 키 추가
            String dropForeignKeySQL = "ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_2";
            stmt.executeUpdate(dropForeignKeySQL);
            String addForeignKeySQL = "ALTER TABLE orders ADD CONSTRAINT orders_ibfk_2 FOREIGN KEY (Mid) REFERENCES menu (Mid) ON DELETE SET NULL";
            stmt.executeUpdate(addForeignKeySQL);
        } catch (SQLException e) {
            System.out.println("orders 테이블 생성 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            System.exit(1);
        }
    }
}
