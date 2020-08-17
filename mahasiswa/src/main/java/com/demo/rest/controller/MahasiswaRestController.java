package com.demo.rest.controller;

import com.demo.bean.jpa.MahasiswaEntity;
import com.demo.business.service.MahasiswaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MahasiswaRestController {

	@Resource
	private MahasiswaService mahasiswaService;
	
	@RequestMapping( value="/mahasiswa",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<MahasiswaEntity> findAll() {
		return mahasiswaService.findAll();
	}

	@RequestMapping( value="/mahasiswa/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public MahasiswaEntity findOne(@PathVariable("id") Integer id) {
		return mahasiswaService.findById(id);
	}
	
	@RequestMapping( value="/mahasiswa",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public MahasiswaEntity create(@RequestBody MahasiswaEntity mahasiswa) {
		return mahasiswaService.create(mahasiswa);
	}

	@RequestMapping( value="/mahasiswa/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public MahasiswaEntity update(@PathVariable("id") Integer id, @RequestBody MahasiswaEntity mahasiswa) {
		return mahasiswaService.update(mahasiswa);
	}

	@RequestMapping( value="/mahasiswa/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		mahasiswaService.delete(id);
	}
	
}
