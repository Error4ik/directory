package com.directory.controller;

import com.directory.domain.Directory;
import com.directory.domain.User;
import com.directory.service.DirectoryService;
import com.directory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Index controller.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private DirectoryService directoryService;


    @RequestMapping("/")
    public ModelAndView getIndex(final Principal principal) {
        ModelAndView view = new ModelAndView("index");
        User user = this.userService.findUserByEmail(principal.getName());
        int id = user.getId();
        List<Directory> list = this.directoryService.findDirectoriesByUserId(id);
        view.addObject("directories", list);
        return view;
    }
}
