package kr.bbmm.mywordbook.be.repository;

import kr.bbmm.mywordbook.be.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
