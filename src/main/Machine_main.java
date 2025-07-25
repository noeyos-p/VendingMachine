package main;

import dto.User;
import service.AdminService;
import service.MenuService;
import service.UserService;
import view.AdminView;
import view.MenuView;
import view.UserView;

import java.util.Scanner;

public class Machine_main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        UserService userService = new UserService();
        UserView userView = new UserView();
        AdminView adminView = new AdminView();
        MenuView menuView = new MenuView();
        MenuService menuService = new MenuService();

        while (true) {
            System.out.println("-- 자판기 --");
            System.out.println("안녕하세요 :)  저희는 회원제로 운영되고 있습니다.");
            System.out.println("1. 회원가입  /  2. 로그인  /  3. 종료");
            System.out.println("원하시는 번호를 입력해주세요.");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    userView.inputMember();
                    continue;
                case 2:
                    User user = userView.login();
                    if (user != null) {
                        userView.addMoney(user);
                        menuView.buyMenu(user);
                    } else {
                        System.out.println("처음으로 돌아갑니다.");
                    }
                    continue;
                case 3:
                    System.out.println("-- 시스템 종료 --");
                    System.out.println("이용해주셔서 감사합니다. 좋은 하루 되세요 :)");
                    return;
            }
        }
    }
}
