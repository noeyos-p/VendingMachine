package view;

import dto.User;
import sales.MenuSales;
import sales.UserSales;
import service.AdminService;
import service.settingView.MenuSettingView;
import service.settingView.UserSettingView;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    Scanner sc = new Scanner(System.in);
    AdminService adminService = new AdminService();
    MenuView menuView = new MenuView();
    UserSettingView userSettingView;
    MenuSettingView menuSettingView = new MenuSettingView();

    public AdminView() {
        userSettingView = new UserSettingView(this);
    }
    public void adminMenu() {
        while (true) {
            System.out.println("-- 관리자 --");

            System.out.println("1. 제품 관리  /  2. 회원 관리  /  3.  판매현황  /  4. 돌아가기");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    setMenu();
                    break;
                case 2:
                    setUser();
                    break;
                case 3:
                    break;
                case 4:
                    return;
            }
        }
    }

    public void setMenu() {
        while (true) {
            System.out.println("-- 제품 관리 --");
            System.out.println("1. 제품 입력  /  2. 제품 수정  /  3. 제품 삭제  /  4. 제품 조회  /  5. 돌아가기");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    menuSettingView.addMenu();
                    break;
                case 2:
                    menuSettingView.updateMenu();
                    break;
                case 3:
                    menuSettingView.deleteMenu();
                    break;
                case 4:
                    menuView.printMenu();
                    break;
                case 5:
                    return;
            }
        }
    }

    public void setUser() {
        while (true) {
            System.out.println("--  회원 관리 --");
            System.out.println("1. 회원 입력  /  2. 회원 수정  /  3. 회원 삭제  /  4. 회원 조회  /  5. 돌아가기");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    userSettingView.addUser();
                    break;
                case 2:
                    userSettingView.updateUser();
                    break;
                case 3:
                    userSettingView.deleteUser();
                    break;
                case 4:
                    printUser();
                    break;
                case 5:
                    return;
            }
        }
    }
    public void setSale() {
        while (true) {
            System.out.println("-- 판매현황 관리 --");
            System.out.println("1. 제품별 판매현황  /  2. 회원별 판매현황  /  3. 돌아가기");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    MenuSales menuSales = new MenuSales();
                    menuSales.menuSalesStatus();
                    break;
                case 2:
                    UserSales userSales = new UserSales();
                    userSales.userSalesStatus();
                    break;
                case 3:
                    return;
            }
        }
    }


    public void printUser() {
        AdminService adminService = new AdminService();
        System.out.println("-- 회원 정보 --");
        System.out.println("----------------------------------------------------------");
        List<User> users = adminService.allUsers();
        users.stream().forEach(x -> System.out.println(x));
        System.out.println("----------------------------------------------------------");
    }
}

