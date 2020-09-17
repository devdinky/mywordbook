package kr.bbmm.mywordbook.be.repository;

import kr.bbmm.mywordbook.be.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
}
