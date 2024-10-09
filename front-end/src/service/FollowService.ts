import instance from "../util/apiInstance";
const basePath = "follow"
export class FollowService {
    static follow(followingId: string): Promise<ApiResponse<FollowResponse>> {
        return instance.get(`${basePath}/account/${followingId}`);
    }
    static unFollow(followingId: string): Promise<ApiResponse<FollowResponse>> {
        return instance.delete(`${basePath}/account/${followingId}/delete`);
    }
    static checkFollowing(followingId: string): Promise<ApiResponse<FollowResponse>> {
        return instance.get(`${basePath}/account/${followingId}/check`);
    }
    static readPageFollower(followingId: string, page = 1, size = 10): Promise<ApiResponse<PageResponse<FollowResponse>>> {
        return instance.get(`${basePath}/follower/${followingId}`, { params: { page, size } });
    }
}