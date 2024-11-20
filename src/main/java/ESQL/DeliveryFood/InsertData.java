// 회원가입 정보 삽입, 기존 아이디와 중복 확인
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertData {
    // Consumer ID 중복 여부 확인
    public boolean isConsumerIdAvailable(int Cid) {
        String checkQuery = "SELECT COUNT(*) FROM consumer WHERE Cid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, Cid);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                // ID가 존재하면 true 반환
                return rs.getInt(1) > 0;
            }
            return false; // ID가 존재하지 않으면 false 반환
        } catch (SQLException e) {
            System.out.println("손님 중복된 ID 확인 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            return false;
        }
    }

    // Owner ID 중복 여부 확인
    public boolean isOwnerIdAvailable(int Oid) {
        String checkQuery = "SELECT COUNT(*) FROM owner WHERE Oid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, Oid);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                // ID가 존재하면 true 반환
                return rs.getInt(1) > 0;
            }
            return false; // ID가 존재하지 않으면 false 반환
        } catch (SQLException e) {
            System.out.println("사장님 중복된 ID 확인 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            return false;
        }
    }

    // 손님 데이터 삽입
    public void insertConsumer(int Cid, String Cname, String Clocation, int Ccontact, int Cpw) {
        String insertQuery = "INSERT INTO consumer (Cid, Cname, Clocation, Ccontact, Cpw) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, Cid);
            insertStmt.setString(2, Cname);
            insertStmt.setString(3, Clocation);
            insertStmt.setInt(4, Ccontact);
            insertStmt.setInt(5, Cpw);
            insertStmt.executeUpdate();
            System.out.println("손님 데이터가 성공적으로 삽입되었습니다.");
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("손님 데이터 삽입 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }

    // 사장님 데이터 삽입
    public void insertOwner(int Oid, String Oname, String Olocation, int Ocontact, int Opw) {
        String insertQuery = "INSERT INTO owner (Oid, Opw, Oname, Olocation, Ocontact) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, Oid);
            insertStmt.setInt(2, Opw);
            insertStmt.setString(3, Oname);
            insertStmt.setString(4, Olocation);
            insertStmt.setInt(5, Ocontact);
            insertStmt.executeUpdate();
            System.out.println("사장님 데이터가 성공적으로 삽입되었습니다.");
        } catch (SQLException e) { // SQLCODE
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("사장님 데이터 삽입 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
    }
}
