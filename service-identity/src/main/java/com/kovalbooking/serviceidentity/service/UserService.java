package com.kovalbooking.serviceidentity.service;

import com.kovalbooking.serviceidentity.repo.UserRepo;
import com.kovalbooking.serviceidentity.repo.model.User;
import com.kovalbooking.serviceidentity.repo.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserService {
    private final UserRepo userRepo;

    public List<User> fetchAll() {
        return userRepo.findAll();
    }

    public User fetchById(long id) throws IllegalArgumentException {
        final Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty()) throw new IllegalArgumentException("No Users found!");
        else return optionalUser.get();
    }

    public long create(String username, String password, UserRole user_role) {
        final User user = new User(username, password, user_role);
        final User saved = userRepo.save(user);
        return saved.getUser_id();
    }

    public void update(long user_id, String username, String password, UserRole user_role) throws IllegalArgumentException {
        final Optional<User> optionalUser = userRepo.findById(user_id);
        if (optionalUser.isEmpty()) throw new IllegalArgumentException("No Users found!");

        final User User = optionalUser.get();
        if(username != null && !username.isBlank()) User.setUsername(username);
        if(password != null && !password.isBlank()) User.setPassword(password);
        if(user_role != null) User.setUser_role(user_role);

        userRepo.save(User);
    }

    public void delete(long user_id) throws IllegalArgumentException {
        userRepo.deleteById(user_id);
    }
}
