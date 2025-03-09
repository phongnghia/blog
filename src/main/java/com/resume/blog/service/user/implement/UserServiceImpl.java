package com.resume.blog.service.user.implement;

import com.resume.blog.entity.es.UserESEntity;
import com.resume.blog.mapper.UserMapper;
import com.resume.blog.dto.user.UserDto;
import com.resume.blog.entity.jpa.UserEntity;
import com.resume.blog.repository.es.UserESRepository;
import com.resume.blog.repository.jpa.UserRepository;
import com.resume.blog.service.user.IUserService;
import com.resume.blog.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository m_userRepository;

    private UserESRepository m_userESRepository;

    private UserMapper m_userMapper;

    @Autowired
    public UserServiceImpl (UserRepository userRepository, UserESRepository userESRepository,
        UserMapper userMapper){
        this.m_userRepository = userRepository;
        this.m_userESRepository = userESRepository;
        this.m_userMapper = userMapper;
    }

    @Override
    public List<UserDto> listUser() {
        return m_userRepository.findAll().stream().map(m_userMapper::userToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        UUID id = Utils.generateRandomId();

        userDto.setId(id);
        UserEntity userEntity = m_userMapper.userToEntity(userDto);
        UserESEntity userESEntity = m_userMapper.userDtoToEntity(userDto);

        m_userRepository.save(userEntity);
        m_userESRepository.save(userESEntity);
        return userDto;
    }

    @Override
    public UserDto findUserByUsername(String username) {
        UserDto userDto = m_userMapper.userToDto(m_userRepository.findUserByUsername(username));
        return userDto;
    }

    @Override
    public UserDto findUserById(UUID uuid) {
        return null;
    }

}
