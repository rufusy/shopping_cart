package dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"usersFound", "users"})
public class ListUserDto {
    private final List<UserDto> users;

    public ListUserDto(List<UserDto> users){
        this.users = users;
    }

    @JsonProperty("users")
    public List<UserDto> getUsers() {
        return new ArrayList<>(users);
    }

    @JsonProperty("usersFound")
    public boolean isUsersFound(){
        return true;
    }
}
