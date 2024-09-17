import { BlogBase } from "../base/BlogBase";

interface BlogRequest extends BlogBase{
    blog_tags: string[];
}