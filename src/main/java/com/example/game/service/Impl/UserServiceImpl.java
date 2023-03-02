package com.example.game.service.Impl;

import com.example.game.constants.AppConstants;
import com.example.game.dto.ResponseDto;
import com.example.game.dto.UserDto;
import com.example.game.entity.User;
import com.example.game.repository.UserRepository;
import com.example.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDto login(UserDto dto) {
        try {
            if (dto.getUserName() == null || dto.getPassword() == null) {
                return new ResponseDto(null, AppConstants.CODE_500, "Something is wrong!");
            }

            User user = userRepository.findUserByUsernameAndPassword(dto.getUserName(), dto.getPassword());
            return new ResponseDto(null, AppConstants.CODE_200, user);

        } catch (Exception ex) {
            return new ResponseDto(null, AppConstants.CODE_500, AppConstants.SERVER_ERROR);
        }
    }


}
