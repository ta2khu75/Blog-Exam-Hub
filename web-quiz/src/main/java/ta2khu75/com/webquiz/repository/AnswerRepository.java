package ta2khu75.com.webquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ta2khu75.com.webquiz.entity.Answer;
import java.util.List;
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuizId(Long id);
}
