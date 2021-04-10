package com.kovalenko.diploma.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PROFILE")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROFESSION")
    private String profession;

    @Column(name = "RATING")
    private Double rating;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "views")
    private Integer viewsCount;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PROFILE_ASPECT", joinColumns = @JoinColumn(name = "ASPECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROFILE_ID"))
    private Set<LifeAspect> lifeAspects;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "profileId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Profile(String profession, Double rating, String description, Double rate, Integer viewsCount,
                   Set<LifeAspect> lifeAspects, User user, List<Review> reviews) {
        this.profession = profession;
        this.rating = rating;
        this.description = description;
        this.rate = rate;
        this.viewsCount = viewsCount;
        this.lifeAspects = lifeAspects;
        this.user = user;
        this.reviews = reviews;
    }

    public Profile() {
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int views) {
        this.viewsCount = views;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Set<LifeAspect> getLifeAspects() {
        return lifeAspects;
    }

    public void setLifeAspects(Set<LifeAspect> lifeAspects) {
        this.lifeAspects = lifeAspects;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;

        return rate.equals(profile.rate) &&
                Objects.equals(id, profile.id) &&
                Objects.equals(profession, profile.profession) &&
                Objects.equals(rating, profile.rating) &&
                Objects.equals(viewsCount, profile.viewsCount) &&
                Objects.equals(description, profile.description) &&
                Objects.equals(lifeAspects, profile.lifeAspects) &&
                Objects.equals(user, profile.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getRating(), getRate(), getViewsCount(), getUser());
    }

    @Override
    public String toString() {
        return "Profile{" +
                ", profession='" + profession + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", viewsCount=" + viewsCount +
                ", lifeAspects=" + lifeAspects +
                ", user=" + user +
                '}';
    }
}
