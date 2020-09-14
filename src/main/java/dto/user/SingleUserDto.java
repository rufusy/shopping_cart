package dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userFound", "user"})
public class SingleUserDto {
    private final UserDto user;

    public SingleUserDto(UserDto user){
        this.user = user;
    }

    @JsonProperty("user")
    public UserDto getUser() {
        return this.user;
    }

    @JsonProperty("userFound")
    public boolean isUserFound(){
        return true;
    }
}
