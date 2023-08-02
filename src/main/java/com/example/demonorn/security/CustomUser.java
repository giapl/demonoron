package com.example.demonorn.security;


import com.example.demonorn.dao.enity.User;
import com.example.demonorn.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUser implements UserDetailsService {

  private final IUserRepository iUserRepository;

  @Autowired
  public CustomUser(IUserRepository iUserRepository) {
    this.iUserRepository = iUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = iUserRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    // Tạo một đối tượng UserDetails tương ứng từ đối tượng User
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUsername())
        .password(user.getPassword()) // Mật khẩu đã được mã hóa trước khi lưu vào cơ sở dữ liệu
        .roles(user.getRoles().toArray(new String[0]))
        .build();
  }
}
