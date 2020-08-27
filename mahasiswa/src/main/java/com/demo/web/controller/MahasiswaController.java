package com.demo.web.controller;

import com.demo.bean.jpa.MahasiswaEntity;
import com.demo.business.service.MahasiswaService;
import com.demo.web.common.AbstractController;
import com.demo.web.common.FormMode;
import com.demo.web.common.Message;
import com.demo.web.common.MessageType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController extends AbstractController {

	private static final String MAIN_ENTITY_NAME = "mahasiswa";
	private static final String MAIN_LIST_NAME   = "list";

	private static final String JSP_FORM   = "mahasiswa/form";
	private static final String JSP_LIST   = "mahasiswa/list";

	private static final String SAVE_ACTION_CREATE   = "/mahasiswa/create";
	private static final String SAVE_ACTION_UPDATE   = "/mahasiswa/update";

	@Resource
    private MahasiswaService mahasiswaService; // Injected by Spring

	public MahasiswaController() {
		super(MahasiswaController.class, MAIN_ENTITY_NAME );
		log("MahasiswaController created.");
	}

	private void populateModel(Model model, MahasiswaEntity mahasiswa, FormMode formMode) {
		model.addAttribute(MAIN_ENTITY_NAME, mahasiswa);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE);
		}
	}

	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<MahasiswaEntity> list = mahasiswaService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		MahasiswaEntity mahasiswa = new MahasiswaEntity();
		mahasiswa.setId(mahasiswaService.maxId()+1);
		populateModel( model, mahasiswa, FormMode.CREATE);
		return JSP_FORM;
	}

	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Integer id ) {
		log("Action 'formForUpdate'");
		MahasiswaEntity mahasiswa = mahasiswaService.findById(id);
		populateModel( model, mahasiswa, FormMode.UPDATE);		
		return JSP_FORM;
	}

	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid @ModelAttribute("mahasiswa") MahasiswaEntity mahasiswa, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				MahasiswaEntity mahasiswaCreated = mahasiswaService.create(mahasiswa); 
				model.addAttribute(MAIN_ENTITY_NAME, mahasiswaCreated);

				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, mahasiswa.getId() );
			} else {
				populateModel( model, mahasiswa, FormMode.CREATE);
				messageHelper.addException(redirectAttributes, "mahasiswa.error.delete", null);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "mahasiswa.error.create", e);
			populateModel( model, mahasiswa, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid @ModelAttribute("mahasiswa") MahasiswaEntity mahasiswa, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				MahasiswaEntity mahasiswaSaved = mahasiswaService.update(mahasiswa);
				model.addAttribute(MAIN_ENTITY_NAME, mahasiswaSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, mahasiswa.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, mahasiswa, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "mahasiswa.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, mahasiswa, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	@RequestMapping(value = "/delete/{id}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id) {
		log("Action 'delete'" );
		try {
			mahasiswaService.delete( id );
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "mahasiswa.error.delete", e);
		}
		return redirectToList();
	}

}
