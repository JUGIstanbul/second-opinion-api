package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Entity
@Table(name = "patient")
public class Patient extends EntityBase {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
