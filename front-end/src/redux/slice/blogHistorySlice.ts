import { createSlice, PayloadAction } from "@reduxjs/toolkit";
export interface BlogHistoryState {
    [key: string]: BlogDetailsResponse;
}
const initialState: BlogHistoryState = {
}
// luu danh sach image blog
export const blogHistorySlice = createSlice({
    name: "blogHistory",
    initialState,
    reducers: {
        setBlogHistory: (
            state = initialState,
            action: PayloadAction<{ blogId: string, blog: BlogDetailsResponse }>
        ) => {
            state[action.payload.blogId] = action.payload.blog;
        },
        deleteBlogHistory: (state, action: PayloadAction<string>) => {
            delete state[action.payload];
        },
        resetBlogHistory: () => {
            return initialState;
        },
    },
});
export const { setBlogHistory, deleteBlogHistory, resetBlogHistory } = blogHistorySlice.actions;
export default blogHistorySlice.reducer;