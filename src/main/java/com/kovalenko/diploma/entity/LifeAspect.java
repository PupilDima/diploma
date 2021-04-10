package com.kovalenko.diploma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ASPECT")
public class LifeAspect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "lifeAspects", fetch = FetchType.LAZY)
    private Set<Profile> profiles;

    public LifeAspect(String name) {
        this.name = name;
    }

    public LifeAspect() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LifeAspect that = (LifeAspect) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(profiles, that.profiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getProfiles());
    }

    @Override
    public String toString() {
        return "LifeAspect{" +
                "name='" + name + '\'' +
                ", profiles=" + profiles +
                '}';
    }
}
