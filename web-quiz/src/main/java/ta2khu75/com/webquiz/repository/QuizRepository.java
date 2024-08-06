package ta2khu75.com.webquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ta2khu75.com.webquiz.entity.Quiz;
import java.util.List;
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByExamId(Long id);
}
