package kr.bbmm.mywordbook.be.repository;

import kr.bbmm.mywordbook.be.domain.Expression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressionRepository extends JpaRepository<Expression, Long> {
}
