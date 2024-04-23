package vn.demoRestFullAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.demoRestFullAPI.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
