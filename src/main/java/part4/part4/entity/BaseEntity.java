package part4.part4.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class BaseEntity {



    @Column(name="regdate",updatable = false)
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name="moddate")
    @LastModifiedDate
    private LocalDateTime modDate;
}
