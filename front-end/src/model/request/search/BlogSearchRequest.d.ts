interface BlogSearchRequest extends SearchRequestBase {
    blogTagNames?: string[],
    minView?: number,
    maxView?: number
}