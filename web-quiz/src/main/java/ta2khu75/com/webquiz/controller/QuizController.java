package ta2khu75.com.webquiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ta2khu75.com.webquiz.entity.request.QuizRequest;
import ta2khu75.com.webquiz.entity.response.QuizResponse;
import ta2khu75.com.webquiz.repository.QuizRepository;
import ta2khu75.com.webquiz.service.QuizSerivce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${app.api-prefix}/quiz")
public class QuizController {
    QuizSerivce serivce;
    @PostMapping
    public ResponseEntity<QuizResponse> postMethodName( @Valid @RequestBody QuizRequest request) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(serivce.create(request));
    }
    
    @GetMapping("{id}")
    public ResponseEntity<QuizResponse> getMethodName(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serivce.read(id) );
    }
   @PutMapping("/{id}")
   public ResponseEntity<QuizResponse> putMethodName(@PathVariable Long id, @RequestBody QuizRequest request) {
       return ResponseEntity.status(HttpStatus.OK).body(serivce.update(id, request));
   } 
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteMethodName(@PathVariable Long id){
    serivce.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
    
}
