package dto;

public class Sales {
    private int id;
    private int user_id;
    private int menu_id;

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", menu_id=" + menu_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
}
