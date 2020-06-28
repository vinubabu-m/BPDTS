package com.example.userapi.service;

import com.example.userapi.client.ApiRestClient;
import com.example.userapi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    ApiRestClient apiRestClient;

    @Override
    public List<User> getAllUsers(String city, double lat, double lon, int distance) {
        List<User> allUsers = new ArrayList<>();
        List<User> usersInCity = new ArrayList<>();

        //Fetch All Users
        Future<List<User>> allUsersFuture = CompletableFuture.supplyAsync(() -> apiRestClient.getAllUsers());
        //Fetch users in City
        Future<List<User>> cityUsersFuture = CompletableFuture.supplyAsync(() -> apiRestClient.getAllUsersBasedOnCity(city));

        try {
            allUsers.addAll(allUsersFuture.get());
            usersInCity.addAll(cityUsersFuture.get());
        } catch (Exception e) {
            LOGGER.error("Exception Occurred : " +e);
        }

        return getAllUsersInCityAndWithinMiles(allUsers, usersInCity, lat, lon, distance);
    }

    private List<User> getAllUsersInCityAndWithinMiles(List<User> allUsers, List<User> usersInCity, double lat, double lon, int distance) {
        //Remove already fetched users in a city from AllUser List
        allUsers.removeAll(usersInCity);

        //Find users within given miles and add it to already fetched users in the city
        usersInCity.addAll(allUsers.stream().filter(user ->
                isUserWithinRadius(user, distance, lat, lon)).collect(Collectors.toList()));
        return usersInCity;
    }

    private boolean isUserWithinRadius(User user, int distance, double lat, double lon) {
        boolean isUserWithinRadius = false;

        double distanceInMiles = calculateDistance(lat, lon, user.getLatitude(), user.getLongitude());
        if (distanceInMiles <= distance) {
            isUserWithinRadius = true;
        }

        return isUserWithinRadius;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
