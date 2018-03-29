package com.directory.controller;

import com.directory.domain.User;
import com.directory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(final User user) {
        ModelAndView view = new ModelAndView();
        User u = this.userService.findUserByEmail(user.getEmail());
        if (u != null) {
            view.setViewName("login");
            view.addObject("error", true);
            return view;
        }
        view.setViewName("redirect:/");
        userService.regUser(user);
        return view;
    }
}
