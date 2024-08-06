package ta2khu75.com.webquiz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ta2khu75.com.webquiz.entity.request.AccountRequest;
import ta2khu75.com.webquiz.entity.response.AccountResponse;
import ta2khu75.com.webquiz.entity.response.PageResponse;

public interface AccountService {
    AccountResponse create(AccountRequest request);
    AccountResponse update(Long id,AccountRequest request);
    void delete(Long id);
    AccountResponse read(Long id);
    PageResponse<AccountResponse> readPage(Pageable pageable);

}
