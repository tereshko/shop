package me.tereshko.shop.tests;

import me.tereshko.shop.models.Role;
import me.tereshko.shop.models.User;
import me.tereshko.shop.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class UserRegistrationTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void registrationTest() throws Exception {
        Role role = new Role();
        role.setId(1L);

        User user = new User();

        user.setPassword("1");
        user.setUsername("test");
        user.setFirst_name("test");
        user.setLast_name("test");
        userRepository.save(user);


        Optional<User> userFromDb = userRepository.findByUsername("test");
        Assertions.assertEquals("test", userFromDb.get().getUsername());

    }
}
