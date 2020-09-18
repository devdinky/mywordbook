package kr.bbmm.mywordbook.be.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bbmm.mywordbook.be.controller.vm.LoginVM;
import kr.bbmm.mywordbook.be.domain.User;
import kr.bbmm.mywordbook.be.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.emptyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    public void whenValidInput_thenReturnJWT() throws Exception {
        // Given
        String testUserLogin = "devdinky";
        String testUserEmail = "devdinky@gmail.com";
        String testUserPassword = "myStrongPassword";
        Boolean testUserRememberMe = Boolean.TRUE;

        User user = new User();
        user.setLogin(testUserLogin);
        user.setEmail(testUserEmail);
        user.setPassword(passwordEncoder.encode(testUserPassword));
        userRepository.saveAndFlush(user);

        LoginVM loginVM = new LoginVM();
        loginVM.setUsername(testUserLogin);
        loginVM.setPassword(testUserPassword);
        loginVM.setRememberMe(testUserRememberMe);

        // When

        // Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/authenticate")
                .content(new ObjectMapper().writeValueAsString(loginVM))
                .contentType("APPLICATION/JSON")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_token").isString())
                .andExpect(jsonPath("$.id_token").isNotEmpty())
                .andExpect(header().string("Authorization", not(nullValue())))
                .andExpect(header().string("Authorization", not(is(emptyString()))))
        ;
    }
}