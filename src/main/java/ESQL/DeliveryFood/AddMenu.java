package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMenu {
    private Scanner scanner = new Scanner(System.in);

    // 메뉴 추가 메서드
    public void addMenu(int Oid) {
        System.out.println("새로운 메뉴 이름을 입력하세요:");
        String Mname = scanner.nextLine();

        System.out.println("메뉴 가격을 입력하세요:");
        int Mprice = scanner.nextInt();
        scanner.nextLine(); // 개행 제거

        String query = "INSERT INTO menu (Mname, Mprice, Oid) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, Mname);
            pstmt.setInt(2, Mprice);
            pstmt.setInt(3, Oid);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("메뉴가 성공적으로 추가되었습니다.");
            } else {
                System.out.println("메뉴 추가에 실패했습니다.");
            }

        } catch (SQLException e) {
            int errorCode = e.getErrorCode();

            if (errorCode != 0) {
                System.out.println("메뉴 추가 중 오류 발생 (SQLCODE: " + errorCode + "): " + e.getMessage());
            }
        }
    }
}
