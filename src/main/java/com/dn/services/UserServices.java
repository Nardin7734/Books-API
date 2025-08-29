package com.dn.services;

import com.dn.data.dto.UserDTO;
import com.dn.mappers.ObjectMapper;
import com.dn.models.User;
import com.dn.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServices
{

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;

    @Autowired
    ObjectMapper mapper;

    public UserDTO findById(long id) throws Exception {
        logger.info("Finding one user");

        var entity = repository.findById(id)
                .orElseThrow(() -> new BadRequestException("No records found for this ID"));

        return mapper.parseObject(entity, UserDTO.class);
    }

    public UserDTO findByEmail( String email ) throws Exception
    {
        logger.info("Finding user by e-mail: " + email);

        var entity = repository.findByEmail(email);

        return mapper.parseObject( entity, UserDTO.class );
    }

    public List<UserDTO> findAll() throws Exception {
        return mapper.parseList(repository.findAll(), UserDTO.class);
    }

    public UserDTO create(UserDTO userDTO) throws Exception {
        logger.info("Creating new user");

        String encript = new BCryptPasswordEncoder().encode( userDTO.getPassword() );
        userDTO.setPassword( encript );
        userDTO.setRole( "USER" );

        var entity = repository.save( mapper.parseObject(userDTO, User.class) );
        return mapper.parseObject( entity, UserDTO.class);
    }

    public UserDTO update(UserDTO userDto) throws Exception {
        User entity = repository.findById(userDto.getId())
                .orElseThrow(() -> new BadRequestException("No records found for this user"));

        logger.info("Atualizando usuário");
        entity.setName(userDto.getName());
        entity.setLastName(userDto.getLastName());
        entity.setPhoneNumber(userDto.getPhoneNumber());
        entity.setEmail(userDto.getEmail());
        entity.setPassword( new BCryptPasswordEncoder().encode( userDto.getPassword() ) );
        entity.setRole( "USER" );

        return mapper.parseObject(repository.save(entity), UserDTO.class);
    }

    public void delete(long id) throws Exception {
        logger.info("Deleting one user");
        User entity = repository.findById(id)
                .orElseThrow(() -> new BadRequestException("No records found for this ID"));

        repository.delete(entity);
    }
}
