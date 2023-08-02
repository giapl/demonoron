package com.example.demonorn.service;

import com.example.demonorn.dao.enity.User;
import com.example.demonorn.dao.request.UserRequest;

public interface IUserService {
  User createUser(UserRequest user);
}
