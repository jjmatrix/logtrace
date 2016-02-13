package org.jmatrix.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jmatrix
 * @date 16/2/10
 */
@Controller
@RequestMapping("/config")
public class ConfigManagerController {

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public ModelAndView editWhiteList() {
        ModelAndView view = new ModelAndView("config/whiteconfig");

        return view;
    }


}
