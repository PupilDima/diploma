package com.kovalenko.diploma.filter;

import java.util.List;

public class MentorFilter {
    private String search;
    private String profession;
    private Integer maxRate;
    private Integer minRating;
    private List<String> aspectsNames;

    public MentorFilter(String search, String profession, Integer maxRate, Integer minRating,
                        List<String> aspectsNames) {
        this.search = search;
        this.profession = profession;
        this.maxRate = maxRate;
        this.minRating = minRating;
        this.aspectsNames = aspectsNames;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<String> getAspectsNames() {
        return aspectsNames;
    }

    public void setAspectsNames(List<String> aspectsNames) {
        this.aspectsNames = aspectsNames;
    }

    public Integer getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Integer maxRate) {
        this.maxRate = maxRate;
    }

    public Integer getMinRating() {
        return minRating;
    }

    public void setMinRating(Integer minRating) {
        this.minRating = minRating;
    }

}
