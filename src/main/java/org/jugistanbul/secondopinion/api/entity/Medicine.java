package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Entity
@Table(name = "medicine")
public class Medicine extends EntityBase {

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationPeriod period;

    @Column
    private Integer frequency;

    @Column
    private String reason;

    @Column
    private LocalDate startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationPeriod getPeriod() {
        return period;
    }

    public void setPeriod(ApplicationPeriod period) {
        this.period = period;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
