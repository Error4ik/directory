package com.directory.controller;

import com.directory.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * Directory controller.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Controller
@RequestMapping("/directory")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createDirectory(@RequestParam(value = "data") final List<String> list) {
        this.directoryService.prepareAndSave(list);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView getInfoView(@PathVariable final UUID id) {
        ModelAndView view = new ModelAndView("directory-info");
        view.addObject("directory", this.directoryService.findDirectoryById(id));
        return view;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteDirectory(@RequestParam final UUID id) {
        this.directoryService.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editDirectoryPage(@PathVariable final UUID id) {
        ModelAndView view = new ModelAndView("directory-edit");
        view.addObject("directory", this.directoryService.findDirectoryById(id));
        return view;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView editDirectory(
            @RequestParam final UUID id, @RequestParam final String name, @RequestParam final String description) {
        ModelAndView view = new ModelAndView("redirect:/directory/edit/" + id);
        this.directoryService.updateDirectory(id, name, description);
        return view;
    }
}
