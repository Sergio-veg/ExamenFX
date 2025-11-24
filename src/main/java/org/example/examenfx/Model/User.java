package org.example.examenfx.Model;

import java.time.LocalDateTime;

public class User {
    private String email;
    private String platform;
    private Boolean isAdmin;
    private String version;
    private LocalDateTime creationDate;

    public User() {}

    public User(String email, String platform, Boolean isAdmin, String version, LocalDateTime creationDate) {
        this.email = email;
        this.platform = platform;
        this.isAdmin = isAdmin;
        this.version = version;
        this.creationDate = creationDate;
    }

    // Getters y setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public Boolean getIsAdmin() { return isAdmin; }
    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
}