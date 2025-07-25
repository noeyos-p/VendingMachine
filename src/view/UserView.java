package view;

import dto.User;
import service.UserService;

import java.util.Scanner;

public class UserView {
    static Scanner sc = new Scanner(System.in);
    static UserService userService = new UserService();
    AdminView adminView = new AdminView();

    public void inputMember() {
        User user = new User();
        System.out.println("-- 회원가입 --");

        System.out.println("이름을 입력하세요.");
        user.setName(sc.next());

        System.out.println("전화번호를 입력하세요.");
        user.setPhoneNum(sc.next());

        while (true) {
            System.out.println("카드번호 4자리를 입력하세요.");
            String cardNum = sc.next();
            if (userService.checkCard(cardNum)) {
                System.out.println("이미 등록된 카드입니다. 다시 입력하세요.");
            } else {
                user.setCardNum(cardNum);
                break;
            }
        }

        System.out.println("비밀번호 4자리를 입력하세요.");
        user.setPassword(sc.next());

        int money;
        while (true) {
            System.out.println("충전은 1000원 단위로 가능합니다. 충전 할 금액을 입력하세요.");
            money = sc.nextInt();
            if (money % 1000 != 0 || money <= 0) {
                System.out.println("잘못 입력하셨습니다.");
                continue;
            }
            System.out.println("충전이 완료되었습니다. 현재 금액은 " + money + "원 입니다.");
            break;
        }
        user.setMoney(money);
        userService.newMember(user);
        int userId = userService.newMember(user);
        if (userId != -1) {
            System.out.println("회원가입이 완료되었습니다. ID는 < " + userId + " > 입니다.");
        } else {
            System.out.println("회원가입에 실패했습니다.");
        }

        System.out.println("처음으로 돌아갑니다. 로그인 후 이용해주세요 :)");
    }

    public User login() {
        System.out.println("-- 로그인 --");

        System.out.println("ID를 입력하세요.");
        String id = sc.next();

        if (id.equals("admin")) {
            System.out.println("-- 관리자 --");
            System.out.println("비밀번호를 입력하세요.");
            String password = sc.next();

            if (password.equals("1111")) {
                adminView.adminMenu();
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
            return null;
        }
        int userId = Integer.parseInt(id);
        User userID = userService.checkID(userId);
        if (userID == null) {
            return null;
        }

        System.out.println("비밀번호를 입력하세요.");
        String password = sc.next();
        User userPass = userService.checkPass(userId, password);
        if (userPass != null) {
            System.out.println(userPass.getName() + "님 로그인 되었습니다.");
            return userPass;
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return null;
        }
    }


    public boolean addMoney(User user) {
        System.out.println("-- 카드 충전 --");
        System.out.println("현재 금액은 " + user.getMoney() + "원 입니다.");
        int money;
        System.out.println("금액을 충전하시겠습니까?");
        System.out.println("1. 예  /  2. 아니요 ");
        int choice = sc.nextInt();
        if (choice != 1) {
            return false;
        }
        while (true) {
            System.out.println("충전은 1000원 단위로 가능합니다.");
            System.out.println("충전 할 금액을 입력하세요.");
            money = sc.nextInt();
            if (money % 1000 == 0 && money > 0) {
                break;
            } else {
                System.out.println("잘못 입력하셨습니다.");
            }
        }
        user.setMoney(user.getMoney() + money);
        System.out.println("충전이 완료되었습니다.");
        System.out.println("현재 금액은 " + user.getMoney() + "원 입니다.");
        return true;
    }

}


