package vn.demoRestFullAPI.service;

import vn.demoRestFullAPI.dto.request.UserRequestDTO;
import vn.demoRestFullAPI.dto.response.UserDetailResponse;
import vn.demoRestFullAPI.util.UserStatus;

import java.util.List;

public interface UserService {
    long saveUser(UserRequestDTO request);
    void deleteUser(long userId);
    void updateUser(long userId, UserRequestDTO request);
    void changeStatus(long userId, UserStatus status);
    UserDetailResponse getUser(long userId);
    List<UserDetailResponse> getAllUsers(int pageNo, int pageSize);

}
