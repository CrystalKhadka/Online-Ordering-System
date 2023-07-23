package com.system.online_ordering_system.controller;


import com.system.online_ordering_system.dto.OtpDto;
import com.system.online_ordering_system.dto.UserDto;
import com.system.online_ordering_system.entity.User;
import com.system.online_ordering_system.service.UserHistoryService;
import com.system.online_ordering_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserHistoryService userHistoryService;
    @GetMapping("/register")
    public String register(){
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDto userDto) throws IOException {
        userService.register(userDto);
        return "redirect:/user/sendEmail/"+userDto.getEmail();
    }

    @GetMapping("/login")
    public String login(){

        userHistoryService.generateAllUserHistory();
        return "user/login";
    }

    @GetMapping("/sendEmail/{email}")
    public String sendRegistrationEmail(@PathVariable("email") String email,Model model) throws IOException  {
        this.userService.sendEmail(email);
        model.addAttribute("email",email);
        return "user/verifyOtp";
    }

    @PostMapping("/delete")
    public String deleteUser(){
        User user = userService.getActiveUser().get();
        userService.deleteUser(user.getId());
        return "redirect:/user/login";
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication){
            SecurityContextHolder.clearContext();
        return "redirect:/user/login";
    }



    @PostMapping("/verifyOtp")
    public String verifyOtp(@Valid OtpDto otpDto) throws IOException {
        userService.verifyOtp(otpDto);
        return "redirect:/user/login";
    }

    @GetMapping("/list")
    public String listUsers(Model model) throws IOException {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users.stream().map(user -> User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .imageBase64(getImageBase64(user.getImage()))
                .loginTime(user.getLoginTime())
                .build()
        ));
        return "user/userList";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/user_images/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }






}
