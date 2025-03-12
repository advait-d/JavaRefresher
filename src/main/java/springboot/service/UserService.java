package springboot.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.dto.UserCreationRequest;
import springboot.exception.ResourceAlreadyExistsException;
import springboot.exception.ResourceNotFoundException;
import springboot.model.User;
import springboot.model.User.UserStatus;
import springboot.repository.UserRepository;

/**
 * Service layer for User entity handling business logic.
 * Contains transactional methods and interacts with the repository.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create new user with validation for existing username or email.
     */
    @Transactional
    public User createUser(UserCreationRequest userRequest) {
        // Check if username or email already exists
        if (userRepository.existsByUsernameOrEmail(userRequest.getUsername(), userRequest.getEmail())) {
            throw new ResourceAlreadyExistsException(
                    "User already exists with username: " + userRequest.getUsername() +
                            " or email: " + userRequest.getEmail());
        }

        // Create new user from request
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        // In a real application, you would hash the password here
        user.setPassword(userRequest.getPassword());

        return userRepository.save(user);
    }

    /**
     * Get user by ID with not found exception.
     */
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    /**
     * Get user by username with not found exception.
     */
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    /**
     * Get all users with pagination and sorting.
     */
    @Transactional(readOnly = true)
    public Page<User> getAllUsers(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }

    /**
     * Update user details with validation.
     */
    @Transactional
    public User updateUser(Long id, UserCreationRequest userRequest) {
        User user = getUserById(id);

        // Check if new username is already taken by another user
        userRepository.findByUsername(userRequest.getUsername())
                .ifPresent(existingUser -> {
                    if (!existingUser.getId().equals(id)) {
                        throw new ResourceAlreadyExistsException(
                                "Username already taken: " + userRequest.getUsername());
                    }
                });

        // Check if new email is already taken by another user
        userRepository.findByEmail(userRequest.getEmail())
                .ifPresent(existingUser -> {
                    if (!existingUser.getId().equals(id)) {
                        throw new ResourceAlreadyExistsException(
                                "Email already taken: " + userRequest.getEmail());
                    }
                });

        // Update user fields
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());

        // Only update password if provided
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            // In a real application, you would hash the password here
            user.setPassword(userRequest.getPassword());
        }

        return userRepository.save(user);
    }

    /**
     * Update user status.
     */
    @Transactional
    public User updateUserStatus(Long id, UserStatus status) {
        User user = getUserById(id);
        user.setStatus(status);
        return userRepository.save(user);
    }

    /**
     * Delete user by ID.
     */
    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    /**
     * Search users by keyword.
     */
    @Transactional(readOnly = true)
    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }

    /**
     * Get recently active users.
     */
    @Transactional(readOnly = true)
    public List<User> getRecentUsers() {
        return userRepository.findRecentUsers();
    }

    /**
     * Get users by status.
     */
    @Transactional(readOnly = true)
    public List<User> getUsersByStatus(UserStatus status) {
        return userRepository.findByStatus(status);
    }
}