interface BlogResponse extends BlogBase {
    id: string;
    view_count: number;
    image_path: string;
    created_at: string;
    last_modified_at: string;
    author: AccountResponse;
}