package com.postgresql.order.service;

import com.postgresql.order.entity.Users;
import com.postgresql.order.exception.DuplicateResourceException;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(Users user) throws DuplicateResourceException {
        log.info("create user started");
        try{
            String email = user.getEmail();
            if(usersRepository.existsUsersByEmail(email)){
                log.info("user already exists");
                throw new DuplicateResourceException("User with email {%s} already exists" .formatted(email));
            }
            Users saveUser = usersRepository.save(user);
            log.debug("user saved successfully {}", user );

            return saveUser;
        }catch (Exception ex){
            log.debug("Error creating user {}", ex.getMessage());
            throw new MainServiceBusinessException("Error creating user");
        }

    }

    public Users getUserById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }
}
