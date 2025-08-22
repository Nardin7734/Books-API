package com.interact.repositories;

import com.interact.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles( "test" )
class UserRepositoryTest
{
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName( "Should find a user by e-mail" )
    void findByEmailSuccess()
    {
        //Arrange
        String name = "TesteName";
        String email = "teste@gmail.com";
        String password = "TestePassword";

        User user = User.builder()
                        .name(name)
                        .email(email)
                        .password(password).build();

        //Act
        userRepository.save( user );
        Optional<User> userFound = this.userRepository.findByEmail( "teste@gmail.com" );

        //Assert
        Assertions.assertNotNull( userFound );
        Assertions.assertEquals( name, userFound.<Object>map(User::getName).orElse(null));
        Assertions.assertEquals( email, userFound.<Object>map(User::getEmail).orElse(null));
        Assertions.assertEquals( password, userFound.<Object>map(User::getPassword).orElse(null));
    }
}
