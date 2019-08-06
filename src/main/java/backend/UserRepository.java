package backend;

import backend.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(@NotNull User user);
    User update(@NotNull User user);
    List<User> findAll();
    User findById(@NotNull Long id);
    Optional<User> findByUsername(@NotNull String username);
    Optional<User> findByEmail(@NotNull String email);
    void deleteById(@NotNull Long id);
}