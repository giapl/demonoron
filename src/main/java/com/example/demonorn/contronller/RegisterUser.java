package com.example.demonorn.contronller;


import com.example.demonorn.dao.request.UserRequest;
import com.example.demonorn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUser {

  private IUserService iUserService;

  @Autowired
  public RegisterUser(IUserService iUserService) {
    this.iUserService = iUserService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@RequestBody UserRequest user) {
    iUserService.createUser(user);
    return ResponseEntity.ok("ban da dang ki thanh cong ");
  }
}
