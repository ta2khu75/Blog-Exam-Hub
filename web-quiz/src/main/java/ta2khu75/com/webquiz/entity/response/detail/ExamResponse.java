package ta2khu75.com.webquiz.entity.response.detail;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExamResponse (Long id, String title, @JsonProperty("image_path") String imagePath, @JsonProperty("exam_type") String examType, @JsonProperty("exam_level") String examLevel){
}