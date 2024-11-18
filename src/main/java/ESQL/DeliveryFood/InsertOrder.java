// 손님의 메뉴 주문
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertOrder {
    public void placeOrder(int Cid) {
        Scanner scanner = new Scanner(System.in);
        SelectData selectData = new SelectData();

        // 전체 메뉴 출력 = MENU 테이블 출력
        selectData.displayMenu();
        System.out.println("주문할 메뉴의 ID를 입력하세요:");
        int Mid = scanner.nextInt();
        System.out.println("주문할 수량을 입력하세요:");
        int quantity = scanner.nextInt();

        // 현재 날짜를 주문 날짜로 사용
        String orderDate = java.time.LocalDate.now().toString();

        String sql = "INSERT INTO orders (Cid, Mid, quantity, order_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Cid);
            pstmt.setInt(2, Mid);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, orderDate);

            pstmt.executeUpdate();
            System.out.println("주문이 완료되었습니다!");
            System.out.println("-----------------------");

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }
}
