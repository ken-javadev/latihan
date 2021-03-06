/*
 * Created on 20 Jun 2017 ( Time 11:34:48 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.gvpt.admintool.web.controller.login;

import com.gvpt.admintool.bean.UserAdmin;
import com.gvpt.admintool.bean.auth.ResetPassword;
import com.gvpt.admintool.business.service.LoginService;
import com.gvpt.admintool.business.service.UserAdminService;



import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class is used to handle reset password feature
 * 
 * @author zuniawan.andiprastia
 *
 */
@Controller
@RequestMapping("/reset_pwd")
public class ResetPasswordController {
	
	@Autowired private UserAdminService userService;
	@Autowired private LoginService loginService;
	@Autowired private MessageSource messagesource;
	
	private static Logger log= LoggerFactory.getLogger(ResetPasswordController.class);
	
		@RequestMapping(method=RequestMethod.GET)
	public String resetPwd(@RequestParam("email") String email,@RequestParam("confirm") String confirm,
			ModelMap model){		
		
		model.addAttribute("menu", "Login");
		model.addAttribute("submenu", "Reset Password");
		
		ResetPassword resetPassword= new ResetPassword();
		resetPassword.setEmail(email);
		if(confirm!=null && !confirm.equals("")){
			resetPassword.setOldPassword(confirm);
		}
		model.addAttribute("resetPassword", resetPassword);
		
		return "login/reset-pwd";
	}
	
	/*
	 * This method is used to handle new submitted password
	 */
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	public String resetPwdSubmit(@Valid ResetPassword resetPassword,
			BindingResult bindingResult,
			ModelMap model, final RedirectAttributes redirectAttributes){
		
		if(bindingResult.hasErrors()){
					
			return "login/reset-pwd";
		}
		
		
		if(!resetPassword.getNewPassword().equals(resetPassword.getRetypeNewPassword())){
			bindingResult.rejectValue("newPassword", "notmatch.password");
			bindingResult.rejectValue("retypeNewPassword", "notmatch.password");
			model.addAttribute("menu", "Login");
			model.addAttribute("submenu", "Reset Password");
			
			model.addAttribute("error",true);
			model.addAttribute("message","Error, your password is not match, please try again!");
			
			return "login/reset-pwd";
		}
		
		String oriName=null;
		UserAdmin oriUser = null;
		
		if(resetPassword.getEmail() !=null){			
			
			try {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
				oriName = auth.getName();
				oriUser = userService.getUserByEmail(oriName);
			} catch (Exception e) {
				
				log.error("This reset password did not have auth user.");
				log.error(e.getMessage());
				
				model.addAttribute("error",true);
				model.addAttribute("message","Fail Reset Password");
			}
			
			if(oriUser!=null && oriUser.getEmail().equals(resetPassword.getEmail())){
				loginService.resetPassword(resetPassword.getEmail(), resetPassword.getNewPassword());
				
				model.addAttribute("success",true);
				model.addAttribute("message","Successfully Reset Password");
				
				log.info("successfully reset password for user ID =" +resetPassword.getEmail());
				
			}else if(resetPassword.getOldPassword()!=null){
				oriUser = userService.getUserByEmail(resetPassword.getEmail());
				if(resetPassword.getOldPassword().equalsIgnoreCase(oriUser.getPassword())){
					loginService.resetPassword(resetPassword.getEmail(), resetPassword.getNewPassword());
					
					model.addAttribute("reset.success", "reset.success");
					
					log.info("successfully reset password for user ID =" +resetPassword.getEmail());
					
					model.addAttribute("success",true);
					model.addAttribute("message","Successfully Reset Password");
					
					redirectAttributes.addFlashAttribute("success", true);
					redirectAttributes.addFlashAttribute("message", messagesource.getMessage("success.msg.reset.pass", null, Locale.getDefault()));
					
				}else{
					
					model.addAttribute("error",true);
					model.addAttribute("message", "security key is not matched");
					return "index";
				}
				
				
			}else{
				
				model.addAttribute("error",true);
				model.addAttribute("message", "security key is not matched");
				
				return "index";
			}
						
			
		}
		
		
			
		
		return "redirect:/";
	}
}
