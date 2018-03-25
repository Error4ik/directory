package com.directory.controller;

import com.directory.domain.Property;
import com.directory.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editProperty(@PathVariable final int id, final Property p) {
        Property property = this.propertyService.findPropertyById(id);
        property.setName(p.getName());
        property.setValue(p.getValue());
        this.propertyService.save(property);
        return new ModelAndView(String.format("redirect:/model/info/%s", property.getModel().getId()));
    }

    @RequestMapping(value = "/delete/{id}/{modelId}", method = RequestMethod.POST)
    public ModelAndView deleteProperty(@PathVariable final int id, @PathVariable final int modelId) {
        this.propertyService.deleteProperty(id);
        return new ModelAndView(String.format("redirect:/model/info/%s", modelId));
    }
}
