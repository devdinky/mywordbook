package kr.bbmm.mywordbook.be.repository;

import kr.bbmm.mywordbook.be.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testPasswordColumnConstraint() {
        // Given
        String testUserLogin = "devdinky";
        String testUserNotHashedPassword = "notHashedPassword";
        String testUserEmail = "devdinky@gmail.com";
        String testUserFirstName = "Sunghoon";
        String testUserLastName = "Kim";
        String testUserLangKey = "en";

        User user = new User();
        user.setLogin(testUserLogin);
        user.setPassword(testUserNotHashedPassword);
        user.setEmail(testUserEmail);
        user.setFirstName(testUserFirstName);
        user.setLastName(testUserLastName);
        user.setLangKey(testUserLangKey);

        // When
        Exception exception = assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));

        // Then
        assertEquals(ConstraintViolationException.class, exception.getClass());
    }

    @Test
    public void testSaveUser() {
        // Given
        String testUserLogin = "devdinky";
        String testUserHashedPassword = "012345678901234567890123456789012345678901234567890123456789";
        String testUserEmail = "devdinky@gmail.com";
        String testUserFirstName = "Sunghoon";
        String testUserLastName = "Kim";
        String testUserLangKey = "en";

        User user = new User();
        user.setLogin(testUserLogin);
        user.setPassword(testUserHashedPassword);
        user.setEmail(testUserEmail);
        user.setFirstName(testUserFirstName);
        user.setLastName(testUserLastName);
        user.setLangKey(testUserLangKey);

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertEquals(savedUser.getLogin(), user.getLogin());
    }

    @Test
    public void testFindUserByLogin() {
        // Given
        String testUserLogin = "devdinky";
        String testUserHashedPassword = "012345678901234567890123456789012345678901234567890123456789";
        String testUserEmail = "devdinky@gmail.com";
        String testUserFirstName = "Sunghoon";
        String testUserLastName = "Kim";
        String testUserLangKey = "en";

        User user = new User();
        user.setLogin(testUserLogin);
        user.setPassword(testUserHashedPassword);
        user.setEmail(testUserEmail);
        user.setFirstName(testUserFirstName);
        user.setLastName(testUserLastName);
        user.setLangKey(testUserLangKey);

        User savedUser = userRepository.save(user);
        // When
        Optional<User> findUserOpt = userRepository.findOneByLogin(testUserLogin);

        // Then
        assertTrue(findUserOpt.isPresent());
        assertEquals(findUserOpt.get(), savedUser);
    }

    @Test
    public void testFindUserByEmail() {
        // Given
        String testUserLogin = "devdinky";
        String testUserHashedPassword = "012345678901234567890123456789012345678901234567890123456789";
        String testUserEmail = "devdinky@gmail.com";
        String testUserFirstName = "Sunghoon";
        String testUserLastName = "Kim";
        String testUserLangKey = "en";

        User user = new User();
        user.setLogin(testUserLogin);
        user.setPassword(testUserHashedPassword);
        user.setEmail(testUserEmail);
        user.setFirstName(testUserFirstName);
        user.setLastName(testUserLastName);
        user.setLangKey(testUserLangKey);

        User savedUser = userRepository.save(user);
        // When
        Optional<User> findUserOpt = userRepository.findOneByEmailIgnoreCase(testUserEmail);

        // Then
        assertTrue(findUserOpt.isPresent());
        assertEquals(findUserOpt.get(), savedUser);
    }


}