package com.tlc.blog.data.entity;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity implements Comparable<BaseTimeEntity> {

    @Comment("생성일자")
    @CreatedDate
    private LocalDateTime created;

    @Comment("수정일자")
    @LastModifiedDate
    private LocalDateTime modified;

    @Override
    public int compareTo(BaseTimeEntity o) {
        return this.created.compareTo(o.created);
    }

    public void setCreated(LocalDateTime date) {
        this.created = date;
    }
}
