package com.dn.repositories;

import com.dn.models.User;
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

    //Arrange
    String email = "teste@gmail.com";


    @Test
    @DisplayName( "Should find a user by e-mail" )
    void findByEmailSuccess()
    {
        //Arrange
        String name = "TesteName";
        String password = "TestePassword";

        User user = User.builder()
                        .name(name)
                        .email(email)
                        .password(password).build();

        //Act
        userRepository.save( user );
       User userFound = this.userRepository.findByEmail( email );

        //Assert
        Assertions.assertNotNull( userFound );
        Assertions.assertEquals( name, userFound.getName() );
        Assertions.assertEquals( email, userFound.getEmail() );
        Assertions.assertEquals( password, userFound.getPassword() );
    }

    @Test
    @DisplayName( "Should return null if doesn't exist the e-mail passed" )
    void findByEmailFail()
    {
        User userFound = this.userRepository.findByEmail( email );

        Assertions.assertNull( userFound );
    }
}
