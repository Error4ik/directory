package com.directory.controller;

import com.directory.domain.Model;
import com.directory.service.ModelService;
import com.directory.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    private PropertyService propertyService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createModel(@RequestParam(value = "data") final List<String> list) {
        Model model = this.modelService.save(this.modelService.prepareToSave(list));
        this.propertyService.prepareToSave(list, model);
        return new ModelAndView(String.format("redirect:/directory/info/%s", model.getDirectory().getId()));
    }

    @RequestMapping(value = "/delete/{id}/{directoryId}", method = RequestMethod.POST)
    public ModelAndView deleteModel(@PathVariable final int id, @PathVariable final int directoryId) {
        this.modelService.deleteModelById(id);
        return new ModelAndView(String.format("redirect:/directory/info/%s", directoryId));
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editModel(@PathVariable final int id, final String name) {
        Model model = this.modelService.findModelById(id);
        model.setName(name);
        this.modelService.save(model);
        return new ModelAndView(String.format("redirect:/directory/info/%s", model.getDirectory().getId()));
    }

    @RequestMapping("/info/{id}")
    public ModelAndView modelInfoPage(@PathVariable final int id) {
        ModelAndView view = new ModelAndView("model-info");
        view.addObject("model", this.modelService.findModelById(id));
        return view;
    }
}
