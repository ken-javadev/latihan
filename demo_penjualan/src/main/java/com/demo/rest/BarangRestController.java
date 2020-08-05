
package com.demo.rest;

import com.demo.bean.BarangEntity;
import com.demo.common.AdvanceSearch;
import com.demo.common.JsonResponse;
import com.demo.service.BarangService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class BarangRestController {

    @Autowired
    private BarangService barangService;

    @RequestMapping(value = "/barang-list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse findAll() {
        List<BarangEntity> data= barangService.findAll();

        JsonResponse response =new JsonResponse();
        response.setResult(data);
        response.setStatus(true);
        return response;
    }

    @RequestMapping(value = "/barang-list-pagging",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse findAllPagging(@RequestBody AdvanceSearch params) {
        BigInteger count = barangService.findAllPaggingCount(params);
        List<BarangEntity> data = barangService.findAllPagging(params);
        Map<String,Object> map =new HashMap();
        map.put("total",count);
        map.put("rows", data);

        JsonResponse response =new JsonResponse();
        response.setResult(map);
        response.setStatus(true);
        return response;
    }

    @RequestMapping(value = "/barang-by-id/{idBarang}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse findOne(@PathVariable("idBarang") Integer idBarang) {
        BarangEntity barangEntity = barangService.findOne(idBarang);
        JsonResponse response =new JsonResponse();
        response.setResult(barangEntity);
        response.setStatus(true);
        return response;
    }

    @RequestMapping(value = "/barang-save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse create(@RequestBody BarangEntity barang) {
        barangService.save(barang);
        JsonResponse response =new JsonResponse();
        response.setResult("SUKSES");
        response.setStatus(true);
        return response;
    }

    @RequestMapping(value = "/barang-update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse update(@RequestBody BarangEntity barang) {
        barangService.save(barang);
        JsonResponse response =new JsonResponse();
        response.setResult("SUKSES");
        response.setStatus(true);
        return response;
    }

    @RequestMapping(value = "/barang-delete/{idBarang}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse delete(@PathVariable("idBarang") Integer idBarang) {
        barangService.delete(idBarang);
        JsonResponse response =new JsonResponse();
        response.setResult("SUKSES");
        response.setStatus(true);
        return response;
    }


    @RequestMapping(value = "export-barang-excel", method = RequestMethod.GET)
    public void exportExcel(
            HttpServletResponse response
    ) throws IOException, ParseException {
        List<BarangEntity> list = barangService.findAll();
        Workbook wb = barangService.generateExcel(list);
        String fileName = "data barang";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
        response.setContentType("application/vnd.ms-excel");
        OutputStream outStream = response.getOutputStream();
        wb.write(outStream);
        outStream.flush();
        outStream.close();
    }

    @RequestMapping(value = "export-barang-pdf", method = RequestMethod.GET)
    public void exportPdf( HttpServletResponse response
    ) throws IOException, ParseException, JRException {
        String filePathAwal = "template/pdf/barang.jasper";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePathAwal);
        List<BarangEntity> list = barangService.findAll();

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
        Map parameters = new HashMap();
        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        response.setHeader("Content-Disposition", "inline; filename=data barang.pdf;");
        response.setContentType("application/pdf");
        OutputStream outStream = response.getOutputStream();
        outStream.flush();
        inputStream.close();
        outStream.close();
    }

}
