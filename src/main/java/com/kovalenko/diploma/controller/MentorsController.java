package com.kovalenko.diploma.controller;

import com.kovalenko.diploma.dto.ProfileDto;
import com.kovalenko.diploma.dto.UserDto;
import com.kovalenko.diploma.entity.User;
import com.kovalenko.diploma.filter.MentorFilter;
import com.kovalenko.diploma.mapper.ProfileMapper;
import com.kovalenko.diploma.mapper.UserMapper;
import com.kovalenko.diploma.service.ProfileService;
import com.kovalenko.diploma.service.UserService;
import com.kovalenko.diploma.service.impl.ProfileServiceImpl;
import com.kovalenko.diploma.specification.ProfileSpecification;
import com.kovalenko.diploma.entity.UserType;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/mentors")
@Api(value = "Mentors management system")
public class MentorsController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    public MentorsController(UserMapper userMapper, UserService userService, ProfileServiceImpl profileService,
                             ProfileMapper profileMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }

    @GetMapping("/{id}/profile")
    public ProfileDto getMentorById(@PathVariable Long id) {
        return profileMapper.map(profileService.findByUserIdAndUserType(id, UserType.MENTOR));
    }

    @GetMapping
    public List<UserDto> getMentors(MentorFilter filter) {
        return userMapper.mapFromProfiles(profileService.findAll(new ProfileSpecification(filter)));
    }

    @PostMapping
    public UserDto saveMentor(@RequestBody @Valid UserDto newMentor) {
        User user = userMapper.map(newMentor);
        user.setUserType(UserType.MENTOR);

        return userMapper.map(userService.save(user));
    }

    @PutMapping("/{id}")
    public UserDto updateMentor(@RequestBody UserDto userDto, @RequestParam Long id) {
        userDto.setId(id);
        User user = userService.update(userMapper.map(userDto));

        return userMapper.map(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
