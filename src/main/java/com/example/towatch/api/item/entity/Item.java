package com.example.towatch.api.item.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_id", unique = true, nullable = false)
    private Long typeId;

    @Column(name = "status_id", unique = true, nullable = false)
    private Long statusId;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    public Item () {

    }

    public Long getId() {
        return id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
