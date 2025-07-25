package service.settingView;

import dto.Menu;
import dto.User;
import service.UserService;
import service.settingView.service.UserSettingService;
import view.AdminView;

import java.util.Scanner;

public class UserSettingView {
    Scanner sc = new Scanner(System.in);
    UserSettingService userSettingService = new UserSettingService();
    UserService userService = new UserService();
    private AdminView adminView;

    public UserSettingView(AdminView adminView) {
        this.adminView = adminView;
    }

    public void addUser() {
        User user = new User();
        System.out.println("-- 회원정보 입력 --");

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
        int userId = userService.newMember(user);
        if (userId != -1) {
            System.out.println("회원 입력이 완료되었습니다. ID는 < " + userId + " > 입니다.");
        } else {
            System.out.println("회원 입력에 실패했습니다.");
        }
        adminView.printUser();
    }

    public void updateUser() {
        while (true) {
            System.out.println("-- 회원정보 수정 --");
            adminView.printUser();
            System.out.println("수정할 Id를 입력하세요. 0. 돌아가기");
            int userId = sc.nextInt();
            if (userId == 0) {
                return;
            }
            User user = userSettingService.checkUser(userId);

            if (user == null) {
                System.out.println("해당 회원이 존재하지 않습니다.");
                return;
            }

            System.out.println("현재 회원 정보 : " + user.toString());
            System.out.println("수정할 항목을 선택하세요.");
            System.out.println("1. 이름  /  2. 전화번호  /  3. 카드번호   /  4. 비밀번호  /  5. 돌아가기");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("수정할 이름을 입력하세요.");
                    String updateName = sc.next();
                    userSettingService.updateUser(userId, "name", updateName);
                    continue;
                case 2:
                    System.out.println("수정할 전화번호를 입력하세요.");
                    String updatePhone = sc.next();
                    userSettingService.updateUser(userId, "phone_num", updatePhone);
                    continue;
                case 3:
                    System.out.println("수정할 카드번호를 입력하세요.");
                    String updateCard = sc.next();
                    userSettingService.updateUser(userId, "card_num", updateCard);
                    continue;
                case 4:
                    System.out.println("수정할 비밀번호를 입력하세요.");
                    String updatePass = sc.next();
                    userSettingService.updateUser(userId, "password", updatePass);
                    continue;
                case 5:
                    return;
            }
            System.out.println("회원정보가 수정되었습니다.");
            adminView.printUser();
        }
    }

    public void deleteUser() {
        while (true) {
            System.out.println("-- 회원정보 삭제 --");
            adminView.printUser();
            System.out.println("삭제할 회원의 Id를 입력하세요. 0. 돌아가기");
            int userId = sc.nextInt();
            if (userId == 0) {
                return;
            }

            User user = userSettingService.checkUser(userId);
            if (user == null) {
                System.out.println("해당 회원은 존재하지 않습니다.");
                return;
            }
            System.out.println(user.getName() + "님을 정말 삭제하시겠습니까?");
            System.out.println("1. 예  /  2. 아니요");
            int choice = sc.nextInt();
            if (choice == 1) {
                userSettingService.deleteUser(userId);
                System.out.println("회원정보가 삭제되었습니다.");
            } else {
                System.out.println("삭제가 취소되었습니다.");
            }
            adminView.printUser();
            return;
        }
    }
}
