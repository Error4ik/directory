package com.directory.controller;

import com.directory.domain.Model;
import com.directory.service.DirectoryService;
import com.directory.service.ModelService;
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
 * Model controller.
 *
 * @author Alexey Voronin.
 * @since 21.03.2018.
 */
@Controller
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @Autowired
    private DirectoryService directoryService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createModel(@RequestParam final UUID id, @RequestParam(value = "data") final List<String> list) {
        this.modelService.prepareAndSave(id, list);
        return new ModelAndView(String.format("redirect:/directory/info/%s", id));
    }

    @RequestMapping(value = "/delete/{id}/{directoryId}")
    public ModelAndView deleteModel(@PathVariable final UUID id, @PathVariable final UUID directoryId) {
        this.modelService.deleteModelById(id);
        return new ModelAndView(String.format("redirect:/directory/info/%s", directoryId));
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editModel(@RequestParam final UUID id, @RequestParam final String name) {
        Model model = this.modelService.findModelById(id);
        model.setName(name);
        this.modelService.save(model);
        return new ModelAndView(String.format("redirect:/directory/info/%s", model.getDirectory().getId()));
    }

    @RequestMapping("/info/{id}")
    public ModelAndView modelInfoPage(@PathVariable final UUID id) {
        ModelAndView view = new ModelAndView("model-info");
        view.addObject("model", this.modelService.findModelById(id));
        return view;
    }

    @RequestMapping("/all")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView("all");
        view.addObject("directories", this.directoryService.findAll());
        return view;
    }
}
