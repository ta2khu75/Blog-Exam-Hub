import { createSlice, PayloadAction } from "@reduxjs/toolkit";
export interface QuizState {
    value: QuizRequest[]
}
const initialState: QuizState = {
    value: []
}
// luu quizzes trong truong hop tao quiz
export const quizSlice = createSlice({
    name: "quiz",
    initialState,
    reducers: {
        setQuizzes: (
            state = initialState,
            action: PayloadAction<QuizRequest[]>
        ) => {
            state.value = action.payload
        },
        deleteQuiz: (state, action: PayloadAction<number>) => {
            state.value.splice(action.payload, 1);
        },
        addQuiz: (state, action: PayloadAction<QuizRequest>) => {
            state.value.push(action.payload);
        },
        updateQuiz: (state, action: PayloadAction<{ indexQuiz: number, quiz: QuizRequest }>) => {
            state.value = state.value.map((quiz, index) => {
                if (index === action.payload.indexQuiz) {
                    return action.payload.quiz;
                }
                return quiz;
            })
        },
        resetQuiz: () => {
            return initialState;
        },
    },
});
export const { setQuizzes, deleteQuiz, updateQuiz, addQuiz, resetQuiz } = quizSlice.actions;
export default quizSlice.reducer;