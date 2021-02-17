package me.tereshko.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.tereshko.shop.models.User;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String first_name;
    private String last_name;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
    }
}
