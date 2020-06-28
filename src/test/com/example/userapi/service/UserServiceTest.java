package com.example.userapi.service;

import com.example.userapi.client.ApiRestClient;
import com.example.userapi.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    ApiRestClient apiRestClient;

    @InjectMocks
    UserService userService;

    @Test
    public void getAllUsersTest() {
        Mockito.when(apiRestClient.getAllUsers()).thenReturn(Arrays.asList(new User()));
        Mockito.when(apiRestClient.getAllUsersBasedOnCity(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<User> users = userService.getAllUsers("London", 0, 0, 0);
        Assert.assertTrue(users.size() == 1);
    }
}
