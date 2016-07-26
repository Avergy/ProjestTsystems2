package entity;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import java.io.Serializable;


public class UserRoleKey implements Serializable {
    String login;
    String role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleKey that = (UserRoleKey) o;

        if (!login.equals(that.login)) return false;
        return role.equals(that.role);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
