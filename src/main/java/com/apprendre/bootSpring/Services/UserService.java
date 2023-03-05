package com.apprendre.bootSpring.Services;

import com.apprendre.bootSpring.Models.UserModel;
import com.apprendre.bootSpring.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserModel> getUser() {
        return userRepository.findAll();
    }

    public void addUser(UserModel userModel) {
        // if name exist
        if (userRepository.findByName(userModel.getName()).isPresent()) {
            throw new IllegalStateException("name exist");
        }
        userRepository.save(userModel);
    }

    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("user not found"));
    }


    @Transactional
    public void updateUser(Long id, UserModel userModel) {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("user not found"));
        if (userModel.getName() != null && userModel.getName().length() > 0 && !userModel.getName().equals(user.getName())) {
            user.setName(userModel.getName());
        }
        if (userModel.getEmail() != null && userModel.getEmail().length() > 0 && !userModel.getEmail().equals(user.getEmail())) {
            user.setEmail(userModel.getEmail());
        }
        if (userModel.getPassword() != null && userModel.getPassword().length() > 0 && !userModel.getPassword().equals(user.getPassword())) {
            user.setPassword(userModel.getPassword());
        }
        userRepository.save(user);
    }


    public void deleteUser(Long id) {
        boolean exist = userRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("user not found");
        }
        userRepository.deleteById(id);
    }
}
