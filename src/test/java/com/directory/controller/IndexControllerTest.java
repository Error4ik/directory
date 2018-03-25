package com.directory.controller;

import com.directory.domain.Directory;
import com.directory.domain.User;
import com.directory.repository.UserRepository;
import com.directory.service.DirectoryService;
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

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * IndexControllerTest.
 *
 * @author Alexey Voronin.
 * @since 24.03.2018.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private DirectoryService directoryService;
    @MockBean
    private User user;
    @MockBean
    private Directory directory;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingRootThenReturnIndexPage() throws Exception {
        Mockito.when(userService.findUserByEmail("user")).thenReturn(user);
        Mockito.when(directoryService.findDirectoriesByUserId(0)).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        verify(this.userService, times(1)).findUserByEmail("user");
        verify(this.directoryService, times(1)).findDirectoriesByUserId(0);
    }
}