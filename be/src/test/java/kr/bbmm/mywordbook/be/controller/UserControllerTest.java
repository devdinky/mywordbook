package kr.bbmm.mywordbook.be.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bbmm.mywordbook.be.controller.vm.UserVM;
import kr.bbmm.mywordbook.be.domain.User;
import kr.bbmm.mywordbook.be.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void whenValidInput_thenReturn201() throws Exception {
        // Given
        String testUserLogin = "devdinky";
        String testUserPassword = "myStrongPassword";
        String testUserHashedPassword = "012345678901234567890123456789012345678901234567890123456789";
        String testUserEmail = "devdinky@gmail.com";
        String testUserFirstName = "Sunghoon";
        String testUserLastName = "Kim";
        String testUserLangKey = "en";

        UserVM userVM = new UserVM();
        userVM.setLogin(testUserLogin);
        userVM.setEmail(testUserEmail);
        userVM.setFirstName(testUserFirstName);
        userVM.setLastName(testUserLastName);
        userVM.setLangKey(testUserLangKey);
        userVM.setPassword(testUserPassword);

        User user = new User();
        user.setLogin(testUserLogin);
        user.setPassword(testUserHashedPassword);
        user.setEmail(testUserEmail);
        user.setFirstName(testUserFirstName);
        user.setLastName(testUserLastName);
        user.setLangKey(testUserLangKey);

        // When
        Mockito.when(userService.registerUser(userVM, testUserPassword))
                .thenReturn(user);

        // Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/register")
                .content(new ObjectMapper().writeValueAsString(userVM))
                .contentType("APPLICATION/JSON")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                ;
    }
}