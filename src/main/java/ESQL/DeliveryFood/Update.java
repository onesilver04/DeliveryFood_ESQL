// 개인정보 수정 메서드
// 이름, 위치, 연락처 선택해서 수정 가능하도록 케이스로 구분
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {
    private Scanner scanner = new Scanner(System.in);
    // 손님 정보 수정 메서드
    public void updateCustomer(int Cid) {
        System.out.println("수정할 항목을 선택하세요:");
        System.out.println("1. 이름(닉네임)");
        System.out.println("2. 위치");
        System.out.println("3. 연락처");
        System.out.println("4. 비밀번호");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 제거

        switch (choice) {
            case 1:
                System.out.println("새 이름(닉네임)을 입력하세요. 최대 10글자 가능:");
                String newName = scanner.nextLine();
                updateCustomerField("Cname", newName, Cid);
                break;
            case 2:
                System.out.println("새 위치를 입력하세요:");
                String newLocation = scanner.nextLine();
                updateCustomerField("Clocation", newLocation, Cid);
                break;
            case 3:
                System.out.println("새 연락처를 입력하세요:");
                int newContact = scanner.nextInt();
                updateCustomerField("Ccontact", String.valueOf(newContact), Cid);
                break;
            case 4:
                System.out.println("새 비밀번호를 입력하세요:");
                int newPassword = scanner.nextInt();
                updateCustomerField("Cpw", String.valueOf(newPassword), Cid);
                break;
            default:
                System.out.println("올바른 번호를 선택해주세요.");
        }
    }

    // 사장님 정보 수정 메서드
    public void updateOwner(int Oid) {
        System.out.println("수정할 항목을 선택하세요:");
        System.out.println("1. 이름");
        System.out.println("2. 위치");
        System.out.println("3. 연락처");
        System.out.println("4. 비밀번호");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("새 이름을 입력하세요. 최대 10글자 가능:");
                String newName = scanner.nextLine();
                updateOwnerField("Oname", newName, Oid);
                break;
            case 2:
                System.out.println("새 위치를 입력하세요:");
                String newLocation = scanner.nextLine();
                updateOwnerField("Olocation", newLocation, Oid);
                break;
            case 3:
                System.out.println("새 연락처를 입력하세요:");
                int newContact = scanner.nextInt();
                updateOwnerField("Ocontact", String.valueOf(newContact), Oid);
                break;
            case 4:
                System.out.println("새 비밀번호를 입력하세요:");
                int newPassword = scanner.nextInt();
                updateOwnerField("Opw", String.valueOf(newPassword), Oid);
                break;
            default:
                System.out.println("올바른 번호를 선택해주세요.");
        }
    }

    // 손님 개인정보 수정값이 테이블에 저장되는 직접적인 메소드
    private void updateCustomerField(String fieldName, String newValue, int Cid) {
        String query = "UPDATE consumer SET " + fieldName + " = ? WHERE Cid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newValue);
            pstmt.setInt(2, Cid);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("손님 " + fieldName + "이(가) 성공적으로 수정되었습니다.");
            } else {
                System.out.println("손님 정보 수정에 실패했습니다.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("손님 정보 수정 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
                System.exit(1);
            }
        }
    }

    // 사장님 개인정보 수정값이 테이블에 저장되는 직접적인 메소드
    private void updateOwnerField(String fieldName, String newValue, int Oid) {
        String query = "UPDATE owner SET " + fieldName + " = ? WHERE Oid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newValue);
            pstmt.setInt(2, Oid);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("사장님 " + fieldName + "이(가) 성공적으로 수정되었습니다.");
            } else {
                System.out.println("사장님 정보 수정에 실패했습니다.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("사장님 정보 수정 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
                System.exit(1);
            }
        }
    }
}
