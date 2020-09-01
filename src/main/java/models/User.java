package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "sc_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @Embedded
    private Person person;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_group_id", nullable = false, referencedColumnName = "user_group_id")
    private UserGroup userGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
