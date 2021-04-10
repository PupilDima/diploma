package com.kovalenko.diploma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "REVIEW")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROFILE_ID")
    private Long profileId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "COMMENT_DATE")
    LocalDateTime commentDate;

    public Review(Long id, Long profileId, User author, int rating, String comment, LocalDateTime commentDate) {
        this.id = id;
        this.profileId = profileId;
        this.author = author;
        this.rating = rating;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
        Review review = (Review) o;

        return rating == review.rating &&
                Objects.equals(id, review.id) &&
                Objects.equals(author, review.author) &&
                Objects.equals(comment, review.comment) &&
                Objects.equals(commentDate, review.commentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, rating, comment, commentDate);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", author=" + author +
                ", rate=" + rating +
                ", comment='" + comment + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}
