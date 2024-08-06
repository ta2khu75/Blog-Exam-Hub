package ta2khu75.com.webquiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ta2khu75.com.webquiz.entity.request.AnswerRequest;
import ta2khu75.com.webquiz.entity.response.AnswerResponse;
import ta2khu75.com.webquiz.service.AnswerService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/answer")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerController {
    AnswerService service;
   @PostMapping
   public ResponseEntity<AnswerResponse> postMethodName(@Valid @RequestBody AnswerRequest request) {
       return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
   }
   @DeleteMapping("{id}")
   public ResponseEntity<Void> deleteMethodName(@PathVariable("id") Long id){
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
   @GetMapping("quiz/{id}")
   public ResponseEntity<List<AnswerResponse>> getMethodNam(@PathVariable("id") Long id) {
       return ResponseEntity.ok(service.readAllByQuizId(id));
   }
   
   @GetMapping
   public ResponseEntity<Page<AnswerResponse>> getMethodName(Pageable pageable) {
       return ResponseEntity.status(HttpStatus.OK).body(service.readPage(pageable));
   }
   @PutMapping("{id}")
   public ResponseEntity<AnswerResponse> putMethodName(@PathVariable("id") Long id, @Valid @RequestBody AnswerRequest request) {
       return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
   }
   @GetMapping("{id}")
   public ResponseEntity<AnswerResponse> getMethodName(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.read(id));
   }
}
