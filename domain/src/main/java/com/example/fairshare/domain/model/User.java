package com.example.fairshare.domain.model;

public class User {
    private final String id;
    private final String email;
    private final String name;
    private final String profilePictureUrl;

    public User(String id, String email, String name, String profilePictureUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}
