// 회원가입 메서드
package ESQL.DeliveryFood;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Register {
    private InsertData insertData = new InsertData();
    private Scanner scanner = new Scanner(System.in);

    public void registerCustomer() { // 손님 회원가입 메서드
        int Cid = -1;
        while (true) {
            try {
                System.out.println("손님 ID 입력(4자리 정수):");
                Cid = scanner.nextInt();

                if (Cid < 1000 || Cid > 9999) {
                    System.out.println("유효한 4자리 숫자 ID를 입력하세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                } else if (!insertData.isConsumerIdAvailable(Cid)) {
                    System.out.println(Cid + "은(는) 이미 존재하는 손님 ID입니다.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }
                break; // 올바른 ID 입력 시 루프 종료
            } catch (InputMismatchException e) {
                System.out.println("유효한 4자리 숫자 ID를 입력하세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }

        int Cpw = -1;
        while (true) {
            try {
                System.out.println("손님 비밀번호 입력 (4자리 숫자):");
                Cpw = scanner.nextInt();

                if (Cpw < 1000 || Cpw > 9999) {
                    System.out.println("유효한 4자리 숫자 비밀번호를 입력하세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }
                break; // 올바른 비밀번호 입력 시 루프 종료
            } catch (InputMismatchException e) {
                System.out.println("유효한 4자리 숫자 비밀번호를 입력하세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }

        scanner.nextLine();  // 개행 문자 제거
        System.out.println("손님 이름(닉네임) 입력:");
        String Cname = scanner.nextLine();
        System.out.println("손님 위치 입력:");
        String Clocation = scanner.nextLine();

        int Ccontact = -1;
        while (true) {
            try {
                System.out.println("손님 연락처 입력:");
                Ccontact = scanner.nextInt();

                if (Ccontact <= 0) {
                    System.out.println("유효한 숫자를 입력하세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }
                break; // 올바른 연락처 입력 시 루프 종료
            } catch (InputMismatchException e) {
                System.out.println("유효한 숫자를 입력하세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }

        insertData.insertConsumer(Cid, Cname, Clocation, Ccontact, Cpw);
        System.out.println(Cid + " 손님으로 회원가입이 완료되었습니다.");
    }

    // 사장님 회원가입 메서드
    public void registerOwner() {
        int Oid = -1;
        while (true) {
            try {
                System.out.println("사장님 ID 입력(4자리 정수):");
                Oid = scanner.nextInt();

                if (Oid < 1000 || Oid > 9999) {
                    System.out.println("유효한 4자리 숫자 ID를 입력하세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                } else if (!insertData.isOwnerIdAvailable(Oid)) {
                    System.out.println(Oid + "은(는) 이미 존재하는 사장님 ID입니다.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }
                break; // 올바른 ID 입력 시 루프 종료
            } catch (InputMismatchException e) {
                System.out.println("유효한 4자리 숫자 ID를 입력하세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }
        int Opw = -1;
        while (true) {
            try {
                System.out.println("사장님 비밀번호 입력 (4자리 숫자):");
                Opw = scanner.nextInt();

                if (Opw < 1000 || Opw > 9999) {
                    System.out.println("유효한 4자리 숫자 비밀번호를 입력하세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }
                break; // 올바른 비밀번호 입력 시 루프 종료
            } catch (InputMismatchException e) {
                System.out.println("유효한 4자리 숫자 비밀번호를 입력하세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }

        scanner.nextLine();  // 개행 문자 제거
        System.out.println("사장님 이름 입력:");
        String Oname = scanner.nextLine();
        System.out.println("사장님 위치 입력:");
        String Olocation = scanner.nextLine();

        int Ocontact = -1;
        while (true) {
            try {
                System.out.println("사장님 연락처 입력:");
                Ocontact = scanner.nextInt();

                if (Ocontact <= 0) {
                    System.out.println("유효한 숫자를 입력하세요.");
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue;
                }
                break; // 올바른 연락처 입력 시 루프 종료
            } catch (InputMismatchException e) {
                System.out.println("유효한 숫자를 입력하세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }

        insertData.insertOwner(Oid, Oname, Olocation, Ocontact, Opw);
        System.out.println(Oid + " 사장님으로 회원가입이 완료되었습니다.");
    }
}
