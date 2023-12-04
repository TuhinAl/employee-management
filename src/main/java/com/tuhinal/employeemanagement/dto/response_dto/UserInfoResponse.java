package com.tuhinal.employeemanagement.dto.response_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserInfoResponse {
    
    private String id;
    private String username;
    private String email;
    private Set<String> roles;
    
    public UserInfoResponse(String id, String username, String email, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
