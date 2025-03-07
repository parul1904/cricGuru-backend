package in.cricguru.repository;

import in.cricguru.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // all crud database methods
    User findByEmail(String email);
}