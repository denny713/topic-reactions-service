package com.reaction.topic.model.entity.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Column(name = "created_by", nullable = false, length = 35)
    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    protected LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.setCreatedBy("System");
        this.setCreatedDate(LocalDateTime.now());
    }
}
