package com.example.AntMan.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.EntityListeners;
import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Time {

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created_time;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updated_time;
}
