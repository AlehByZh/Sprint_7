package org.example.Courier;

public class WrongCourierCredentials {
    private String login;
    private String password;

    public WrongCourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public WrongCourierCredentials() {
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static WrongCourierCredentials from(Courier courier) {
        return new WrongCourierCredentials(courier.getLogin(), "1114");
    }
}
