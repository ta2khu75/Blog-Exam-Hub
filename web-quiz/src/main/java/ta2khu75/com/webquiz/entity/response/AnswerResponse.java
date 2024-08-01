package ta2khu75.com.webquiz.entity.response;

public record AnswerResponse(Long id, String ans) {
}
// public class Answer {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     Long id;
//     @Column(nullable = false)
//     String answerString;
//     @Column(nullable = false)
//     Boolean correct;
//     @Column(nullable = false)
//     @Enumerated(EnumType.STRING)
//     AnswerType answerType;
//     @ManyToOne
//     Quiz quiz;
// }