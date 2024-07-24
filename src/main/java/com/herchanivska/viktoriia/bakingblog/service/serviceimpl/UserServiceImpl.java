package com.herchanivska.viktoriia.bakingblog.service.serviceimpl;

import com.herchanivska.viktoriia.bakingblog.constants.Role;
import com.herchanivska.viktoriia.bakingblog.dto.UserResponseDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserSignUpDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserUpdateDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserUpdatePasswordDto;
import com.herchanivska.viktoriia.bakingblog.exception.*;
import com.herchanivska.viktoriia.bakingblog.model.User;
import com.herchanivska.viktoriia.bakingblog.repository.UserRepository;
import com.herchanivska.viktoriia.bakingblog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = new ModelMapper();
    }

    @Override
    public User save(UserSignUpDto user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) throw new UserExistByEmailException("User with email " + user.getEmail() + " already exist.");
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername.isPresent()) throw new UserExistByUsernameException("User with username " + user.getUsername() + " already exist.");
        if (!user.getPassword().equals(user.getSubmitPassword())) throw new WrongPasswordSubmitException("Password and submit password fields must be same.");
        User userToSave = mapper.map(user, User.class);
        userToSave.setRole(Role.ROLE_USER);
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(userToSave);
    }

    @Override
    public User update(UserUpdateDto user, Long id) {
        User userToUpdate = getUserById(id);
        if(!user.getEmail().equals(userToUpdate.getEmail())) {
            Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
            if (byEmail.isPresent()) throw new UserExistByEmailException("User with email " + user.getEmail() + " already exist.");
        }
        if (!user.getUsername().equals(userToUpdate.getUsername())) {
            Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
            if (byUsername.isPresent()) throw new UserExistByUsernameException("User with username " + user.getUsername() + " already exist.");
        }
        userToUpdate = mapper.map(user, User.class);
        userToUpdate.setId(id);
        return userRepository.save(userToUpdate);
    }

    @Override
    public User updatePassword(UserUpdatePasswordDto dto, Long id) {
        User userToUpdate = getUserById(id);
        if (!dto.getPassword().equals(dto.getSubmitPassword())) {
            throw new WrongPasswordSubmitException("New password and submit password fields must be same.");
        }
        if (!passwordEncoder.matches(dto.getOldPassword(), userToUpdate.getPassword())) {
            throw new WrongPasswordException("Old password is not correct.");
        }
        userToUpdate.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(userToUpdate);
    }

    @Override
    public void delete(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return mapper.map(getUserById(id), UserResponseDto.class);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " does not exist"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " does not exist"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username " + username + " does not exist"));
    }

    @Override
    public Set<User> findFollowers(Long id) {
        return userRepository.findFollowers(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
