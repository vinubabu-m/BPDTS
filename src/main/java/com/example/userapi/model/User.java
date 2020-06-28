package com.example.userapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private long id;
  @JsonProperty("first_name")
  private String firstName;
  @JsonProperty("last_name")
  private String lastName;
  private String email;
  @JsonProperty("ip_address")
  private String ipAddress;
  private double latitude;
  private double longitude;

  @Override
  public boolean equals(Object obj) {
    User user  = (User) obj;
    return id == user.getId();
  }
}