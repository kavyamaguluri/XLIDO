package com.crio.xlido.repositories;

// import java.util.List;
// import java.util.Optional;
// import java.util.concurrent.atomic.AtomicLong;
// import com.crio.xlido.entities.User;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// public class UserRepository implements IUserRepository {

    
//     private final Map<Long, User> storage = new HashMap<>();
//     private AtomicLong idCounter = new AtomicLong(0);

//     @Override
//     public User save(User entity) {
//         User user = new User(idCounter.incrementAndGet(),entity);
//         storage.putIfAbsent(user.getId(),user);
//         return user;
//     }

//     @Override
//     public List<User> findAll() {
//         return new ArrayList<>(storage.values());
//     }

//     @Override
//     public Optional<User> findById(Long id) {
//         return Optional.ofNullable(storage.get(id));
//     }
// }

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.User;

public class UserRepository implements IUserRepository {

    private final Map<Long, User> storage = new HashMap<>();
    private final Map<String, User> emailStorage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public User save(User entity) {
        
       // Check if the email already exists
       if (emailStorage.containsKey(entity.getEmail())) {
        throw new RuntimeException("Email already exists: " + entity.getEmail());
    }
    
    // Create a new User with a unique ID
    User user = new User(idCounter.incrementAndGet(), entity);
    storage.put(user.getId(), user);
    emailStorage.put(user.getEmail(), user);
    return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(emailStorage.get(email));
    }
}





