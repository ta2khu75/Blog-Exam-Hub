package ta2khu75.com.webquiz.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;
    @Column(unique = true)
   String email;
    @Column(nullable = false)
   String password;
   @Enumerated(EnumType.STRING)
   Role role=Role.USER;
   @OneToMany(mappedBy = "account")
   List<ExamHistory> examHistories;
}
