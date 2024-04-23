package vn.demoRestFullAPI.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.demoRestFullAPI.dto.request.UserRequestDTO;
import vn.demoRestFullAPI.dto.response.UserDetailResponse;
import vn.demoRestFullAPI.exception.ResourceNotFoundException;
import vn.demoRestFullAPI.model.User;
import vn.demoRestFullAPI.repository.UserRepository;
import vn.demoRestFullAPI.service.UserService;
import vn.demoRestFullAPI.util.UserType;

import java.sql.Date;
import java.util.List;

@Service
@Slf4j //ghi log
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public long addUser(UserRequestDTO requestDTO) {
        User user = User.builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .dateOfBirth((Date) requestDTO.getDateOfBirth())
                .gender(requestDTO.getGender())
                .phone(requestDTO.getPhone())
                .email(requestDTO.getEmail())
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .status(requestDTO.getStatus())
                .type(UserType.valueOf(requestDTO.getType().toUpperCase()))
                .build();
        return 0;
    }

    @Override
    public void updateUser(long userId, UserRequestDTO request) {

    }

    @Override
    public void changeStatus(long userId, UserRequestDTO request) {

    }

    @Override
    public UserDetailResponse getUser(long userId) {
        return null;
    }

    @Override
    public List<UserDetailResponse> getAllUsers(int pageNo, int pageSize) {
        return null;
    }
}
