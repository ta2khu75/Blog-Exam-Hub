package ta2khu75.com.webquiz.entity.response;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public record PageResponse<T>(@JsonProperty("total_elements") int totalElements, @JsonProperty("total_pages") int totalPages, List<T> content) {
    
}
