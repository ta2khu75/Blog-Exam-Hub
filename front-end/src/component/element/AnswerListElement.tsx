import { Checkbox, Radio, Space } from "antd";
import QuizResponse from "../../response/QuizResponse";
import AnswerResponse from "../../response/AnswerResponse";
import { useAppSelector } from "../../redux/hooks";
type Props = {
  examId: number;
  quizResponse: QuizResponse;
  answerResponseList: AnswerResponse[];
  handleAnswerClick: (quizResponse: QuizResponse, answers: number[]) => void;
};
const AnswerListElement = ({
  examId,
  quizResponse,
  answerResponseList,
  handleAnswerClick,
}: Props) => {
  const value = useAppSelector(
    (state) => state.userExams?.[examId]?.[quizResponse.id]
  );
  return (
    <>
      {quizResponse.quiz_type == "SINGLE_CHOICE" && (
        <Radio.Group
          value={value?.[0] ?? -1}
          onChange={(e) => handleAnswerClick(quizResponse, [e.target.value])}
        >
          <Space direction="vertical">
            {answerResponseList.map((answer) => (
              <Radio
                key={`answer-radio-${answer.id}`}
                className="d-block"
                value={answer.id}
              >
                {answer.answer}
              </Radio>
            ))}
          </Space>
        </Radio.Group>
      )}
      {quizResponse.quiz_type == "MULTIPLE_CHOICE" && (
        <Checkbox.Group
          value={value}
          onChange={(e) => handleAnswerClick(quizResponse, e)}
        >
          <Space direction="vertical">
            {answerResponseList.map((answer) => (
              <Checkbox key={`answer-checkbox-${answer.id}`} value={answer.id}>
                {answer.answer}
              </Checkbox>
            ))}
          </Space>
        </Checkbox.Group>
      )}
    </>
  );
};

export default AnswerListElement;
