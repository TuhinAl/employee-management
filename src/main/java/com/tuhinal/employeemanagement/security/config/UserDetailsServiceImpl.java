package com.tuhinal.employeemanagement.security.config;

import com.tuhinal.employeemanagement.entity.EmployeeAccount;
import com.tuhinal.employeemanagement.repository.EmployeeAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final EmployeeAccountRepository employeeAccountRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeAccount employeeAccount = employeeAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
      /*  EmployeeAccount employeeAccount = employeeAccountRepository.findFirstByUsername(username);
        if(employeeAccount == null){
            throw new UsernameNotFoundException("Employee not found",null);
        }*/
    
        return UserDetailsImpl.build(employeeAccount);
/*        return new org.springframework.security.core.userdetails.User(employeeAccount.getUsername(),
                employeeAccount.getPassword(), new ArrayList<>());
    }*/
    }
}
