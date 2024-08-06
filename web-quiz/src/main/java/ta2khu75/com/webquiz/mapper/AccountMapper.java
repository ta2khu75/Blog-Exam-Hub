package ta2khu75.com.webquiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import ta2khu75.com.webquiz.entity.Account;
import ta2khu75.com.webquiz.entity.request.AccountRequest;
import ta2khu75.com.webquiz.entity.response.AccountResponse;
import ta2khu75.com.webquiz.entity.response.PageResponse;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountRequest request);
    AccountResponse toResponse(Account account);
    void update(AccountRequest request, @MappingTarget Account account);
    PageResponse<AccountResponse> toPageResponse(Page<AccountResponse> response);
}
