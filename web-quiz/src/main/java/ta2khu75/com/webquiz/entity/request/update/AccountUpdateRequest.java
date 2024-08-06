package ta2khu75.com.webquiz.entity.request.update;

import ta2khu75.com.webquiz.entity.Role;

public record AccountUpdateRequest(String email, Role role) {
}