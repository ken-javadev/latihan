
/*
 * Created on 20 Jun 2017 ( Time 10:26:31 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gvpt.admintool.web.controller;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gvpt.admintool.bean.AuditTrail;
import com.gvpt.admintool.business.service.AuditTrailService;
import com.gvpt.admintool.data.repository.specifications.AuditTrailSpecifications;
//--- Common classes
import com.gvpt.admintool.web.common.AbstractController;
import com.gvpt.admintool.web.common.FormMode;
import com.gvpt.admintool.web.common.Message;
import com.gvpt.admintool.web.common.MessageType;
import com.gvpt.admintool.web.datatable.commons.DataTableRequest;
import com.gvpt.admintool.web.datatable.commons.DataTableResponse;
import com.gvpt.admintool.web.datatable.enumerations.AuditTrailIndexEnum;
import com.gvpt.admintool.web.util.ParamUtil;

//-- Datatable

//--- Entities

//--- Services 



/**
 * Spring MVC controller for 'AuditTrail' management.
 */
@Controller
@RequestMapping("/auditTrail")
public class AuditTrailController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "auditTrail";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "auditTrail/form";
	private static final String JSP_LIST   = "auditTrail/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/auditTrail/create";
	private static final String SAVE_ACTION_UPDATE   = "/auditTrail/update";

	//--- Main entity service
	@Resource
    private AuditTrailService auditTrailService; // Injected by Spring
	//--- Other service(s)

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public AuditTrailController() {
		super(AuditTrailController.class, MAIN_ENTITY_NAME );
		log("AuditTrailController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param auditTrail
	 */
	private void populateModel(Model model, AuditTrail auditTrail, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, auditTrail);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of AuditTrail found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<AuditTrail> list = auditTrailService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new AuditTrail
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		AuditTrail auditTrail = new AuditTrail();	
		populateModel( model, auditTrail, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing AuditTrail
	 * @param model Spring MVC model
	 * @param auditTrailId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{auditTrailId}")
	public String formForUpdate(Model model, @PathVariable("auditTrailId") Long auditTrailId ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		AuditTrail auditTrail = auditTrailService.findById(auditTrailId);
		populateModel( model, auditTrail, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param auditTrail  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid AuditTrail auditTrail, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				AuditTrail auditTrailCreated = auditTrailService.create(auditTrail); 
				model.addAttribute(MAIN_ENTITY_NAME, auditTrailCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, auditTrail.getAuditTrailId() );
			} else {
				populateModel( model, auditTrail, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "auditTrail.error.create", e);
			populateModel( model, auditTrail, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param auditTrail  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid AuditTrail auditTrail, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				AuditTrail auditTrailSaved = auditTrailService.update(auditTrail);
				model.addAttribute(MAIN_ENTITY_NAME, auditTrailSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, auditTrail.getAuditTrailId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, auditTrail, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "auditTrail.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, auditTrail, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param auditTrailId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{auditTrailId}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("auditTrailId") Long auditTrailId) {
		log("Action 'delete'" );
		try {
			auditTrailService.delete( auditTrailId );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "auditTrail.error.delete", e);
		}
		return redirectToList();
	}

	@RequestMapping(value = "/datatableJson", method = RequestMethod.GET)
	public @ResponseBody DataTableResponse<AuditTrail> getDataTableJson(@Valid DataTableRequest dataTableRequest, BindingResult bindingResult, HttpServletRequest request, Model model){
		log("getDataTableJson has called");

		Long auditTrailIdFilter = ParamUtil.getLong(request, "auditTrailIdFilter");
		Long userIdFilter = ParamUtil.getLong(request, "userIdFilter");
		String actionFilter = ParamUtil.getString(request, "actionFilter");
		Date actionDatetimeFilter = ParamUtil.getDate(request, "actionDatetimeFilter");
		String referencesFilter = ParamUtil.getString(request, "referencesFilter");
		String ipAddressFilter = ParamUtil.getString(request, "ipAddressFilter");
		int pageSize = 10;
		if(dataTableRequest.getiDisplayLength() > 0){
			pageSize = dataTableRequest.getiDisplayLength();
		}
		
		DataTableResponse<AuditTrail> response = new DataTableResponse<AuditTrail>();
		String sortedColumn = AuditTrailIndexEnum.getMaps().get(dataTableRequest.getiSortedColumnIndex());
		Direction sort = Sort.Direction.ASC;
		if(dataTableRequest.getsSortDirection().equals("desc")){
			sort = Sort.Direction.DESC;
		}
		Pageable pageable = new PageRequest(dataTableRequest.getPageNumber(), pageSize, sort, 
				sortedColumn);
		Long totalRecord = 0l;

		JSONObject filters = new JSONObject();

		if(auditTrailIdFilter != null){
			filters.put("auditTrailId", auditTrailIdFilter);	
		}
		if(userIdFilter != null){
			filters.put("userId", userIdFilter);	
		}
		if(actionFilter != null && !actionFilter.isEmpty()){
			filters.put("action", actionFilter);	
		}
		if(actionDatetimeFilter != null){
			filters.put("actionDatetime", actionDatetimeFilter);	
		}
		if(referencesFilter != null && !referencesFilter.isEmpty()){
			filters.put("references", referencesFilter);	
		}
		if(ipAddressFilter != null && !ipAddressFilter.isEmpty()){
			filters.put("ipAddress", ipAddressFilter);	
		}
		AuditTrailSpecifications spec = new AuditTrailSpecifications(filters);

		if(
			auditTrailIdFilter != null ||
			userIdFilter != null ||
			(actionFilter != null && !actionFilter.isEmpty()) ||
			actionDatetimeFilter != null ||
			(referencesFilter != null && !referencesFilter.isEmpty()) ||
			(ipAddressFilter != null && !ipAddressFilter.isEmpty())		){
			totalRecord = auditTrailService.countWithSpec(spec);
		} else {
			totalRecord = auditTrailService.countAll();
		}
		
		if (totalRecord.intValue() > 0) {
			if (dataTableRequest.getiDisplayLength() < totalRecord.intValue()) {
				pageSize = dataTableRequest.getiDisplayLength();
			} else {
				pageSize = totalRecord.intValue();
			}
		}
		
		List<AuditTrail> pageResult = new ArrayList<AuditTrail>();
		if(
			auditTrailIdFilter != null ||
			userIdFilter != null ||
			(actionFilter != null && !actionFilter.isEmpty()) ||
			actionDatetimeFilter != null ||
			(referencesFilter != null && !referencesFilter.isEmpty()) ||
			(ipAddressFilter != null && !ipAddressFilter.isEmpty())		){
			pageResult = auditTrailService.findWithSpec(spec, pageable);
			
		} else {
			pageResult = auditTrailService.findAll(pageable);
		}
		response.setDraw(dataTableRequest.getsEcho());
		response.setRecordsFiltered(totalRecord.intValue());
		response.setRecordsTotal(totalRecord.intValue());
		response.setData(pageResult);
		return response;
	}
}
