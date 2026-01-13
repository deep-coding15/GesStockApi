package com.deep_coding15.GesStockApi.common;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public class BaseEntity {
    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /* GETTERS */
    public LocalDateTime getCreatedAt() { return this.createdAt;}
    public LocalDateTime getUpdateAt() { return this.updatedAt;}

    /* SETTERS */
    public void setCreatedAt(LocalDateTime created_at) { this.createdAt = created_at;}
    public void setUpdatedAt(LocalDateTime updated_at) { this.updatedAt = updated_at;}
}
