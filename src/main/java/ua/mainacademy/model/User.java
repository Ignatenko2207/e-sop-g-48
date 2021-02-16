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

    public User(Integer id, String login, String password, String firstName, String lastName) {
        super.setId(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
