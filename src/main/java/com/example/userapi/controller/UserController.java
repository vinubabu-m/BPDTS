package com.example.userapi.controller;

import com.example.userapi.common.Constants;
import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Collection<User>> getAll(@RequestParam(name = "city", defaultValue = Constants.LONDON) String city,
                                                 @RequestParam(name = "lat", defaultValue = Constants.LONDON_LAT) double lat,
                                                 @RequestParam(name = "lon", defaultValue = Constants.LONDON_LON) double lon,
                                                 @RequestParam(name = "distance", defaultValue = Constants.DISTANCE) int distance) {

    List<User> users = userService.getAllUsers(city, lat, lon, distance);
    return ResponseEntity.ok(users);
  }

}
