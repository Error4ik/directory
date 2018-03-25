package com.directory.controller;

import com.directory.domain.Directory;
import com.directory.service.DirectoryService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 24.03.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DirectoryController.class)
public class DirectoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectoryService directoryService;
    @MockBean
    private Directory directory;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingDirectoryCreateShouldRedirectToRoot() throws Exception {
        this.mockMvc.perform(post("/directory/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(this.directoryService, times(1)).save(new Directory());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingDirectoryInfoIdShouldReturnDirectoryInfoPageById() throws Exception {
        Mockito.when(this.directoryService.findDirectoryById(1)).thenReturn(directory);
        this.mockMvc.perform(post("/directory/info/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("directory-info"));
        verify(this.directoryService, times(1)).findDirectoryById(1);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingDirectoryDeleteIdShouldRedirectRootPage() throws Exception {

        this.mockMvc.perform(post("/directory/delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(this.directoryService, times(1)).deleteById(1);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingDirectoryEditIdShouldRedirectRootPage() throws Exception {
        Mockito.when(this.directoryService.findDirectoryById(1)).thenReturn(directory);

        this.mockMvc.perform(post("/directory/edit/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(this.directoryService, times(1)).findDirectoryById(1);
        verify(this.directoryService, times(1)).save(new Directory());
    }
}
