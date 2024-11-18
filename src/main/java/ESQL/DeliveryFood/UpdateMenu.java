// 사장님의 메뉴 수정
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMenu {
    private Scanner scanner = new Scanner(System.in);
    public void updateMenuInfo(int Oid) {
        // 사장님이 소유한 메뉴 표시
        SelectData selectData = new SelectData();
        // 메뉴가 없는 경우 돌아가기
        if (!selectData.selectMenuByOwner(Oid)) {
            return;
        }
        System.out.println("수정할 메뉴의 ID를 입력하세요:");
        int Mid = scanner.nextInt();
        scanner.nextLine(); // 개행 제거

        System.out.println("수정할 항목을 선택하세요:");
        System.out.println("1. 메뉴 이름");
        System.out.println("2. 메뉴 가격");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 개행 제거

        String columnName = "";
        String newValue = "";

        switch (choice) {
            case 1:
                System.out.println("새로운 메뉴 이름을 입력하세요:");
                columnName = "Mname";
                newValue = scanner.nextLine();
                break;
            case 2:
                System.out.println("새로운 메뉴 가격을 입력하세요:");
                columnName = "Mprice";
                newValue = String.valueOf(scanner.nextInt());
                scanner.nextLine(); // 개행 제거
                break;
            default:
                System.out.println("올바른 선택이 아닙니다.");
                return;
        }

        String query = "UPDATE menu SET " + columnName + " = ? WHERE Mid = ? AND Oid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newValue);
            pstmt.setInt(2, Mid);
            pstmt.setInt(3, Oid);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("메뉴 정보가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("메뉴 수정에 실패했습니다. 메뉴 ID 또는 권한을 확인하세요.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("메뉴 정보 수정 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
                System.exit(1);
            }
        }
    }
}
