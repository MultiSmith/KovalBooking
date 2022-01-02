package com.kovalbooking.serviceidentity.api;

import com.kovalbooking.serviceidentity.api.dto.UserDTO;
import com.kovalbooking.serviceidentity.repo.model.User;
import com.kovalbooking.serviceidentity.repo.model.UserRole;
import com.kovalbooking.serviceidentity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public final class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> index() {
        final List<User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> showById(@PathVariable long user_id) {
        try {
            final User user = userService.fetchById(user_id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO user) {
        final String username = user.getUsername();
        final String password = user.getPassword();
        final UserRole user_role = user.getUser_role();
        final long user_id = userService.create(username, password, user_role);
        final String location = String.format("/users/%d", user_id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{user_id}")
    public ResponseEntity<Void> update(@PathVariable long user_id, @RequestBody UserDTO user) {
        final String username = user.getUsername();
        final String password = user.getPassword();
        final UserRole user_role = user.getUser_role();

        try {
            userService.update(user_id, username, password, user_role);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> delete(@PathVariable long user_id) {
        userService.delete(user_id);
        return ResponseEntity.noContent().build();
    }
}
