package ta2khu75.com.webquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ta2khu75.com.webquiz.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
