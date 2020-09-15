package dto.user;

import models.User;

public class UserDto {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String telephone;
    private final String image;
    private final String group;
    private final String status;
    private final String creationDate;

    public UserDto(User user){
        this.id = String.valueOf(user.getId());
        this.firstName = user.getPerson().getFirstName();
        this.lastName = user.getPerson().getLastName();
        this.email = user.getPerson().getEmail();
        this.telephone = user.getPerson().getTelephone();
        this.image = user.getImage();
        this.group = user.getUserGroup().getName();
        this.creationDate = user.getPerson().userCreationDate();
        this.status = user.getPerson().personStatus();
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getImage() {
        return image;
    }

    public String getGroup() {
        return group;
    }

    public String getStatus() {
        return status;
    }

    public String getCreationDate() {
        return creationDate;
    }

}
