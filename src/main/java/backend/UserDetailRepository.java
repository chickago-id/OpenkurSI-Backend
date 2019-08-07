package backend;

import backend.model.UserDetail;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface UserDetailRepository {
    UserDetail save(@NotNull UserDetail userDetail);
    UserDetail update(@NotNull UserDetail userDetail);
    Optional<UserDetail> findByUserId(@NotNull Long userId);
}