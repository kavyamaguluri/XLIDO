package com.crio.xlido.services;

import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IUserRepository;

// import java.util.Optional;
// import com.crio.xlido.entities.User;
// import com.crio.xlido.repositories.IUserRepository;

// public class UserService {
//     private final IUserRepository userRepository;

//     public UserService(IUserRepository userRepository) {
//         this.userRepository = userRepository;
//     }


//     /**
//      * Creates a new user with the specified email and password.
//      * @param email Email of the user.
//      * @param password Password of the user.
//      * @return Created User object.
//      */
//     public User createUser(String name, String password) {
//         User user = new User(name, password);
//         return userRepository.save(user);
//     }

//     /**
//      * Retrieves a user by ID.
//      * @param userId ID of the user.
//      * @return Optional containing the User object if found.
//      */
//     public Optional<User> getUser(Long userId) {
//         return userRepository.findById(userId);
//     }
// }
//     /**
//      * Deletes a user by ID.
//      * @param userId ID of the user to delete.
//      */
//     // public void deleteUser(Long userId) {
//     //     userRepository.deleteById(userId);
//     // }
// // }

public class UserService {
    
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password) {
        // Validate that the email is unique
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered: " + email);
        }

        // Create a new User
        User user = new User(email, password);
        return userRepository.save(user);
    }
}