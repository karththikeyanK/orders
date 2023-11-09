package com.postgresql.order.service;

import com.postgresql.order.entity.Users;

import com.postgresql.order.exception.DuplicateResourceException;
import com.postgresql.order.exception.MainServiceBusinessException;
import com.postgresql.order.repo.DepartmentRepository;
import com.postgresql.order.repo.UsersRepository;
import com.postgresql.order.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    public Users createUser(Users user) throws DuplicateResourceException {
        log.info("User Service: create user started");
        try{
            String email = user.getEmail();
            if(usersRepository.existsUsersByEmail(email)){
                log.info("User Service: user already exists");
                throw new MainServiceBusinessException("User with email {%s} already exists" .formatted(email));
            }
            Users saveUser = usersRepository.save(user);
            log.info(" User Service: user saved successfully {}", user );

            return saveUser;
        }catch (MainServiceBusinessException e){
            log.error("Error creating user {}", e.getMessage());
            throw e;
        } catch (Exception ex){
            log.error("Error creating user {}", ex.getMessage());
            throw new MainServiceBusinessException("Error creating user");
        }

    }

    public Users getUserById(Long userId) {

        try{
            log.info("UserService: fetching user with id {}", userId);
            return usersRepository.findById(userId).orElseThrow(() -> new MainServiceBusinessException("User with id {%s} not found".formatted(userId)));
        }catch (MainServiceBusinessException e){
            log.error("User Service: Error fetching user {}", e.getMessage());
            throw e;
        } catch (Exception ex){
            log.error("User Service: Error fetching user {}", ex.getMessage());
            throw new MainServiceBusinessException("Error fetching user");
        }

    }

    public Users updateUser(Long userId, UserRequest userRequest){
        try {
            log.info("UserService: updating user with id {}", userId);
            Users user = usersRepository.findById(userId).orElseThrow(() -> new MainServiceBusinessException("User with id {%s} not found".formatted(userId)));
            if (userRequest.getUserName() != null ) {
                user.setUserName(userRequest.getUserName());
            }
            if (userRequest.getEmail() != null ) {
                user.setEmail(userRequest.getEmail());
            }
            if (userRequest.getAddress() != null ) {
                user.setAddress(userRequest.getAddress());
            }

            if (userRequest.getDepartmentId() != null ) {
                user.setDepartment(departmentRepository.findById(userRequest.getDepartmentId()).orElseThrow(() -> new MainServiceBusinessException("Department with id {%s} not found".formatted(userRequest.getDepartmentId()))));
            }

            Users updatedUser = usersRepository.save(user);
            log.info("UserService: user updated successfully {}", updatedUser );
            return updatedUser;
        }catch (MainServiceBusinessException e){
            log.error("UserService: Error updating user {}", e.getMessage());
            throw e;
        } catch (Exception ex){
            log.error("UserService: Error updating user {}", ex.getMessage());
            throw new MainServiceBusinessException("Error updating user");
        }
    }


    public String deleteUser(Long id){
        try{
            log.info("UserService: deleting user with id {}", id);
            Users user = usersRepository.findById(id).orElseThrow(() -> new MainServiceBusinessException("User with id {%s} not found".formatted(id)));
            log.info("UserService: deleting user with id {}", id);
            usersRepository.delete(user);
            return "User deleted successfully";
        }catch (MainServiceBusinessException e){
            log.error("UserService: Error deleting user {}", e.getMessage());
            throw e;
        } catch (Exception ex){
            log.error("UserService: Error deleting user {}", ex.getMessage());
            throw new MainServiceBusinessException("Error deleting user");
        }
    }
}
