package backend.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import backend.model.UserDetail;

public interface UserDetailRepository {
    UserDetail save(@NotNull UserDetail userDetail);
    UserDetail update(@NotNull UserDetail userDetail);
    List<UserDetail> getInstructor();
    List<UserDetail> getSiswa();
    Optional<UserDetail> findByUserId(@NotNull Long userId);
}