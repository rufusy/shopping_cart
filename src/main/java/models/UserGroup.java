package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sc_users_group")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_group_id")
    private int id;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String permission;

    @OneToMany(mappedBy="userGroup",
    cascade={CascadeType.PERSIST, CascadeType.MERGE},
    fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<User>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
