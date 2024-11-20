// 로그인 메서드
package ESQL.DeliveryFood;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private SelectData selectData = new SelectData();
    private InsertData insertData = new InsertData();
    private Pwd pwd = new Pwd(); // 비밀번호 검증을 위한 Pwd 클래스 사용
    private Scanner scanner = new Scanner(System.in);

    // 손님 로그인 메서드
    public int customerLogin() {
        int Cid = -1;
        while (true) {
            try {
                System.out.println("손님 ID 입력(4자리 숫자):");
                Cid = scanner.nextInt();
                if (Cid < 1000 || Cid > 9999) { // 자릿수 확인
                    System.out.println("4자리 숫자가 맞는지 확인해주세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                } else if (!insertData.isConsumerIdAvailable(Cid)) {
                    System.out.println(Cid + "은(는) 존재하지 않는 손님 ID입니다.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }
                break; // 올바른 입력일 경우 반복 종료
            } catch (InputMismatchException e) {
                System.out.println("ID는 숫자로만 구성되어 있습니다.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }

        while (true) {
            try {
                System.out.println("비밀번호 입력(4자리 숫자):");
                int inputPassword = scanner.nextInt();
                if (inputPassword < 1000 || inputPassword > 9999) { // 자릿수 확인
                    System.out.println("4자리 숫자가 맞는지 확인해주세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }

                // 비밀번호 검증
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
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("비밀번호는 숫자로만 구성되어 있습니다.");
                scanner.nextLine(); // 잘못된 입력 제거
            } catch (SQLException e) { // SQLCODE
                System.out.println("로그인 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
                return -1;
            }
        }
    }

    // 사장님 로그인 메서드
    public int ownerLogin() {
        int Oid = -1;
        while (true) {
            try {
                System.out.println("사장님 ID 입력(4자리 숫자):");
                Oid = scanner.nextInt();
                if (Oid < 1000 || Oid > 9999) { // 자릿수 확인
                    System.out.println("4자리 숫자가 맞는지 확인해주세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                } else if (!insertData.isOwnerIdAvailable(Oid)) {
                    System.out.println(Oid + "은(는) 존재하지 않는 사장님 ID입니다.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }
                break; // 올바른 입력일 경우 반복 종료
            } catch (InputMismatchException e) {
                System.out.println("ID는 숫자로만 구성되어 있습니다.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }

        while (true) {
            try {
                System.out.println("비밀번호 입력(4자리 숫자):");
                int inputPassword = scanner.nextInt();
                if (inputPassword < 1000 || inputPassword > 9999) { // 자릿수 확인
                    System.out.println("4자리 숫자가 맞는지 확인해주세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }

                // 비밀번호 검증
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
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("비밀번호는 숫자로만 구성되어 있습니다.");
                scanner.nextLine(); // 잘못된 입력 제거
            } catch (SQLException e) { // SQLCODE
                System.out.println("로그인 중 오류 발생 (SQLCODE: " + e.getErrorCode() + "): " + e.getMessage());
                return -1;
            }
        }
    }
}
