package kr.bbmm.mywordbook.be.service;

import kr.bbmm.mywordbook.be.domain.User;
import kr.bbmm.mywordbook.be.service.dto.UserDTO;

public interface UserService {

    User registerUser(UserDTO userDTO, String password);
}
