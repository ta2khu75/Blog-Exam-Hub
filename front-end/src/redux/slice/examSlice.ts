import { createSlice, PayloadAction } from "@reduxjs/toolkit";
interface ExamListQuiz{
  id:number;
  value:QuizDetailsResponse[];
}
export interface ExamState {
   [key:number]:QuizDetailsResponse[];
}
const initialState:ExamState= {
}
export const examSlice= createSlice({
  name: "exam",
  initialState,
  reducers: {
    setExam: (
      state = initialState,
      action: PayloadAction<ExamListQuiz>
    ) => {
      state[action.payload.id]=action.payload.value;
    },
    deleteExam:(state, action:PayloadAction<number>)=>{
      delete state[action.payload];
    },
    resetExam: () => {
      return initialState;
    },
  },
});
export const { setExam, resetExam, deleteExam} = examSlice.actions;
export default examSlice.reducer;