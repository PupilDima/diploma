package com.kovalenko.diploma.specification;

import com.kovalenko.diploma.entity.LifeAspect_;
import com.kovalenko.diploma.entity.Profile_;
import com.kovalenko.diploma.entity.User_;
import com.kovalenko.diploma.entity.Profile;
import com.kovalenko.diploma.entity.UserType;
import com.kovalenko.diploma.filter.MentorFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.kovalenko.diploma.utils.QueryHelper.likePattern;

public class ProfileSpecification implements Specification<Profile> {

    private final MentorFilter mentorFilter;

    public ProfileSpecification(MentorFilter mentorFilter) {
        this.mentorFilter = mentorFilter;
    }

    @Override
    public Predicate toPredicate(Root<Profile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        query.distinct(true);
        query.orderBy(criteriaBuilder.desc(root.get(Profile_.RATING)),
                criteriaBuilder.desc(root.get(Profile_.VIEWS_COUNT)));

        predicates.add(criteriaBuilder.equal(root.get(Profile_.USER).get(User_.USER_TYPE), UserType.MENTOR));
        predicates.addAll(getMentorPredicates(root, criteriaBuilder));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getSearchPredicate(Root<Profile> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.or(
                criteriaBuilder.like(root.get(Profile_.USER).get(User_.FULL_NAME),
                        likePattern(mentorFilter.getSearch())),
                criteriaBuilder.like(root.get(Profile_.PROFESSION), likePattern(mentorFilter.getSearch())),
                criteriaBuilder.like(root.get(Profile_.DESCRIPTION), likePattern(mentorFilter.getSearch())),
                criteriaBuilder.like(root.joinSet(Profile_.LIFE_ASPECTS, JoinType.LEFT).get(LifeAspect_.NAME),
                        likePattern(mentorFilter.getSearch())));
    }

    private Predicate getAspectsNamesPredicate(Root<Profile> root) {
        return root.joinSet(Profile_.LIFE_ASPECTS, JoinType.LEFT).get(LifeAspect_.NAME)
                .in(mentorFilter.getAspectsNames());
    }

    private Predicate getProfessionPredicate(Root<Profile> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(Profile_.PROFESSION), mentorFilter.getProfession());
    }

    private Predicate getMaxRatePredicate(Root<Profile> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lessThan(root.get(Profile_.RATE), mentorFilter.getMaxRate());
    }

    private Predicate getMinRatingPredicate(Root<Profile> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(root.get(Profile_.RATING), mentorFilter.getMinRating());
    }

    private List<Predicate> getMentorPredicates(Root<Profile> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (mentorFilter != null) {
            if (mentorFilter.getSearch() != null && !mentorFilter.getSearch().isEmpty()) {
                predicates.add(getSearchPredicate(root, criteriaBuilder));
            }
            if (mentorFilter.getAspectsNames() != null && !mentorFilter.getAspectsNames().isEmpty()) {
                predicates.add(getAspectsNamesPredicate(root));
            }
            if (mentorFilter.getProfession() != null && !mentorFilter.getProfession().isEmpty()) {
                predicates.add(getProfessionPredicate(root, criteriaBuilder));
            }
            if (mentorFilter.getMaxRate() != null && mentorFilter.getMaxRate() > 0) {
                predicates.add(getMaxRatePredicate(root, criteriaBuilder));
            }
            if (mentorFilter.getMinRating() != null && mentorFilter.getMinRating() > 0) {
                predicates.add(getMinRatingPredicate(root, criteriaBuilder));
            }
        }

        return predicates;
    }
}
