package com.kovalenko.diploma.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "All details about the aspect.")
public class LifeAspectDto {
    @ApiModelProperty(notes = "The database generated aspect ID")
    private Long id;

    @ApiModelProperty(notes = "Aspect name")
    private String name;

    public LifeAspectDto() {
    }

    public LifeAspectDto(Long id, String name) {
        this.id = id;
        this.name = name;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LifeAspectDto that = (LifeAspectDto) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "LifeAspectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
