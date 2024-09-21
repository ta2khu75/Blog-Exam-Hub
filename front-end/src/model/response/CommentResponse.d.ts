
interface CommentResponse extends CommentBase{
    file_path:string;
    author: AccountResponse;
    create_at:string;
    update_at: string;
}