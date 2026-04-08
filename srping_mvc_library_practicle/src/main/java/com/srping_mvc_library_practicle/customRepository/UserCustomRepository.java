package com.srping_mvc_library_practicle.customRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.srping_mvc_library_practicle.entity.Users;
import com.srping_mvc_library_practicle.enums.Role;

@Repository
public class UserCustomRepository {

    // Store user data in memory
    private Map<String, Users> userStore = new HashMap<>();

    public UserCustomRepository() {

        // Initialize users ONCE
        userStore.put("dharmender", createUser("dharmender", Role.USER));
        userStore.put("ravi", createUser("ravi", Role.SUPER_ADMIN));
        userStore.put("roshan", createUser("roshan", Role.MODERATOR));
    }
    
    public void saveUser(Users users) {
        userStore.put(users.getUserName().toLowerCase(), createUser(users));
    }
    
    public Users createUser(String username, Role role) {
        Users user = new Users();
        user.setUserName(username);
        user.setPassword("$2a$10$E6agxwiMV2wGIsgUd.OWeuQJL8ssURRFwubZOhUUsn0CBNySUkaou"); // admin123
        user.setRole(role);
        user.setAccountNonLocked(false);
        user.setFailedAttempts(0);
        return user;
    }
    
    public Users createUser(Users users) {
        Users user = new Users();
        user.setUserName(users.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(users.getPassword())); // admin123
        user.setRole(users.getRole());
        user.setAccountNonLocked(false);
        user.setFailedAttempts(0);
        return user;
    }
    

    public Optional<Users> getByUserName(String userName) {
        return Optional.ofNullable(userStore.get(userName.toLowerCase()));
    }

    public void updateUser(Users user) {
        userStore.put(user.getUserName().toLowerCase(), user);
    }
}
	

