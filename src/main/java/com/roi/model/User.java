package com.roi.model;

//@Component
public class User {

    private String login;
    private String password;
    private String fio;
    private String level;

    public User(String login, String password, String fio, String level) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.level = level;
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

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
