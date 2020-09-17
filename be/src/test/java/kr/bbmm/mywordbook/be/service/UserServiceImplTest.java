package kr.bbmm.mywordbook.be.service;

import kr.bbmm.mywordbook.be.domain.User;
import kr.bbmm.mywordbook.be.repository.UserRepository;
import kr.bbmm.mywordbook.be.service.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void assertThatEmailMustBeUnique() {
        // Given
        String testUserLogin = "devdinky";
        String testUserPassword = "myStrongPassword";
        String testUserHashedPassword = "012345678901234567890123456789012345678901234567890123456789";
        String testUserEmail = "devdinky@gmail.com";
        String testUserFirstName = "Sunghoon";
        String testUserLastName = "Kim";
        String testUserLangKey = "en";

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(testUserLogin);
        userDTO.setEmail(testUserEmail);
        userDTO.setFirstName(testUserFirstName);
        userDTO.setLastName(testUserLastName);
        userDTO.setLangKey(testUserLangKey);

        User user = new User();
        user.setLogin(testUserLogin);
        user.setPassword(testUserHashedPassword);
        user.setEmail(testUserEmail);
        user.setFirstName(testUserFirstName);
        user.setLastName(testUserLastName);
        user.setLangKey(testUserLangKey);

        // When
        when(userRepository.findOneByEmailIgnoreCase(testUserEmail))
                .thenReturn(Optional.of(user));

        when(userRepository.findOneByLogin(testUserLogin))
                .thenReturn(Optional.of(user));

        // Then
        assertThrows(EmailAlreadyUsedException.class, () -> userService.registerUser(userDTO, testUserPassword));
    }

    @Test
    public void assertThatLoginMustBeUnique() {
        // Given
        String testUserLogin = "devdinky";
        String testUserPassword = "myStrongPassword";
        String testUserHashedPassword = "012345678901234567890123456789012345678901234567890123456789";
        String testUserEmail = "devdinky@gmail.com";
        String testUserFirstName = "Sunghoon";
        String testUserLastName = "Kim";
        String testUserLangKey = "en";

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(testUserLogin);
        userDTO.setEmail(testUserEmail);
        userDTO.setFirstName(testUserFirstName);
        userDTO.setLastName(testUserLastName);
        userDTO.setLangKey(testUserLangKey);

        User user = new User();
        user.setLogin(testUserLogin);
        user.setPassword(testUserHashedPassword);
        user.setEmail(testUserEmail);
        user.setFirstName(testUserFirstName);
        user.setLastName(testUserLastName);
        user.setLangKey(testUserLangKey);

        // When
        when(userRepository.findOneByEmailIgnoreCase(testUserEmail))
                .thenReturn(Optional.empty());

        when(userRepository.findOneByLogin(testUserLogin))
                .thenReturn(Optional.of(user));

        // Then
        assertThrows(UsernameAlreadyUsedException.class, () -> userService.registerUser(userDTO, testUserPassword));
    }
}