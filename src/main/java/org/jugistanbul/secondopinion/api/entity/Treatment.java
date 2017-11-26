package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Entity
@Table(name = "treatment")
public class Treatment extends EntityBase {

    @Column
    private String description;

    @ManyToOne
    private Case relevantCase;

    @OneToMany
    @JoinColumn(name = "treatment_id")
    private Set<Media> media = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Media> getMedia() {
        return media;
    }

    public void setMedia(Set<Media> media) {
        this.media = media;
    }

    public Case getRelevantCase() {
        return relevantCase;
    }

    public void setRelevantCase(Case relevantCase) {
        this.relevantCase = relevantCase;
    }
}

