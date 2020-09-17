package kr.bbmm.mywordbook.be.repository;

import kr.bbmm.mywordbook.be.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
