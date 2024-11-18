// 로그인 메서드
package ESQL.DeliveryFood;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private SelectData selectData = new SelectData();
    private Pwd pwd = new Pwd(); // 비밀번호 검증을 위한 Pwd 클래스 사용
    private Scanner scanner = new Scanner(System.in);

    // 손님 로그인 메서드
    public int customerLogin() {
        System.out.println("손님 ID 입력:");
        int Cid = scanner.nextInt();

        if (!selectData.isConsumerExists(Cid)) {
            System.out.println(Cid + "은(는) 존재하지 않는 손님 ID입니다. 메인 메뉴로 돌아갑니다.");
            System.out.println("****************");
            return -1; // 로그인 실패 시 -1 반환
        }

        System.out.println("비밀번호 입력:");
        int inputPassword = scanner.nextInt();

        try {
            if (pwd.verifyConsumerPassword(Cid, inputPassword)) {
                String Cname = selectData.getConsumerName(Cid);
                if (Cname == null) { // 이름 조회에 문제가 있을 경우
                    throw new SQLException("손님 이름 조회 오류 발생", "SQLCODE", -1);
                }
                System.out.println(Cname + " 손님, 환영합니다!");
                System.out.println("원하시는 메뉴를 선택해주세요:");
                System.out.println("****************");
                return Cid;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다. 메인 메뉴로 돌아갑니다.");
                System.out.println("****************");
                return -1;
            }
        } catch (SQLException e) { // SQLCODE
            System.out.println("로그인 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            return -1;
        }
    }

    // 사장님 로그인 메서드
    public int ownerLogin() {
        System.out.println("사장님 ID 입력:");
        int Oid = scanner.nextInt();

        if (!selectData.isOwnerExists(Oid)) {
            System.out.println(Oid + "은(는) 존재하지 않는 사장님 ID입니다. 메인 메뉴로 돌아갑니다.");
            System.out.println("****************");
            return -1; // 로그인 실패 시 -1 반환
        }

        System.out.println("비밀번호 입력:");
        int inputPassword = scanner.nextInt();

        try {
            if (pwd.verifyOwnerPassword(Oid, inputPassword)) {
                String Oname = selectData.getOwnerName(Oid);
                if (Oname == null) { // 이름 조회에 문제가 있을 경우
                    throw new SQLException("사장님 이름 조회 오류 발생", "SQLCODE", -1);
                }
                System.out.println(Oname + " 사장님, 환영합니다!");
                System.out.println("원하시는 메뉴를 선택해주세요:");
                System.out.println("****************");
                return Oid;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다. 메인 메뉴로 돌아갑니다.");
                System.out.println("****************");
                return -1;
            }
        } catch (SQLException e) { // SQLCODE
            System.out.println("로그인 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
            return -1;
        }
    }
}
