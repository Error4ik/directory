package com.directory.controller;

import com.directory.domain.Property;
import com.directory.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * PropertyController.
 *
 * @author Alexey Voronin.
 * @since 23.03.2018.
 */
@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value = "/edit")
    public ModelAndView editProperty(@RequestParam final UUID id, @RequestParam final String value) {
        Property property = this.propertyService.findPropertyById(id);
        property.setValue(value);
        this.propertyService.save(property);
        return new ModelAndView(String.format("redirect:/model/info/%s", property.getModel().getId()));
    }

    @RequestMapping(value = "/delete/{id}/{modelId}")
    public ModelAndView deleteProperty(@PathVariable final UUID id, @PathVariable final UUID modelId) {
        this.propertyService.deleteProperty(id);
        return new ModelAndView(String.format("redirect:/model/info/%s", modelId));
    }
}
