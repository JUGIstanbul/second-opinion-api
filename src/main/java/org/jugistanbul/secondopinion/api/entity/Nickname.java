package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nicknames")
public class Nickname extends EntityBase {

    private String name;

    public Nickname() {
    }

    public Nickname(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Nickname nickname = (Nickname) o;

        return name.equals(nickname.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
