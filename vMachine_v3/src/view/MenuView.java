package view;

import dto.Menu;
import dto.User;
import service.AdminService;
import service.MenuService;

import java.util.List;
import java.util.Scanner;

public class MenuView {
    static Scanner sc = new Scanner(System.in);
    static MenuService menuService = new MenuService();
    static AdminService adminService = new AdminService();
    static UserView userView = new UserView();


    public void printMenu() {
        System.out.println("-- 메뉴 정보 --");
        System.out.println("----------------------------------------------------------");
        List<Menu> menus = menuService.allMenu();
        menus.stream().forEach(x -> System.out.println(x));
        System.out.println("----------------------------------------------------------");
    }

    public void buyMenu(User user) {
        while (true) {
            printMenu();
            List<Menu> menus = menuService.allMenu();
            System.out.println("구매하실 제품 번호를 입력하세요.");
            int menu = sc.nextInt();
            Menu check = menuService.checkMenu(menu);

            if (check == null) {
                System.out.println("해당 제품이 존재하지 않습니다.");
                continue;
            }
            if (check.getStock() == 0) {
                System.out.println("죄송합니다. 해당 제품은 현재 재고가 없습니다.");
                continue;
            }
            if (user.getMoney() < check.getPrice()) {
                System.out.println("금액이 부족합니다.");
                if (!userView.addMoney(user)) {
                    System.out.println("로그아웃 되었습니다. 처음으로 돌아갑니다.");
                    return;
                }
                continue;
            }
            System.out.println(check.getName() + " 구매가 완료되었습니다.");
            menuService.saleMenu(user, check);
            System.out.println("현재 잔액 : " + user.getMoney() + "원");

            System.out.println("더 구매하시겠습니까?");
            System.out.println("1. 예  /  2. 아니요 ( 로그아웃 )");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    continue;
                case 2:
                    System.out.println("로그아웃 되었습니다. 처음으로 돌아갑니다.");
                    return;
            }

        }
    }
}

