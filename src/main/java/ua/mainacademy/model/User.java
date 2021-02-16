package ua.mainacademy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseEntity{

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public User(Integer id, String login, String password, String firstName, String lastName, String email, String phone) {
        super.setId(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
