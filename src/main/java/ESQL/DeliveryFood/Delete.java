// 삭제와 관련된 모든 함수들의 집합
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    private Scanner scanner = new Scanner(System.in);

    // 손님 정보 삭제 메서드
    public void deleteCustomer(int Cid) {
        String deleteOrdersQuery = "DELETE FROM orders WHERE Cid = ?";
        String deleteCustomerQuery = "DELETE FROM consumer WHERE Cid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement deleteOrdersStmt = conn.prepareStatement(deleteOrdersQuery);
             PreparedStatement deleteCustomerStmt = conn.prepareStatement(deleteCustomerQuery)) {

            // 트랜잭션 시작
            conn.setAutoCommit(false);

            // orders 테이블에서 해당 손님의 주문 내역 삭제
            deleteOrdersStmt.setInt(1, Cid);
            deleteOrdersStmt.executeUpdate();

            // consumer 테이블에서 손님 정보 삭제
            deleteCustomerStmt.setInt(1, Cid);
            int rowsDeleted = deleteCustomerStmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("손님 정보가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("삭제할 손님 정보가 없습니다.");
            }

            // 커밋
            conn.commit();

        } catch (SQLException e) {
            int errorCode = e.getErrorCode();

            if (errorCode != 0) { // SQLCODE
                System.out.println("오류 발생 (SQLCODE: " + errorCode + "): " + e.getMessage());
            }
        }
    }

    // 사장님 메뉴 삭제
    public void deleteMenu(int Oid) {
        SelectData selectData = new SelectData();

        // 사장님의 메뉴 표시
        if (!selectData.selectMenuByOwner(Oid)) {  // 메뉴가 없는 경우 종료
            return;
        }

        System.out.println("삭제할 메뉴의 ID를 입력하세요:");
        int Mid = scanner.nextInt();

        // 소프트 삭제: is_active 필드를 FALSE로 설정
        String deactivateMenuQuery = "UPDATE menu SET is_active = FALSE WHERE Mid = ? AND Oid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deactivateMenuQuery)) {

            pstmt.setInt(1, Mid);
            pstmt.setInt(2, Oid);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("메뉴가 성공적으로 비활성화(삭제)되었습니다.");
            } else {
                System.out.println("삭제할 메뉴가 없거나 권한이 없습니다.");
            }

        } catch (SQLException e) { // SQLCODE
            int errorCode = e.getErrorCode();

            if (errorCode != 0) {
                System.out.println("메뉴 삭제 중 오류 발생 (SQLCODE: " + errorCode + "): " + e.getMessage());
            }
        }
    }


    // 사장님 개인 정보 삭제 메서드
    // 손님과 달리, Oid가 menu의 외래 키로 사용되고 있고, Mid가 orders의 외래키로 사용되고 있기 때문에
    // orders->menu->owner 순으로 정보를 삭제해 나가야 함
    public void deleteOwner(int Oid) {
        String deleteOrdersQuery = "DELETE FROM orders WHERE Mid IN (SELECT Mid FROM menu WHERE Oid = ?)";
        String deleteMenuQuery = "DELETE FROM menu WHERE Oid = ?";
        String deleteOwnerQuery = "DELETE FROM owner WHERE Oid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement deleteOrdersStmt = conn.prepareStatement(deleteOrdersQuery);
             PreparedStatement deleteMenuStmt = conn.prepareStatement(deleteMenuQuery);
             PreparedStatement deleteOwnerStmt = conn.prepareStatement(deleteOwnerQuery)) {

            // 트랜잭션 시작
            conn.setAutoCommit(false);

            // orders 테이블에서 해당 사장님과 관련된 주문 항목 삭제
            deleteOrdersStmt.setInt(1, Oid);
            deleteOrdersStmt.executeUpdate();

            // menu 테이블에서 해당 사장님의 메뉴 항목 삭제
            deleteMenuStmt.setInt(1, Oid);
            deleteMenuStmt.executeUpdate();

            // owner 테이블에서 사장님 삭제
            deleteOwnerStmt.setInt(1, Oid);
            deleteOwnerStmt.executeUpdate();

            // 커밋
            conn.commit();
            System.out.println("사장님과 관련된 주문, 메뉴 및 개인정보가 성공적으로 삭제되었습니다.");

        } catch (SQLException e) { // SQLCODE
            int errorCode = e.getErrorCode();

            if (errorCode != 0) {
                System.out.println("사장님 정보 삭제 중 오류 발생 (SQLCODE: " + errorCode + "): " + e.getMessage());
            }
        }
    }
}
