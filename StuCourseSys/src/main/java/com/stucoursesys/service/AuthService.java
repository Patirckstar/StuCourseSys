package com.stucoursesys.service;

import com.stucoursesys.common.ErrorCode;
import com.stucoursesys.dto.LoginRequestDTO;
import com.stucoursesys.dto.LoginResult;
import com.stucoursesys.dto.UserVO;
import com.stucoursesys.entity.SysUser;
import com.stucoursesys.exception.BusinessException;
import com.stucoursesys.exception.UnauthorizedException;
import com.stucoursesys.mapper.UserMapper;
import com.stucoursesys.security.CurrentUser;
import com.stucoursesys.security.JwtUtil;
import com.stucoursesys.security.UserContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public AuthService(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResult login(LoginRequestDTO request) {
        SysUser user = userMapper.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BusinessException(ErrorCode.LOGIN_FAILED, HttpStatus.UNAUTHORIZED, ErrorCode.MSG_LOGIN_FAILED);
        }
        List<String> roles = userMapper.findRoleCodes(user.getId());
        String token = jwtUtil.createToken(user, Set.copyOf(roles));
        UserVO userVO = new UserVO(user.getId(), user.getUsername(), user.getDisplayName(), roles);
        return new LoginResult(token, "Bearer", jwtUtil.getExpireSeconds(), userVO);
    }

    public UserVO currentUser() {
        CurrentUser me = UserContext.get();
        if (me == null) {
            throw new UnauthorizedException(ErrorCode.MSG_UNAUTHENTICATED);
        }
        SysUser user = userMapper.findById(me.getId());
        if (user == null) {
            throw new UnauthorizedException(ErrorCode.MSG_UNAUTHENTICATED);
        }
        List<String> roles = userMapper.findRoleCodes(user.getId());
        return new UserVO(user.getId(), user.getUsername(), user.getDisplayName(), roles);
    }
}
