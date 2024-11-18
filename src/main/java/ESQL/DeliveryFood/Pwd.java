// PW 확인 메서드
// Login.java에서 로그인 시도 시 아이디가 존재한다는 가정 하에 아이디와 비번이 일치하는 지 확인
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pwd {
    // 손님 비밀번호 확인 메서드
    public boolean verifyConsumerPassword(int Cid, int inputPassword) {
        String query = "SELECT Cpw FROM consumer WHERE Cid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Cid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int storedPassword = rs.getInt("Cpw");
                return storedPassword == inputPassword;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
        return false;
    }

    // 사장님 비밀번호 확인 메서드
    public boolean verifyOwnerPassword(int Oid, int inputPassword) {
        String query = "SELECT Opw FROM owner WHERE Oid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Oid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int storedPassword = rs.getInt("Opw");
                return storedPassword == inputPassword;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) { // SQLCODE
                System.out.println("오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            }
        }
        return false;
    }
}
