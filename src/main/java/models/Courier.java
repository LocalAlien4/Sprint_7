package models;

public class Courier {



    private String login;
    private String password;
    private String firstName;

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public Courier withLogin(String login) {
        this.login = login;
        return this;
    }
    public Courier withPassword(String password) {
        this.password = password;
        return this;
    }
    public Courier withFirstname(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public void setLoginAndPass(String login, String password) {
        this.login = login;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Courier{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
