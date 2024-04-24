package vn.demoRestFullAPI.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.demoRestFullAPI.dto.request.AddressDTO;
import vn.demoRestFullAPI.dto.request.UserRequestDTO;
import vn.demoRestFullAPI.dto.response.UserDetailResponse;
import vn.demoRestFullAPI.exception.ResourceNotFoundException;
import vn.demoRestFullAPI.model.Address;
import vn.demoRestFullAPI.model.User;
import vn.demoRestFullAPI.repository.UserRepository;
import vn.demoRestFullAPI.service.UserService;
import vn.demoRestFullAPI.util.UserStatus;
import vn.demoRestFullAPI.util.UserType;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j //ghi log
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public long saveUser(UserRequestDTO requestDTO) {
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
                .addresses(convertToAddress(requestDTO.getAddresses()))
                .build();
        requestDTO.getAddresses().forEach(a ->
                user.saveAddress(Address.builder()
                        .apartmentNumber(a.getApartmentNumber())
                        .floor(a.getFloor())
                        .building(a.getBuilding())
                        .streetNumber(a.getStreetNumber())
                        .street(a.getStreet())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .addressType(a.getAddressType())
                        .build()));
        userRepository.save(user);
        log.info("User has saved");
        return user.getId();
    }

    @Override
    public void deleteUser(long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
        log.info("User deleted, user id = {}", userId);
    }

    private Set<Address> convertToAddress(Set<AddressDTO> addresses) {
        Set<Address> result = new HashSet<>();
        addresses.forEach(a ->
                result.add(Address.builder()
                        .apartmentNumber(a.getApartmentNumber())
                        .floor(a.getFloor())
                        .building(a.getBuilding())
                        .streetNumber(a.getStreetNumber())
                        .street(a.getStreet())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .addressType(a.getAddressType())
                        .build())
        );
        return result;
    }
    @Override
    public void updateUser(long userId, UserRequestDTO request) {
        User user = getUserById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        if (!request.getEmail().equals(user.getEmail())) {
            // check email from database if not exist then allow update email otherwise throw exception
            user.setEmail(request.getEmail());
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setStatus(request.getStatus());
        user.setType(UserType.valueOf(request.getType().toUpperCase()));
        user.setAddresses(convertToAddress(request.getAddresses()));
        userRepository.save(user);

        log.info("User updated successfully");

    }

    @Override
    public void changeStatus(long userId, UserStatus status) {
        User user = getUserById(userId);
        user.setStatus(status);
        userRepository.save(user);
        log.info("status changed");
    }

    @Override
    public UserDetailResponse getUser(long userId) {
        User user = getUserById(userId);
        return UserDetailResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    @Override
    public List<UserDetailResponse> getAllUsers(int pageNo, int pageSize) {
        return null;
    }

    private User getUserById(long userID) {
        return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
