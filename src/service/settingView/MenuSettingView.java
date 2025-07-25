package service.settingView;

import dto.Menu;
import service.MenuService;
import service.settingView.service.MenuSettingService;
import view.MenuView;

import java.util.Scanner;

public class MenuSettingView {
    Scanner sc = new Scanner(System.in);
    MenuView menuView = new MenuView();
    MenuSettingService menuSettingService = new MenuSettingService();
    MenuService menuService = new MenuService();

    public void addMenu() {
        Menu newMenu = new Menu();
        System.out.println("-- 제품 입력 --");
        System.out.println("등록할 제품명을 입력하세요.");
        newMenu.setName(sc.next());

        System.out.println("가격을 입력하세요.");
        newMenu.setPrice(sc.nextInt());

        System.out.println("재고 수량을 입력하세요.");
        newMenu.setStock(sc.nextInt());

        boolean result = menuSettingService.addMenuService(newMenu);
        if (result) {
            System.out.println("제품 등록이 완료되었습니다");
        } else {
            System.out.println("추가할 수 없습니다. 다시 시도하세요.");
        }
        menuView.printMenu();
    }

    public void updateMenu() {
        while (true) {
            menuView.printMenu();
            System.out.println("-- 제품 수정 --");
            System.out.println("수정할 Id를 입력하세요. 0. 돌아가기");
            int menuId = sc.nextInt();
            if (menuId == 0) {
                return;
            }

            Menu menu = menuService.checkMenu(menuId);

            if (menu == null) {
                System.out.println("해당 메뉴는 존재하지 않습니다.");
                return;
            }
            System.out.println("수정할 항목을 선택하세요.");
            System.out.println("1. 이름  /  2. 가격  /  3. 재고  / 4. 돌아가기");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("수정할 이름을 입력하세요.");
                    String updateName = sc.next();
                    menu.setName(updateName);
                    boolean result = menuSettingService.updateMenuService(menu);
                    System.out.println("메뉴가 수정되었습니다.");
                    menuView.printMenu();
                    break;
                case 2:
                    System.out.println("수정할 가격을 입력하세요.");
                    int updatePrice = sc.nextInt();
                    menu.setPrice(updatePrice);
                    result = menuSettingService.updateMenuService(menu);
                    System.out.println("메뉴가 수정되었습니다.");
                    menuView.printMenu();
                    break;
                case 3:
                    System.out.println("수정할 재고를 입력하세요.");
                    int updateStock = sc.nextInt();
                    menu.setStock(updateStock);
                    result = menuSettingService.updateMenuService(menu);
                    System.out.println("메뉴가 수정되었습니다.");
                    menuView.printMenu();
                    break;
                case 4:
                    return;
            }
        }
    }

    public void deleteMenu() {
        while (true) {
            menuView.printMenu();
            System.out.println("-- 제품 삭제 --");
            System.out.println("삭제할 메뉴의 Id를 입력하세요. 0. 돌아가기");
            int menuId = sc.nextInt();
            if (menuId == 0) {
                return;
            }
            Menu menu = menuService.checkMenu(menuId);
            if (menu == null) {
                System.out.println("해당 메뉴는 존재하지 않습니다.");
                return;
            }
            System.out.println(menu.getName() + "를 정말 삭제하시겠습니까?");
            System.out.println("1. 예  /  2. 아니요");
            int choice = sc.nextInt();
            if (choice == 1) {
                menuSettingService.deleteMenuService(menuId);
                System.out.println("메뉴가 삭제되었습니다.");
                menuView.printMenu();
                break;
            } else {
                System.out.println("삭제가 취소되었습니다.");
                break;
            }

        }
    }
}
