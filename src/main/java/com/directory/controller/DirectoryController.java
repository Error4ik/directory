package com.directory.controller;

import com.directory.domain.Directory;
import com.directory.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView createDirectory(final Directory directory) {
        this.directoryService.save(directory);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView getInfoView(@PathVariable final int id) {
        ModelAndView view = new ModelAndView("directory-info");
        view.addObject("directory", this.directoryService.findDirectoryById(id));
        return view;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteDirectory(@PathVariable final int id) {
        this.directoryService.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editDirectory(@PathVariable final int id, final Directory dir) {
        Directory directory = this.directoryService.findDirectoryById(id);
        if (directory != null) {
            directory.setDescription(dir.getDescription());
            directory.setName(dir.getName());
        }
        this.directoryService.save(directory);
        return new ModelAndView("redirect:/");
    }
}
