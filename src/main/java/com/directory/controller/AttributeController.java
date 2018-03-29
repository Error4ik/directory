package com.directory.controller;

import com.directory.domain.Directory;
import com.directory.domain.DirectoryAttribute;
import com.directory.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * Attribute controller.
 *
 * @author Alexey Voronin.
 * @since 28.03.2018.
 */
@Controller
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @RequestMapping("/delete")
    public ModelAndView deleteAttribute(@RequestParam final UUID attrId, @RequestParam final UUID dirId) {
        this.attributeService.deleteAttribute(attrId);
        return new ModelAndView("redirect:/directory/edit/" + dirId);
    }

    @RequestMapping("/edit")
    public ModelAndView editAttribute(@RequestParam final UUID id, @RequestParam final String name) {
        DirectoryAttribute attribute = this.attributeService.findAttributeById(id);
        attribute.setName(name);
        this.attributeService.save(attribute);
        return new ModelAndView("redirect:/directory/edit/" + attribute.getDirectory().getId());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addNewAttribute(@RequestParam final UUID id, @RequestParam final String name) {
        Directory directory = new Directory();
        directory.setId(id);
        DirectoryAttribute attribute = new DirectoryAttribute();
        attribute.setName(name);
        attribute.setDirectory(directory);
        this.attributeService.save(attribute);
        return new ModelAndView("redirect:/directory/edit/" + id);
    }
}
