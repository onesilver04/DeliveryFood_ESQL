// 파일 경로: src/main/java/ESQL/DeliveryFood/DatabaseConnection.java
// 데이터베이스 연결 설정 파일
package ESQL.DeliveryFood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {// MySQL 데이터베이스 연결 정보를 설정
    private static final String URL = "jdbc:mysql://localhost:3306/deliveryfood";
    private static final String USER = "root";
    private static final String PASSWORD = "Eunjung123";

    public static Connection getConnection() {
        Connection connection = null;
        try { // 데이터베이스 연결 생성
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) { // 연결 실패 시 오류 메시지를 출력하고 종료
            System.err.println("데이터베이스 연결 실패");
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
        return connection;
    }
}
