package domain;

// WIP
// @author Fie og Etienne
public class User {

    private String userName;
    private String password;

    public User (String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public User (){}

    public String toString(){
        return userName + " " + password;
    }

}
