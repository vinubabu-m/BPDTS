package com.example.userapi.config;

public interface HttpClientConfigConstants {

  // Timeouts
  int CONNECTION_TIMEOUT = 30 * 1000; // 30 sec, the time for waiting until a connection is established
  int REQUEST_TIMEOUT    = 30 * 1000; // 30 sec, the time for waiting for a connection from connection pool
  int SOCKET_TIMEOUT     = 60 * 1000; // 60 sec, the time for waiting for data

}
