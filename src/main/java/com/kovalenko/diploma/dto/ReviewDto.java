package com.kovalenko.diploma.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Objects;

@ApiModel(description = "Review entity")
public class ReviewDto {
    @ApiModelProperty("Review`s id")
    private Long id;

    @ApiModelProperty("Review`s author fullName")
    private String authorFullName;

    @ApiModelProperty("Review`s author id")
    private Long authorId;

    @ApiModelProperty("Review`s profile id")
    private Long profileId;

    @ApiModelProperty("Review`s author avatar property")
    private ImageDto authorAvatar;

    @ApiModelProperty("Review leave an rating")
    private int rating;

    @ApiModelProperty("Review comment text")
    private String comment;

    @ApiModelProperty("Date of leaving the review")
    LocalDateTime commentDate;

    public ReviewDto() {
    }

    public ReviewDto(Long id, String authorFullName, Long authorId, Long profileId, ImageDto authorAvatar, int rating,
                     String comment, LocalDateTime commentDate) {
        this.id = id;
        this.authorFullName = authorFullName;
        this.authorId = authorId;
        this.profileId = profileId;
        this.authorAvatar = authorAvatar;
        this.rating = rating;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public ImageDto getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(ImageDto authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReviewDto reviewDto = (ReviewDto) o;

        return rating == reviewDto.rating &&
                Objects.equals(id, reviewDto.id) &&
                Objects.equals(authorFullName, reviewDto.authorFullName) &&
                Objects.equals(authorAvatar, reviewDto.authorAvatar) &&
                Objects.equals(comment, reviewDto.comment) &&
                Objects.equals(commentDate, reviewDto.commentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorFullName, authorAvatar, rating, comment, commentDate);
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "id=" + id +
                "profileId=" + profileId +
                ", authorFullName='" + authorFullName + '\'' +
                ", authorAvatar=" + authorAvatar +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}
