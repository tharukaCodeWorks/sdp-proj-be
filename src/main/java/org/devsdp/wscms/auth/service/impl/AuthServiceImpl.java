package org.devsdp.wscms.auth.service.impl;

import org.devsdp.wscms.auth.dto.AuthTokenDto;
import org.devsdp.wscms.auth.dto.ConfirmEmailDto;
import org.devsdp.wscms.auth.dto.LoginUserDto;
import org.devsdp.wscms.auth.dto.UserDto;
import org.devsdp.wscms.auth.dto.responses.LoginResponse;
import org.devsdp.wscms.auth.dto.responses.SignupResponse;
import org.devsdp.wscms.auth.exceptions.EmailAlreadyException;
import org.devsdp.wscms.auth.exceptions.EmailNotVerifiedException;
import org.devsdp.wscms.auth.exceptions.InvalidCodeException;
import org.devsdp.wscms.auth.exceptions.InvalidCredentialsException;
import org.devsdp.wscms.auth.model.Role;
import org.devsdp.wscms.auth.model.User;
import org.devsdp.wscms.auth.service.interfaces.AuthService;
import org.devsdp.wscms.auth.service.interfaces.UserService;
import org.devsdp.wscms.complaint.dao.PublicUserDao;
import org.devsdp.wscms.complaint.model.PublicUser;
import org.devsdp.wscms.sys.util.EmailService;
import org.devsdp.wscms.sys.util.HtmlProcessService;
import org.devsdp.wscms.sys.util.RandomValueUtil;
import org.devsdp.wscms.sys.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.IOException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private HtmlProcessService htmlProcessService;

    @Autowired
    private PublicUserDao publicUserDao;

    @Override
    public LoginResponse signIn(LoginUserDto loginUserDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserDto.getEmail(),
                            loginUserDto.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new InvalidCredentialsException("User email or password incorrect");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        User user =  userService.findOne(loginUserDto.getEmail());
        if(user!=null) {
            if(user.getIsEmailVerified().equals("FALSE")) {
                throw new EmailNotVerifiedException("Your email is not verified yet");
            }
        }

        assert user != null;
        return LoginResponse
                .builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .isEmailVerified(user.getIsEmailVerified())
                .token(new AuthTokenDto(token).getToken())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .build();
    }

    @Override
    public SignupResponse signUp(UserDto userDto) {
        if( !userService.isUserExists(userDto.getEmail())){
           try {
               Role userRole = Role
                       .builder()
                       .id(2)
                       .build();
               User user = new User();
               String resetCode = RandomValueUtil.getRandomNumberString(999999);
               user.setFirstName(userDto.getFirstName());
               user.setLastName(userDto.getLastName());
               user.setEmail(userDto.getEmail());
               user.setPassword(userDto.getPassword());
               user.setEmailVerifyCode(resetCode);
               user.setIsEmailVerified("FALSE");
               user.setRole(userRole);
               user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
               Context emailContext = new Context();
               emailContext.setVariable("user", user);
               emailContext.setVariable("code", resetCode);
               emailService.sendHtmlEmailSmtp("Email Verification ", this.htmlProcessService.processHtml(emailContext, "email/email-verification"), user.getEmail());
               user = userService.save(user);
               userService.setUserRole(2, user);
               SignupResponse signupResponse = SignupResponse
                       .builder()
                       .userId(user.getId())
                       .lastName(user.getLastName())
                       .firstName(user.getFirstName())
                       .email(user.getEmail())
                       .build();
               user.setId(signupResponse.getUserId());
               PublicUser publicUser = PublicUser.builder().userId(user).build();
               publicUserDao.save(publicUser);
               return signupResponse;

           } catch (Exception e){
               e.printStackTrace();
               throw new RuntimeException("Something went wrong");
           }


        } else {
            throw new EmailAlreadyException("Email address already using");
        }
    }

    @Override
    public Object verifyEmail(ConfirmEmailDto confirmEmailDto) throws IOException {
        User authUser = userService.findOne(confirmEmailDto.getEmail());
        if( authUser != null){
            if(confirmEmailDto.getVerifyCode().equals(authUser.getEmailVerifyCode())) {
                authUser.setIsEmailVerified("TRUE");
                authUser.setEmailVerifyCode(null);
                authUser = userService.save(authUser);
                emailService.sendHtmlEmailSmtp("Email Verification", "Welcome to the Spring Email", authUser.getEmail());
                return SignupResponse
                        .builder()
                        .userId(authUser.getId())
                        .lastName(authUser.getLastName())
                        .firstName(authUser.getFirstName())
                        .email(authUser.getEmail())
                        .build();
            } else {
                throw new InvalidCodeException("You provided verify code is wrong");
            }
        } else {
           throw new InvalidCredentialsException("Entered email address is not valid!");
        }
    }

    @Override
    public Object forgotPassword(String email) throws IOException {
        if(userService.isUserExists(email)){
            User authUser = userService.findOne(email);
            String resetCode = RandomValueUtil.getRandomNumberString(999999);
            authUser.setPasswordResetCode(resetCode);
            userService.save(authUser);

            Context emailContext = new Context();
            emailContext.setVariable("user", authUser);
            emailContext.setVariable("code", resetCode);

            emailService.sendHtmlEmailSmtp("Email Verification", this.htmlProcessService.processHtml(emailContext, "email/email-verification"), authUser.getEmail());

            return authUser;
        } else {
            throw new InvalidCredentialsException("User not found");
        }
    }

    @Override
    public Object checkPasswordVerifyCode(String email, String code) {
        if(userService.isUserExists(email)){
            User authUser = userService.findOne(email);
            authUser = userService.save(authUser);
            if(code.equals(authUser.getPasswordResetCode())) {
                return authUser;
            } else {
                throw new InvalidCodeException("Wrong reset code");
            }
        } else {
            throw new InvalidCredentialsException("User not found");
        }
    }

    @Override
    public Object resetPassword(String email, String code, String newPassword) throws IOException {
        if(userService.isUserExists(email)){
            User authUser = userService.findOne(email);
            if(code.equals(authUser.getPasswordResetCode())) {
                authUser.setPassword(bcryptEncoder.encode(newPassword));
                authUser.setPasswordResetCode(null);
                authUser = userService.save(authUser);
                emailService.sendHtmlEmailSmtp("Password reset successfully", "Your password reset successfully!", email);
                return authUser;
            } else {
                throw new InvalidCodeException("Wrong reset code");
            }
        } else {
            throw new InvalidCredentialsException("User not found");
        }
    }
}
