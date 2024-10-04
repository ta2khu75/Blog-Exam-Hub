
interface CommentResponse extends CommentBase{
    file_path:string;
    author: AccountResponse;
    info:InfoResponse;
}