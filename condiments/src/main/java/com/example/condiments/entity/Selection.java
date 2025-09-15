package com.example.condiments.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Selection {
    private String id = UUID.randomUUID().toString();
    private List<String> condiments;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Selection() {
    }

    public Selection(List<String> condiments) {
        this.condiments = condiments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCondiments() {
        return condiments;
    }

    public void setCondiments(List<String> condiments) {
        this.condiments = condiments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
