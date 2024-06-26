package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public Courier() {
    }

    public static Courier generic() {
        return new Courier("Quicksilver", "1234", "Maximoff");
    }
    public static Courier random() {
        return new Courier(RandomStringUtils.randomAlphabetic(5,15), "1111", "Vadik");
    }
    public static Courier withoutLoggin(){
        return new Courier(null, "1234", "Saske");
    }
    public static Courier withoutPassword(){
        return new Courier(RandomStringUtils.randomAlphabetic(5,15), null, "Saske");
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
