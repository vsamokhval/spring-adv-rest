package vss.springrest.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vss.springrest.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Slice<User> findAll(Pageable pageable);
}
