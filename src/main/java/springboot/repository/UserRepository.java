package springboot.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springboot.model.User;
import springboot.model.User.UserStatus;

/**
 * Repository interface for User entity.
 * Extends JpaRepository to inherit CRUD operations.
 * Custom queries demonstrate different ways to retrieve data.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username - derived query method
    Optional<User> findByUsername(String username);

    // Find user by email - derived query method
    Optional<User> findByEmail(String email);

    // Check if user exists by username or email - derived query method
    boolean existsByUsernameOrEmail(String username, String email);

    // Find users by status - derived query method
    List<User> findByStatus(UserStatus status);

    // Custom query using JPQL
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> searchUsers(@Param("keyword") String keyword);

    // Native SQL query example
    @Query(value = "SELECT * FROM users WHERE created_at >= CURRENT_DATE - INTERVAL '30 days'",
            nativeQuery = true)
    List<User> findRecentUsers();

    // Custom query counting users by status
    @Query("SELECT COUNT(u) FROM User u WHERE u.status = :status")
    long countByStatus(@Param("status") UserStatus status);
}
