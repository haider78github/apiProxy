package com.softech.ls360.web.proxy.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.softech.ls360.web.proxy.config.annotation.WebController;
import com.softech.ls360.web.proxy.security.UserPrincipal;
import com.softech.ls360.web.proxy.validation.NotBlank;

@WebController
public class AuthenticationController {

	private static final Logger log = LogManager.getLogger();

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(Map<String, Object> model, HttpSession session) {
		
		if(SecurityContextHolder.getContext().getAuthentication() instanceof UserPrincipal) {
			log.info("Redirecting to /dashboard/list");
		    return new ModelAndView(new RedirectView("/dashboard/list", true, false));
		}
           
        model.put("loginForm", new LoginForm());
        return new ModelAndView("login");
	}

	public static class LoginForm {
		
		@NotBlank(message = "{validate.authenticate.username}")
		private String username;
		
		@NotBlank(message = "{validate.authenticate.password}")
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

}
