package main.java.com.yourorg.lms.repository;


import main.java.com.yourorg.lms.model.user.User;
import java.util.List;

/**
 * Repository abstraction for User persistence.
 *
 * SOLID:
 * - SRP: Defines persistence operations only.
 * - DIP: High-level layers depend on this interface.
 */
public interface UserRepository {

    void save(User user);

    User findByEmail(String email);

    List<User> findAll();

    void delete(String id);
}
