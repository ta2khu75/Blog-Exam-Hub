package ta2khu75.com.webquiz.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ta2khu75.com.webquiz.entity.Account;
import ta2khu75.com.webquiz.entity.request.AccountRequest;
import ta2khu75.com.webquiz.entity.response.AccountResponse;
import ta2khu75.com.webquiz.exception.NotFoundException;
import ta2khu75.com.webquiz.exception.NotMatchesException;
import ta2khu75.com.webquiz.mapper.AccountMapper;
import ta2khu75.com.webquiz.repository.AccountRepository;
import ta2khu75.com.webquiz.service.AccountService;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository repository;
    AccountMapper mapper;
    @Override
    public AccountResponse create(AccountRequest request) {
        if(request.password().equals(request.confirmPassword())){
                Account account =mapper.toEntity(request);
                account.setEmail(account.getEmail().toLowerCase());
                return mapper.toResponse(repository.save(account));
        }
        throw new NotMatchesException("password and confirm password not matches");
    }

    @Override
    public AccountResponse update(Long id, AccountRequest request) {
        if(request.password().equals(request.confirmPassword())){
            Account account =repository.findById(id).orElseThrow(()-> new NotFoundException("Could not found account with id: "+id));
            mapper.update(request,account);
            return mapper.toResponse(repository.save(account));
        }
        throw new NotMatchesException("password and confirm password not matches");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AccountResponse read(Long id) {
        return mapper.toResponse(repository.findById(id).orElseThrow(()-> new NotFoundException("Could not found account with id: "+id)));
    }

    @Override
    public Page<AccountResponse> readPage(Pageable pageable) {
        return repository.findAll(pageable).map((account)->mapper.toResponse(account));
    }
}
