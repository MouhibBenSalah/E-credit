package com.spring.user.Mappers;

import com.spring.user.DTO.UpdateUserDTO;
import com.spring.user.Entity.User;
import org.modelmapper.ModelMapper;

public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static UpdateUserDTO convertToDto(User user) {
        return modelMapper.map(user, UpdateUserDTO.class);
    }

    public static User convertToEntity(UpdateUserDTO updateUserDTO) {
        return modelMapper.map(updateUserDTO, User.class);
    }
}
