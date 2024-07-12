package com.project.demo.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CreationAwareSoftDeletableEntity extends CreationAwareEntity{
    @Column(name="date_deleted")
    private OffsetDateTime dateDeleted;

    public boolean isDeleted(){
        return dateDeleted!=null;
    }
}
