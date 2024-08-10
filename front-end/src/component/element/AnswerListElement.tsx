import { useEffect, useState } from "react";
import { Checkbox, Radio, Space } from "antd";
import QuizResponse from "../../response/QuizResponse";
import AnswerResponse from "../../response/AnswerResponse";
import AnswerService from "../../service/AnswerService";
import RandomUtil from "../../util/RandomUtil";
type Props = {
  quizResponse: QuizResponse;
  handleAnswerClick: (quizResponse: QuizResponse, answer_id: number) => void;
};
const AnswerListElement = ({ quizResponse, handleAnswerClick }: Props) => {
  const [answerResponseList, setAnswerResponseList] = useState<
    AnswerResponse[]
  >([]);
  useEffect(() => {
    fetchAnswerByQuizId();
  }, []);
  const fetchAnswerByQuizId = () => {
    AnswerService.readByQuizId(quizResponse.id).then((d) => {
      setAnswerResponseList(RandomUtil.shuffleArray(d.data));
    });
  };
  return (
    <>
      {quizResponse.quiz_type == "SINGLE_CHOICE" && (
        <Radio.Group>
          <Space direction="vertical">
            {answerResponseList.map((answer) => (
              <Radio
              key={`answer-radio-${answer.id}`}
                className="d-block"
                onChange={(e) =>
                  handleAnswerClick(quizResponse, e.target.value)
                }
                value={answer.id}
              >
                {answer.answer}
              </Radio>
            ))}
          </Space>
        </Radio.Group>
      )}
      {quizResponse.quiz_type == "MULTIPLE_CHOICE" && (
        <Space direction="vertical">
          {answerResponseList.map((answer) => (
            <Checkbox
            key={`answer-checkbox-${answer.id}`}
              value={answer.id}
              onChange={(e) => handleAnswerClick(quizResponse, e.target.value)}
            >
              {answer.answer}
            </Checkbox>
          ))}
        </Space>
      )}
    </>
  );
};

export default AnswerListElement;
