package com.kovalenko.diploma.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "Contain paths to user avatars")
public class ImageDto {
    @ApiModelProperty("The database generated image ID")
    private Long id;

    @ApiModelProperty("The name of user's image")
    private String name;

    public ImageDto() {
    }

    public ImageDto(Long id) {
        this.id = id;
    }

    public ImageDto(Long id, String name) {
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
        ImageDto imageDto = (ImageDto) o;

        return Objects.equals(id, imageDto.id) &&
                Objects.equals(name, imageDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
