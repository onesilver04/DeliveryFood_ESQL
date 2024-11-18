package ESQL.DeliveryFood;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 테이블 생성
        CreateTable createTable = new CreateTable();
        createTable.createConsumerTable();
        createTable.createOwnerTable();
        createTable.createMenuTable();
        createTable.createOrdersTable();

        // Insert.java로 기존 데이터 삽입
        Insert insert = new Insert();
        insert.setConsumerData();
        insert.setOwnerData();
        insert.setMenuData();
        insert.setOrdersData();

        Register register = new Register();
        Login login = new Login();
        SelectData selectData = new SelectData();
        Delete delete = new Delete();
        Update update = new Update(); // 개인정보 수정
        UpdateMenu updateMenu = new UpdateMenu(); // 사장님의 메뉴 수정
        AddMenu addMenu = new AddMenu(); // 사장님의 메뉴 추가
        InsertOrder insertOrder = new InsertOrder();
        Scanner scanner = new Scanner(System.in);

        // 최초 상태는 로그인 되지 않도록 설정
        int loggedInCid = -1;
        int loggedInOid = -1;

        while (true) {
            System.out.println("****************");
            System.out.println("배달 주문 및 내역 조회 시스템 메인 화면입니다.");
            System.out.println("원하시는 메뉴를 선택해주세요: ");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 종료");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:  // 회원가입
                    System.out.println("1. 손님 회원가입");
                    System.out.println("2. 사장님 회원가입");
                    int signupChoice = scanner.nextInt();

                    if (signupChoice == 1) {
                        register.registerCustomer(); // 손님 회원가입 호출
                    } else if (signupChoice == 2) {
                        register.registerOwner(); // 사장님 회원가입 호출
                    }
                    break;

                case 2:  // 로그인
                    System.out.println("1. 손님 로그인");
                    System.out.println("2. 사장님 로그인");
                    int loginChoice = scanner.nextInt();

                    if (loginChoice == 1) { // 1. 손님 로그인 선택
                        loggedInCid = login.customerLogin();
                        if (loggedInCid != -1) {
                            while (loggedInCid != -1) {
                                System.out.println("1. 정보 조회");
                                System.out.println("2. 주문 내역 조회");
                                System.out.println("3. 정보 삭제");
                                System.out.println("4. 정보 수정");
                                System.out.println("5. 음식 주문하기");
                                System.out.println("6. 로그아웃");
                                int action = scanner.nextInt();
                                switch (action) {
                                    case 1:
                                        selectData.selectConsumerInfo(loggedInCid);
                                        break;
                                    case 2:
                                        selectData.selectOrderDetails(loggedInCid);
                                        break;
                                    case 3:
                                        delete.deleteCustomer(loggedInCid);
                                        loggedInCid = -1; // 삭제 후 로그아웃 처리
                                        System.out.println("손님 정보가 삭제되었습니다. 메인 메뉴로 돌아갑니다.");
                                        break;
                                    case 4:
                                        update.updateCustomer(loggedInCid); // 손님 정보 수정
                                        break;
                                    case 5:
                                        insertOrder.placeOrder(loggedInCid); // 손님의 Cid를 전달하여 주문 기능 호출
                                        break;
                                    case 6:
                                        System.out.println("로그아웃되었습니다.");
                                        loggedInCid = -1;
                                        break;
                                    default:
                                        System.out.println("올바른 번호를 선택해주세요.");
                                }
                            }
                        }
                    } else if (loginChoice == 2) { // 2. 사장님 로그인 선택
                        loggedInOid = login.ownerLogin();
                        if (loggedInOid != -1) {
                            while (loggedInOid != -1) {
                                System.out.println("1. 정보 조회");
                                System.out.println("2. 메뉴 조회");
                                System.out.println("3. 정보 삭제");
                                System.out.println("4. 개인 정보 수정");
                                System.out.println("5. 메뉴 정보 수정");
                                System.out.println("6. 메뉴 추가");
                                System.out.println("7. 메뉴 삭제");
                                System.out.println("8. 로그아웃");
                                int action = scanner.nextInt();
                                switch (action) {
                                    case 1:
                                        selectData.selectOwnerInfo(loggedInOid); // 사장님 정보 조회
                                        break;
                                    case 2:
                                        selectData.selectMenuByOwner(loggedInOid); // 사장님의 메뉴 조회
                                        break;
                                    case 3:
                                        delete.deleteOwner(loggedInOid);
                                        loggedInOid = -1; // 삭제 후 로그아웃 처리
                                        System.out.println("사장님 정보가 삭제되었습니다. 메인 메뉴로 돌아갑니다.");
                                        break;
                                    case 4:
                                        update.updateOwner(loggedInOid); // 사장님 정보 수정
                                        break;
                                    case 5: // 메뉴 정보 수정
                                        updateMenu.updateMenuInfo(loggedInOid); // 사장님 메뉴 수정
                                        break;
                                    case 6: // 메뉴 추가
                                        addMenu.addMenu(loggedInOid); // 사장님의 Oid를 전달하여 메뉴 추가 기능 호출
                                        break;
                                    case 7:
                                        delete.deleteMenu(loggedInOid); // 메뉴 삭제 기능 호출
                                        break;
                                    case 8:
                                        System.out.println("로그아웃되었습니다.");
                                        loggedInOid = -1;
                                        break;
                                    default:
                                        System.out.println("올바른 번호를 선택해주세요.");
                                }
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;

                default:
                    System.out.println("올바른 번호를 선택해주세요.");
            }
        }
    }
}
