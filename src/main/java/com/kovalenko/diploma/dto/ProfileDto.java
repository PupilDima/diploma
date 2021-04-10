package com.kovalenko.diploma.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@ApiModel(description = "Mentor`s profile")
public class ProfileDto {

    @ApiModelProperty("Id of mentor's profile")
    private Long id;

    @ApiModelProperty("Mentor`s avatar id")
    private Long avatarId;

    @ApiModelProperty("Mentor`s fullName")
    private String fullName;

    @ApiModelProperty(value = "Mentor`s profession", example = "Psychologist")
    private String profession;

    @ApiModelProperty(value = "Mentor`s rating", example = "4.5")
    private Double rating;

    @ApiModelProperty("Mentor`s cost per hour")
    private Double rate;

    @ApiModelProperty("Description of mentor's profile")
    private String description;

    @ApiModelProperty("Mentor`s phone number")
    private String phone;

    @ApiModelProperty("Mentor`s email")
    private String email;

    @ApiModelProperty("Facebook link to mentor's profile")
    private String facebook;

    @ApiModelProperty("Mentor`s profile aspects")
    private Set<String> lifeAspects;

    @ApiModelProperty("Profile number of views")
    private Integer viewsCount;

    @ApiModelProperty("Mentor`s profile reviews")
    private List<ReviewDto> reviews;

    public ProfileDto() {
    }

    public ProfileDto(Long id, Long avatarId, String fullName, String profession, Double rating, Double rate,
                      String description, String phone, String email, String facebook,
                      Set<String> lifeAspects, Integer viewsCount, List<ReviewDto> reviews) {
        this.id = id;
        this.avatarId = avatarId;
        this.fullName = fullName;
        this.profession = profession;
        this.rating = rating;
        this.rate = rate;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.facebook = facebook;
        this.lifeAspects = lifeAspects;
        this.viewsCount = viewsCount;
        this.reviews = reviews;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Set<String> getLifeAspects() {
        return lifeAspects;
    }

    public void setLifeAspects(Set<String> lifeAspectsDto) {
        this.lifeAspects = lifeAspectsDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProfileDto that = (ProfileDto) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(avatarId, that.avatarId) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(profession, that.profession) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(facebook, that.facebook) &&
                Objects.equals(lifeAspects, that.lifeAspects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatarId, fullName, profession, rating, rate, description, phone, email, facebook,
                lifeAspects);
    }

    @Override
    public String toString() {
        return "ProfileDto{" +
                "id=" + id +
                ", avatarId=" + avatarId +
                ", fullName='" + fullName + '\'' +
                ", profession='" + profession + '\'' +
                ", rating=" + rating +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", facebook='" + facebook + '\'' +
                ", lifeAspectsDto=" + lifeAspects +
                '}';
    }
}
