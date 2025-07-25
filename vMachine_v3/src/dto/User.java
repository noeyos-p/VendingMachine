package dto;

public class User {
    private int id;
    private String name;
    private String phoneNum;
    private String password;
    private String cardNum;
    private int money;

    @Override
    public String toString() {
        return id + ". " + name + " / " +
                phoneNum + " / " +
                "카드번호 : " +
                cardNum + " / " +
                "비밀번호 : " +
                 password + " / " +
                 money + "원";
    }

    public User(){}
    public User(String name, String phoneNum, String password, String cardNum) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.password = password;
        this.cardNum = cardNum;
        this.money = 0;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
