
package com.demo.rest;

import com.demo.bean.PenjualanEntity;
import com.demo.common.JsonResponse;
import com.demo.service.PenjualanService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;
@Controller
@RequestMapping(value = "/api")
public class PenjualanRestController {

	@Autowired
	private PenjualanService penjualanService;
	
	@RequestMapping( value="/penjualan-list",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public JsonResponse findAll() {
		List<PenjualanEntity> data = penjualanService.findAll();

		JsonResponse response = new JsonResponse();
		response.setResult(data);
		response.setStatus(true);
		return response;
	}

	@RequestMapping( value="/penjualan-by-id/{idPenjualan}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public JsonResponse findOne(@PathVariable("idPenjualan") Integer idPenjualan) {
		PenjualanEntity penjualanEntity = penjualanService.findOne(idPenjualan);
		JsonResponse response = new JsonResponse();
		response.setResult(penjualanEntity);
		response.setStatus(true);
		return response;
	}
	
	@RequestMapping( value="/penjualan-save",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public JsonResponse create(@RequestBody PenjualanEntity penjualan) {
		penjualanService.save(penjualan);

		JsonResponse response = new JsonResponse();
		response.setResult("SUKSES");
		response.setStatus(true);
		return response;
	}

	@RequestMapping( value="/penjualan-update",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public JsonResponse update(@RequestBody PenjualanEntity penjualan) {
		penjualanService.save(penjualan);

		JsonResponse response = new JsonResponse();
		response.setResult("SUKSES");
		response.setStatus(true);
		return response;
	}

	@RequestMapping( value="/penjualan-delete/{idPenjualan}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public JsonResponse delete(@PathVariable("idPenjualan") Integer idPenjualan) {
		penjualanService.delete(idPenjualan);

		JsonResponse response = new JsonResponse();
		response.setResult("SUKSES");
		response.setStatus(true);
		return response;
	}

	@RequestMapping(value = "export-penjualan-excel", method = RequestMethod.GET)
	public void exportExcel(
			HttpServletResponse response
	) throws IOException, ParseException {
		List<PenjualanEntity> list = penjualanService.findAll();
		Workbook wb = penjualanService.generateExcel(list);
		String fileName = "data penjualan";
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		response.setContentType("application/vnd.ms-excel");
		OutputStream outStream = response.getOutputStream();
		wb.write(outStream);
		outStream.flush();
		outStream.close();
	}
	
}
