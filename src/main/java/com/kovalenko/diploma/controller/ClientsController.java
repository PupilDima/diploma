package com.kovalenko.diploma.controller;

import com.kovalenko.diploma.dto.UserDto;
import com.kovalenko.diploma.mapper.UserMapper;
import com.kovalenko.diploma.service.UserService;
import com.kovalenko.diploma.entity.User;
import com.kovalenko.diploma.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@ApiIgnore
public class ClientsController {
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public ClientsController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userMapper.map(userService.findAllByUserType(UserType.CLIENT));
    }

    @GetMapping(path = "/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userMapper.map(userService.findByIdAndUserType(id, UserType.CLIENT));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto save(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.map(userDto);
        user.setUserType(UserType.CLIENT);

        return userMapper.map(userService.save(user));
    }

    @PutMapping(path = "/{id}")
    public UserDto update(@RequestBody UserDto userDto, @RequestParam Long id) {
        userDto.setId(id);
        User user = userService.update(userMapper.map(userDto));

        return userMapper.map(user);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
