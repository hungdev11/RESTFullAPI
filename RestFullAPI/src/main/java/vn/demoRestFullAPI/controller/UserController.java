package vn.demoRestFullAPI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.demoRestFullAPI.configuration.Translator;
import vn.demoRestFullAPI.dto.request.SampleDTO;
import vn.demoRestFullAPI.dto.request.UserRequestDTO;
import vn.demoRestFullAPI.dto.response.ResponseData;
import vn.demoRestFullAPI.dto.response.ResponseError;
import vn.demoRestFullAPI.dto.response.ResponseFailure;
import vn.demoRestFullAPI.dto.response.ResponseSuccess;
import vn.demoRestFullAPI.service.UserService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/user")
@Slf4j
@Tag(name = "User Controller")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Add User", description = "API create new user")
    @PostMapping("/add")
    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO userDTO) {
        try {
            userService.addUser(userDTO);
            return new ResponseData<Integer>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), 1);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Save user fail " + e.getMessage());
        }
        //return new ResponseFailure(HttpStatus.BAD_REQUEST.value(), "Can not create user");
        //return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Can not create user");
    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable @Min(1)int userId, @Valid @RequestBody UserRequestDTO userDTO) {
        System.out.println("Request update userId = " + userId);
        log.info("Request add user = {} {}", userDTO.getFirstName(), userDTO.getLastName());
//        SampleDTO dto = SampleDTO.builder()
//                .id(1)
//                .name("Hung")
//                .build();
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully");
    }

    @PatchMapping("/{userId}")
    public ResponseData<?> changeStatus(@PathVariable @Min(1) int userId, @Min(1) @RequestParam int status) {
        System.out.println("Request change user status, userId = " + userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User status changed");
    }

    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@Min(1) @PathVariable int userId) {
        System.out.println("Request delete userId = " + userId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted");
    }

    @GetMapping("/{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable int userId) {
        System.out.println("Request get user detail by userId = " + userId);
        return new ResponseData<>(HttpStatus.OK.value(), "User",new UserRequestDTO("a","b","123","ab@gmail.com"));
    }
    @GetMapping("/list")
    public ResponseData<List<UserRequestDTO>> getAllUsers() {
        System.out.println("Request get user list");
        return new ResponseData<>(HttpStatus.OK.value(), "LIST",
            List.of(
                    new UserRequestDTO("a","b","123","ab@gmail.com"),
                    new UserRequestDTO("a","b","123","ab@gmail.com"))
            );

    }
}
