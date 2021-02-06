package me.tereshko.shop.dto;

import lombok.Data;
import me.tereshko.shop.models.User;

@Data
public class UserDto {
    private String username;
    private String first_name;
    private String last_name;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
    }
}
