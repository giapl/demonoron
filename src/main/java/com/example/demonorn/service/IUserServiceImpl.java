package com.example.demonorn.service;

import com.example.demonorn.dao.enity.Role;
import com.example.demonorn.dao.enity.User;
import com.example.demonorn.dao.request.UserRequest;
import com.example.demonorn.repository.IRoleRepository;
import com.example.demonorn.repository.IUserRepository;
import java.util.Collections;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl implements IUserService {

  private IUserRepository iUserRepository;
  private IRoleRepository iRoleRepository;

  private PasswordEncoder passwordEncoder;

  @Autowired
  public IUserServiceImpl(IUserRepository iUserRepository, IRoleRepository iRoleRepository,
      PasswordEncoder passwordEncoder) {
    this.iUserRepository = iUserRepository;
    this.iRoleRepository = iRoleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @SneakyThrows
  @Override
  public User createUser(UserRequest user) {
    User user1 = new User();
    if (iUserRepository.existsByUsernameAndEmail(user.getUsername(), user.getEmail())) {
      throw new Exception("email and username da ton tai vui long thu lai");
    }
    user1.setEmail(user.getEmail());
    user1.setUsername(user.getUsername());
    user1.setPassword(passwordEncoder.encode(user.getPassword()));
    Role role = iRoleRepository.findByName("USER");
    user1.setRoles(Collections.singletonList(role));
    return iUserRepository.save(user1);
  }
}
