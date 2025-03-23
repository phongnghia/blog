package com.resume.blog.service.user.implement;

import com.resume.blog.entity.es.UserESEntity;
import com.resume.blog.mapper.BlogMapper;
import com.resume.blog.dto.user.UserDto;
import com.resume.blog.entity.jpa.UserEntity;
import com.resume.blog.mapper.UpdateUserMapper;
import com.resume.blog.repository.es.UserESRepository;
import com.resume.blog.repository.jpa.UserRepository;
import com.resume.blog.service.user.IUserService;
import com.resume.blog.utils.CustomException;
import com.resume.blog.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository m_userRepository;

    private final UserESRepository m_userESRepository;

    private final BlogMapper m_blogMapper;

    @Autowired
    public UserServiceImpl (UserRepository userRepository, UserESRepository userESRepository,
        BlogMapper blogMapper){
        this.m_userRepository = userRepository;
        this.m_userESRepository = userESRepository;
        this.m_blogMapper = blogMapper;
    }

    @Override
    public List<UserDto> listUser() {
        return m_userRepository.findAll().stream().map(m_blogMapper::userToDto)
                .peek(userDto -> userDto.setPasswordHash(null)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        try {
            UUID id = Utils.generateRandomId();

            userDto.setId(id);
            UserEntity userEntity = m_blogMapper.userToEntity(userDto);
            UserESEntity userESEntity = m_blogMapper.userDtoToESEntity(userDto);

            m_userRepository.save(userEntity);
            m_userESRepository.save(userESEntity);
            return userDto;
        } catch (Exception ex) {
            throw new CustomException("An error occurred while adding the User", ex);
        }
    }

    @Override
    public UserDto findUserByUsername(String username) {
        UserDto userDto = m_blogMapper.userToDto(m_userRepository.findUserByUsername(username));
        if (userDto != null) {
            userDto.setPasswordHash(null);
        }
        return userDto;
    }

    @Override
    public UserDto findUserById(UUID id) {
        UserDto userDto = m_blogMapper.userToDto(m_userRepository.findUserById(id));
        if (userDto != null) {
            userDto.setPasswordHash(null);
        }
        return userDto;
    }

    @Override
    @Transactional
    public UserDto updateUser(UUID id, UserDto userDto) {
        try {
            UserEntity user = m_userRepository.findUserById(id);
            UserESEntity userESEntity = m_userESRepository.findUserById(id);

            if (user == null) {
                throw new EntityNotFoundException(String.format("No user found with ID %s", id.toString()));
            }

            if (userESEntity == null) {
                throw new EntityNotFoundException(String.format("No elasticsearch user found with ID %s", id.toString()));
            }

            UpdateUserMapper.updateUserDtoToEntity(user, userDto);
            UpdateUserMapper.updateUserDtoToESEntity(userESEntity, userDto);

            m_userRepository.save(user);
            m_userESRepository.save(userESEntity);

            userDto = m_blogMapper.userToDto(user);
            userDto.setPasswordHash(null);
            return userDto;
        } catch (Exception ex) {
            throw new CustomException("An error occurred while updating the User", ex);
        }
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id) {
        try {
            if (findUserById(id) == null) {
                throw new EntityNotFoundException(String.format("User not found with ID: %s", id.toString()));
            }

            m_userRepository.deleteById(id);
            m_userESRepository.deleteById(id);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw  new CustomException("Cannot delete User due to foreign key constraint",dataIntegrityViolationException);
        } catch (Exception ex) {
            throw new CustomException("An error occurred while deleting the User", ex);
        }
    }

}
