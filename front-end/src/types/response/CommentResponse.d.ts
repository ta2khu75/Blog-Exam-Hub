
interface CommentResponse extends CommentBase{
    id: number;
    file_path:string;
    author: AccountResponse;
    created_at:string;
    updated_at: string;
}