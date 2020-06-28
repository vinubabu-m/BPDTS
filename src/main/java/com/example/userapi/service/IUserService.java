package com.example.userapi.service;

import com.example.userapi.model.User;

import java.util.List;

public interface IUserService {

    public List<User> getAllUsers(String city, double lat, double lon, int distance);
}
