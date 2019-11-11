package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.service.IUserService;

import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private IUserService userService;
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;

	@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
	    KorisnikDTO userDto = new KorisnikDTO();
	    model.addAttribute("user", userDto);
	    return "registration";
	}
	
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid final KorisnikDTO userDto, 
			final HttpServletRequest request, final Errors errors) {
		LOGGER.debug("Registering user account with information: {}", userDto);

        final Korisnik registered = userService.registerNewUserAccount(userDto);
        if (registered == null) {
            // result.rejectValue("email", "message.regError");
            return new ModelAndView("registration", "user", userDto);
        }
        try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (final Exception ex) {
            LOGGER.warn("Unable to register user", ex);
            return new ModelAndView("emailError", "user", userDto);
        }
        return new ModelAndView("successRegister", "user", userDto);
		
	}
	
	
}
