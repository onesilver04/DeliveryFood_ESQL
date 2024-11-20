// ID로 이름 가져오기, 개인정보 조회, ID 존재 확인, 메뉴 조회, 주문 내역 조회
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectData {
    // 손님 ID로 손님의 이름을 가져오는 메서드
    public String getConsumerName(int Cid) {
        String query = "SELECT Cname FROM consumer WHERE Cid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Cid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Cname");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("손님 이름 조회 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
        // 이름을 찾지 못할 경우 예외 처리
        return "손님";
    }
    // 사장님 ID로 사장님의 이름을 가져오는 메서드
    public String getOwnerName(int Oid) {
        String query = "SELECT Oname FROM owner WHERE Oid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Oid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Oname");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("사장님 이름 조회 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
        // 이름을 찾지 못할 경우 예외 처리
        return "사장님";
    }

    // 손님 개인 정보 조회 메서드
    public void selectConsumerInfo(int Cid) {
        String query = "SELECT Cid, Cname, Clocation, Ccontact, Cpw FROM consumer WHERE Cid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, Cid); // Cid에 해당하는 손님 정보를 가져옵니다.

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("손님 ID: " + rs.getInt("Cid"));
                System.out.println("손님 이름: " + rs.getString("Cname"));
                System.out.println("손님 위치: " + rs.getString("Clocation"));
                System.out.println("손님 연락처: " + rs.getInt("Ccontact"));
                System.out.println("손님 비밀번호: " + rs.getInt("Cpw"));
                System.out.println("****************");
            } else {
                System.out.println("해당 손님 정보를 찾을 수 없습니다.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("손님 정보 조회 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }

    // 특정 메뉴 정보 조회 메서드
    public boolean selectMenuByOwner(int Oid) {
        String query = "SELECT Mid, Mname, Mprice FROM menu WHERE Oid = ? AND is_active = TRUE";
        boolean hasMenu = false;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, Oid);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("메뉴 목록:");
            System.out.println("ID | 이름 | 가격");

            while (rs.next()) {
                hasMenu = true;
                int id = rs.getInt("Mid");
                String name = rs.getString("Mname");
                int price = rs.getInt("Mprice");
                System.out.println(id + " | " + name + " | " + price + "원");
            }

            if (!hasMenu) {
                System.out.println("아직 등록된 메뉴가 없습니다.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("메뉴 조회 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
        return hasMenu;
    }

    // 사장님 개인 정보 조회 메서드
    public void selectOwnerInfo(int Oid) {
        String query = "SELECT Oid, Oname, Olocation, Ocontact, Opw FROM owner WHERE Oid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, Oid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("사장님 ID: " + rs.getInt("Oid"));
                System.out.println("사장님 이름: " + rs.getString("Oname"));
                System.out.println("사장님 위치: " + rs.getString("Olocation"));
                System.out.println("사장님 연락처: " + rs.getInt("Ocontact"));
                System.out.println("사장님 비밀번호: " + rs.getInt("Opw"));
                System.out.println("****************");
            } else {
                System.out.println("해당 사장님 정보를 찾을 수 없습니다.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("사장님 정보 조회 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }
    // 특정 손님의 주문 내역 조회 메서드
    public void selectOrderDetails(int Cid) {
        String query = "SELECT " +
                "consumer.Cname AS 손님이름, " +
                "consumer.Clocation AS 손님위치, " +
                "menu.Mname AS 메뉴이름, " +
                "menu.Mprice AS 가격, " +
                "orders.quantity AS 주문수량, " +
                "owner.Olocation AS 가게위치, " +
                "orders.order_date AS 주문날짜, " +
                "(menu.Mprice * orders.quantity) AS 총가격 " +
                "FROM consumer " +
                "JOIN orders ON consumer.Cid = orders.Cid " +
                "JOIN menu ON orders.Mid = menu.Mid " +
                "JOIN owner ON menu.Oid = owner.Oid " +
                "WHERE consumer.Cid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, Cid); // Cid 값 바인딩

            ResultSet rs = pstmt.executeQuery();

            // 주문 내역이 없는 경우 처리
            if (!rs.isBeforeFirst()) { // 결과가 없는 경우
                System.out.println("주문한 메뉴가 없습니다.");
                System.out.println("------------------------");
                return;
            }

            // 결과 출력
            while (rs.next()) {
                System.out.println("손님 이름: " + rs.getString("손님이름"));
                System.out.println("손님 위치: " + rs.getString("손님위치"));
                System.out.println("메뉴 이름: " + rs.getString("메뉴이름"));
                System.out.println("개 당 가격: " + rs.getInt("가격"));
                System.out.println("주문 수량: " + rs.getInt("주문수량"));
                System.out.println("가게 위치: " + rs.getString("가게위치"));
                System.out.println("주문 날짜: " + rs.getString("주문날짜"));
                int totalPrice = rs.getInt("총가격");
                System.out.println("총 결제 금액: " + totalPrice); // 총 가격 출력
                System.out.println("--------------------------------");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("데이터 조회 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }

    // 메뉴 전체 목록을 표시하는 메서드
    public void displayMenu() {
        // 사장님이 비활성화(=삭제)한 매뉴는 손님이 볼 수 없음
        String query = "SELECT Mid, Mname, Mprice FROM menu WHERE is_active = TRUE";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();

            System.out.println("메뉴판:");
            System.out.println("메뉴 ID | 메뉴 | 가격");
            while (rs.next()) {
                int id = rs.getInt("Mid");
                String name = rs.getString("Mname");
                int price = rs.getInt("Mprice");
                System.out.println(id + " | " + name + " | " + price + "원");
            }
            System.out.println("--------------------------------");

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("메뉴 조회 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
                System.exit(1);
            }
        }
    }
}
