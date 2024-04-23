package vn.demoRestFullAPI.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class UserDetailResponse implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
