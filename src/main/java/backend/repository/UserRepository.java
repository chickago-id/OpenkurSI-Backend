package backend.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import backend.model.User;

public interface UserRepository {
    User save(@NotNull User user);
    User update(@NotNull User user);
    List<User> findAll();
    List<User> getAll();
    User findById(@NotNull Long id);
    Optional<User> findByUsername(@NotNull String username);
    Optional<User> findByEmail(@NotNull String email);
    void deleteById(@NotNull Long id);
}