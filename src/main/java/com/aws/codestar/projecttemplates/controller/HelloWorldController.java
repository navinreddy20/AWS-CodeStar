package com.aws.codestar.projecttemplates.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aws.codestar.projecttemplates.dao.AlienDao;
import com.aws.codestar.projecttemplates.model.Alien;

/**
 * Basic Spring MVC controller that handles all GET requests.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {

	@Autowired
	AlienDao dao;
	
    private final String siteName;

    public HelloWorldController(final String siteName) {
        this.siteName = siteName;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("siteName", this.siteName);
        return mav;
    }
    
    
    @RequestMapping("my")
    public ModelAndView welcome()
    {
    	ModelAndView mv = new ModelAndView("myPage");
    	
    	return mv;
    	
    }
    
    @RequestMapping("alien/{aid}")
    public ModelAndView getAlien(@PathVariable("aid") int aid)
    {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("showAlien");
    	
    	Alien a = dao.getAlien(aid);
    	mv.addObject("alien",a);
    	
    	return mv;
    	
    }

}
