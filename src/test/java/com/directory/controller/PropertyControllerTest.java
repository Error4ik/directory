package com.directory.controller;

import com.directory.domain.Model;
import com.directory.domain.Property;
import com.directory.service.PropertyService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * PropertyControllerTest.
 *
 * @author Alexey Voronin.
 * @since 24.03.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PropertyController.class)
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private PropertyService propertyService;
    @MockBean
    private Model model;
    @MockBean
    private Property property;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingEditPropertyByIdShouldRedirectToModelInfoId() throws Exception {
        Mockito.when(this.propertyService.findPropertyById(1)).thenReturn(property);
        Mockito.when(this.property.getModel()).thenReturn(model);
        Mockito.when(this.model.getId()).thenReturn(1);
        this.mockMvc.perform(post("/property/edit/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/model/info/1"));

        verify(this.propertyService, times(1)).findPropertyById(1);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenMappingDeletePropertyByIdShouldRedirectToModelInfoId() throws Exception {

        this.mockMvc.perform(post("/property/delete/{id}/{modelId}", 1, 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/model/info/1"));

        verify(this.propertyService, times(1)).deleteProperty(1);
    }

}
