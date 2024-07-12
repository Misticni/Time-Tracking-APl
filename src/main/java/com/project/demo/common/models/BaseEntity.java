package com.project.demo.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;

        if(getClass() != o.getClass()){

            if(getClass().isInstance(o)){
                return o.equals(this);
            }
            return false;
        }
        BaseEntity baseEntity = (BaseEntity) o;
        return Objects.equals(id, baseEntity.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
