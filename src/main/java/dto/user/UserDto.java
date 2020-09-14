package dto.user;

import models.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDto {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String telephone;
    private final String image;
    private final String status;
    private final Date dateAdded;
    private final String group;

    public UserDto(User user){
        this.id = String.valueOf(user.getId());
        this.firstName = user.getPerson().getFirstName();
        this.lastName = user.getPerson().getLastName();
        this.email = user.getPerson().getEmail();
        this.telephone = user.getPerson().getTelephone();
        this.image = user.getImage();
        this.status = (user.getPerson().isStatus()) ? "Active" : "Inactive";
        this.dateAdded = user.getPerson().getDateAdded();
        this.group = user.getUserGroup().getName();
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

    public String getStatus() {
        return status;
    }

    public String getDateAdded(){
        String pattern = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String prettyDateAdded = simpleDateFormat.format(this.dateAdded);
        return  prettyDateAdded;
    }

    public String getGroup() {
        return group;
    }
}
