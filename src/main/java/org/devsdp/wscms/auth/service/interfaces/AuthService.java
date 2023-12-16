package org.devsdp.wscms.auth.service.interfaces;

import org.devsdp.wscms.auth.dto.ConfirmEmailDto;
import org.devsdp.wscms.auth.dto.LoginUserDto;
import org.devsdp.wscms.auth.dto.UserDto;
import org.devsdp.wscms.auth.dto.responses.LoginResponse;
import org.devsdp.wscms.auth.dto.responses.SignupResponse;
import org.devsdp.wscms.auth.model.User;

import java.io.IOException;
import java.util.List;
public interface AuthService {
    LoginResponse signIn(LoginUserDto loginUserDto);
    SignupResponse signUp(UserDto userDto) throws IOException;
    Object verifyEmail(ConfirmEmailDto confirmEmailDto) throws IOException;
    Object forgotPassword(String email) throws IOException;
    Object checkPasswordVerifyCode(String email, String code);
    Object resetPassword(String email, String code, String newPassword) throws IOException;
}
