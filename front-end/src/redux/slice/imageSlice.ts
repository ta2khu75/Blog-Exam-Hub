import { createSlice, PayloadAction } from "@reduxjs/toolkit";
export interface ImageState {
    value: string[];
}
const initialState: ImageState = {
    value: []
}
// luu danh sach image blog
export const imageSlice = createSlice({
    name: "image",
    initialState,
    reducers: {
        setImages: (
            state = initialState,
            action: PayloadAction<string[]>
        ) => {
            state.value = action.payload
        },
        deleteImages: (state, action: PayloadAction<number>) => {
            state.value.splice(action.payload, 1);
        },
        addImages: (state, action: PayloadAction<string>) => {
            state.value.push(action.payload);
        },
        resetImages: () => {
            return initialState;
        },
    },
});
export const { setImages, deleteImages, addImages, resetImages } = imageSlice.actions;
export default imageSlice.reducer;