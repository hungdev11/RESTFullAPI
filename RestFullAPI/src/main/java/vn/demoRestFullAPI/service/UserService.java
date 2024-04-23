package vn.demoRestFullAPI.service;

import vn.demoRestFullAPI.dto.request.UserRequestDTO;
import vn.demoRestFullAPI.dto.response.UserDetailResponse;

import java.util.List;

public interface UserService {
    long addUser(UserRequestDTO requestDTO);
    void updateUser(long userId, UserRequestDTO request);
    void changeStatus(long userId, UserRequestDTO request);
    UserDetailResponse getUser(long userId);
    List<UserDetailResponse> getAllUsers(int pageNo, int pageSize);

}
