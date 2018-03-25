package com.directory.controller;

import com.directory.domain.User;
import com.directory.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * LoginControllerTest.
 *
 * @author Alexey Voronin.
 * @since 24.03.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private User user;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingLoginThenReturnLoginPage() throws Exception {
        this.mockMvc.perform(
                get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingRegisterNewUserThenReturnIndexView() throws Exception {
        Mockito.when(this.userService.findUserByEmail("test@test.ru")).thenReturn(null);

        this.mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
        verify(this.userService, times(1)).findUserByEmail(new User().getEmail());
        verify(this.userService, times(1)).regUser(new User());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingRegisterWithOldUserShouldReturnRegisterView() throws Exception {
        Mockito.when(userService.findUserByEmail(user.getEmail())).thenReturn(user);

        this.mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

        verify(this.userService, times(1)).findUserByEmail(user.getEmail());
    }
}
