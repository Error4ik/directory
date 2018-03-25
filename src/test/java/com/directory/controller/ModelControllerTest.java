package com.directory.controller;

import com.directory.domain.Directory;
import com.directory.domain.Model;
import com.directory.service.ModelService;
import com.directory.service.PropertyService;
import com.directory.service.UserService;
import org.assertj.core.util.Lists;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * ModelControllerTest.
 *
 * @author Alexey Voronin.
 * @since 25.03.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ModelController.class)
public class ModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelService modelService;
    @MockBean
    private PropertyService propertyService;
    @MockBean
    private UserService userService;
    @MockBean
    private Model model;
    @MockBean
    private Directory directory;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void test() throws Exception {
        Mockito.when(modelService.save(new Model())).thenReturn(model);
        Mockito.when(modelService.prepareToSave(new ArrayList<>(Lists.newArrayList("1")))).thenReturn(new Model());
        Mockito.when(model.getDirectory()).thenReturn(directory);
        Mockito.when(directory.getId()).thenReturn(1);

        this.mockMvc.perform(post("/model/create").param("data", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/directory/info/1"));
        verify(this.modelService, times(1)).save(new Model());
        verify(this.modelService, times(1)).prepareToSave(
                new ArrayList<>(Lists.newArrayList("1")));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingModelDeleteByIdShouldRedirectToDirectoryInfoId() throws Exception {
        this.mockMvc.perform(post("/model/delete/{id}/{directoryId}", 1, 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/directory/info/1"));

        verify(this.modelService, times(1)).deleteModelById(1);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingModelEditByIdShouldRedirectToDirectoryInfoId() throws Exception {
        Mockito.when(this.modelService.findModelById(1)).thenReturn(model);
        Mockito.when(this.model.getDirectory()).thenReturn(directory);
        Mockito.when(this.directory.getId()).thenReturn(1);
        this.mockMvc.perform(post("/model/edit/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/directory/info/1"));

        verify(this.modelService, times(1)).findModelById(1);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whereMappingModelInfoIdShouldReturnModelInfoView() throws Exception {
        Mockito.when(this.modelService.findModelById(1)).thenReturn(model);
        Mockito.when(this.model.getDirectory()).thenReturn(directory);
        this.mockMvc.perform(get("/model/info/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("model-info"));

        verify(this.modelService, times(1)).findModelById(1);
    }
}
