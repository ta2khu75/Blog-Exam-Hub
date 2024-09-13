import { createSlice, PayloadAction } from "@reduxjs/toolkit";
interface ExamQuizAnswer {
  examId: string;
  quizId: number;
  answerIds: number[];
}
export interface QuizListState {
  [key: number]: number[];
}
export interface ExamListState {
  [key: string]: QuizListState;
}
// luu cac dap an cua bai thi
const initialState: ExamListState = {};
export const examUserSlice = createSlice({
  name: "examUser",
  initialState,
  reducers: {
    setUserExam: (
      state = initialState,
      action: PayloadAction<ExamQuizAnswer>
    ) => {
      const { examId, quizId, answerIds } = action.payload;
      state[examId] = state[examId] ?? {};

      state[examId][quizId] = answerIds;

      if (!answerIds.length) {
        delete state[examId][quizId];
      }
    },
    deleteUserExam: (state, action: PayloadAction<string>) => {
      delete state[action.payload];
    },
    resetUserExam: () => {
      return initialState;
    },
  },
});
export const {setUserExam, deleteUserExam, resetUserExam} = examUserSlice.actions;
export default examUserSlice.reducer;
