package entity;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import javax.persistence.*;
import java.io.Serializable;


@Entity
@IdClass(UserRoleKey.class)
@Table(name = "user_roles")
public class UserRoles implements Serializable {

    @Id
    @Column(name = "login", length = 15, nullable = false)
    private String login;

    @Id
    @Column(name = "role", length = 15, nullable = false)
    private String role;

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

        UserRoles userRoles = (UserRoles) o;

        if (!login.equals(userRoles.login)) return false;
        return role.equals(userRoles.role);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
