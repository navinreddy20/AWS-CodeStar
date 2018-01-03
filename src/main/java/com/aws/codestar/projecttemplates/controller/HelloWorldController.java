package com.aws.codestar.projecttemplates.controller;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aws.codestar.projecttemplates.model.Alien;

/**
 * Basic Spring MVC controller that handles all GET requests.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {

	@Autowired
	SessionFactory sf;
	
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
    @Transactional
    public ModelAndView welcome()
    {
    	ModelAndView mv = new ModelAndView("myPage");
    	
    	Session session = sf.getCurrentSession();
    	Alien a = new Alien();
    	a.setAid(501);
    	a.setAname("Mayank");
    	a.setTech("ML");
    	
    	session.save(a);
    	
    	
    	return mv;
    	
    }

}
