package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@MappedSuperclass
public abstract class EntityBase implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private ModelStatus modelStatus = ModelStatus.ACTIVE;

    @Column
    private LocalDateTime createTime = LocalDateTime.now();

    @Column
    private LocalDateTime lastUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelStatus getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(ModelStatus modelStatus) {
        this.modelStatus = modelStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityBase that = (EntityBase) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
