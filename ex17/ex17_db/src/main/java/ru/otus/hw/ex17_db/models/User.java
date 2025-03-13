package ru.otus.hw.ex17_db.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

/*
    Выбрать пользователя с которым будет игра.
    Идентификатора и имени будет достаточно.
 */
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    @Column("name")
    private String name;

    @Column("login")
    private String login;


    @Column("password")
    private String password;

    @Column("role")
    private String role;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}