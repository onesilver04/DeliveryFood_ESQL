// 프로그램 최초 실행 시 각 테이블에 넣을 정보
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Insert { // 데이터 존재 여부 확인 메서드
    private boolean isDataExists(Connection conn, String tableName) throws SQLException {
        String checkDataSQL = "SELECT COUNT(*) AS count FROM " + tableName;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkDataSQL)) {
            if (rs.next()) {
                return rs.getInt("count") > 0; // 데이터가 존재하면 true 반환
            }
        }
        return false;
    }

    // consumer 테이블에 데이터 삽입
    public void setConsumerData() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement()) {

            if (isDataExists(conn, "consumer")) {
                System.out.println("consumer 테이블에 기존 데이터가 이미 존재합니다.");
                return;
            }

            String sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1001, 'Alice', 'Seoul', 1010, 1234)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1002, 'Bob', 'Busan', 1020, 2345)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1003, 'Charlie', 'Incheon', 1030, 3456)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1004, 'David', 'Daegu', 1040, 4567)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1005, 'Eve', 'Gwangju', 1050, 5678)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1006, 'Frank', 'Daejeon', 1060, 6789)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1007, 'Grace', 'Ulsan', 1070, 7890)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1008, 'Hannah', 'Suwon', 1080, 8901)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1009, 'Ivy', 'Jeju', 1090, 9012)";
            st.executeUpdate(sql);

            sql = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES "
                    + "(1010, 'Jack', 'Chuncheon', 1100, 1122)";
            st.executeUpdate(sql);

            System.out.println("consumer 데이터가 성공적으로 삽입되었습니다.");

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {  // SQLCODE가 0이 아니면 오류 발생
                System.out.println("consumer 데이터 삽입 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }

    // owner 테이블에 데이터 삽입
    public void setOwnerData() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement()) {

            if (isDataExists(conn, "owner")) {
                System.out.println("owner 테이블에 기존 데이터가 이미 존재합니다.");
                return;
            }

            String sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2001, 'Owner1', 'Seoul', 1111, 2233)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2002, 'Owner2', 'Busan', 2222, 3344)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2003, 'Owner3', 'Incheon', 3333, 4455)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2004, 'Owner4', 'Daegu', 4444, 5566)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2005, 'Owner5', 'Gwangju', 5555, 6677)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2006, 'Owner6', 'Daejeon', 6666, 7788)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2007, 'Owner7', 'Ulsan', 7777, 8899)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2008, 'Owner8', 'Suwon', 8888, 9900)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2009, 'Owner9', 'Jeju', 9999, 1111)";
            st.executeUpdate(sql);

            sql = "INSERT INTO owner (Oid, Oname, Olocation, Ocontact, Opw) VALUES "
                    + "(2010, 'Owner10', 'Chuncheon', 1010, 1222)";
            st.executeUpdate(sql);

            System.out.println("owner 데이터가 성공적으로 삽입되었습니다.");

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                System.out.println("owner 데이터 삽입 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }

    // menu 테이블에 데이터 삽입
    public void setMenuData() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement()) {

            if (isDataExists(conn, "menu")) {
                System.out.println("menu 테이블에 기존 데이터가 이미 존재합니다.");
                return;
            }

            String sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3001, 'Pizza', 15000, 2001)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3002, 'Burger', 8000, 2002)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3003, 'Pasta', 12000, 2003)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3004, 'Sushi', 20000, 2004)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3005, 'Salad', 7000, 2005)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3006, 'Ramen', 9000, 2006)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3007, 'Steak', 25000, 2007)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3008, 'Fried Chicken', 18000, 2008)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3009, 'Tacos', 6000, 2009)";
            st.executeUpdate(sql);

            sql = "INSERT INTO menu (Mid, Mname, Mprice, Oid) VALUES "
                    + "(3010, 'Burrito', 7000, 2010)";
            st.executeUpdate(sql);

            System.out.println("menu 데이터가 성공적으로 삽입되었습니다.");

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                System.out.println("menu 데이터 삽입 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }

    // orders 테이블에 데이터 삽입
    public void setOrdersData() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement()) {

            if (isDataExists(conn, "orders")) {
                System.out.println("orders 테이블에 기존 데이터가 이미 존재합니다.");
                return;
            }

            String sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4001, 1001, 3001, 2, '2024-11-01')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4002, 1002, 3002, 1, '2024-11-02')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4003, 1003, 3003, 3, '2024-11-03')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4004, 1004, 3004, 1, '2024-11-04')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4005, 1005, 3005, 4, '2024-11-05')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4006, 1006, 3006, 2, '2024-11-06')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4007, 1007, 3007, 1, '2024-11-07')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4008, 1008, 3008, 3, '2024-11-08')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4009, 1009, 3009, 2, '2024-11-09')";
            st.executeUpdate(sql);

            sql = "INSERT INTO orders (Rid, Cid, Mid, quantity, order_date) VALUES "
                    + "(4010, 1010, 3010, 1, '2024-11-10')";
            st.executeUpdate(sql);

            System.out.println("orders 데이터가 성공적으로 삽입되었습니다.");

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                System.out.println("orders 데이터 삽입 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }
}
