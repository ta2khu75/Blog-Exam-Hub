package ta2khu75.com.webquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ta2khu75.com.webquiz.entity.ExamHistory;

public interface ExamHistoryRepository extends JpaRepository<ExamHistory, Long> {
}
