package com.kovalenko.diploma.mapper;

import com.kovalenko.diploma.dto.ContactDto;
import com.kovalenko.diploma.entity.Contact;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact map(ContactDto contactDto);

    ContactDto map(Contact contact);
}