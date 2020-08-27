package com.demo.rest.controller;

import com.demo.bean.jpa.MahasiswaEntity;
import com.demo.business.service.MahasiswaService;
import com.demo.rest.common.DtoResponse;
import com.demo.web.common.AbstractController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class MahasiswaRestController extends AbstractController {


    @Resource
    private MahasiswaService mahasiswaService;

    public MahasiswaRestController() {
        super(MahasiswaRestController.class, "mahasiswa");
    }

    @RequestMapping("/mahasiswa/form")
    public String list() {
        return "mahasiswa_jquery_form";
    }

    @RequestMapping(value = "/mahasiswa-list",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<MahasiswaEntity> findAll(@RequestBody Map<String, Object> params) {
        return mahasiswaService.findAll();
    }

    @RequestMapping(value = "/mahasiswa-get-by-id/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DtoResponse findOne(@PathVariable("id") Integer id) {
        MahasiswaEntity entity = mahasiswaService.findById(id);
        return new DtoResponse(1, entity, "Sukses");
    }

    @RequestMapping(value = "/mahasiswa-validate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DtoResponse validate(@RequestBody MahasiswaEntity mahasiswa) {
        Map<String, String> messageField = new LinkedHashMap<String, String>();
        if (mahasiswa.getId() == null)
            messageField.put("mahasiswa_id", "Id is Required");
        if (mahasiswa.getNim().isEmpty())
            messageField.put("mahasiswa_nim", "Nim is Required");
        if (mahasiswa.getNama().isEmpty())
            messageField.put("mahasiswa_nama", "Nama is Required");
        if (mahasiswa.getAlamat().isEmpty())
            messageField.put("mahasiswa_alamat", "Alamat is Required");
        if (mahasiswa.getJenisKelamin() == null)
            messageField.put("mahasiswa_jenisKelamin", "Jenis kelamin is Required");
        if (mahasiswa.getTempatLahir().isEmpty())
            messageField.put("mahasiswa_tempatLahir", "Tempat lahir is Required");
        if (mahasiswa.getTanggalLahir() == null)
            messageField.put("mahasiswa_tanggalLahir", "Tanggal lahir is Required");
        if (mahasiswa.getAgama().isEmpty())
            messageField.put("mahasiswa_agama", "Agama is Required");
        if (mahasiswa.getStatusPerkawinan() == null)
            messageField.put("mahasiswa_statusPerkawinan", "Status perkawinan is Required");
        if(messageField.size()>0){
            return new DtoResponse(1, messageField);
        }else{
            return new DtoResponse(0, messageField);
        }
    }

    @RequestMapping(value = "/mahasiswa-save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DtoResponse save(@RequestBody MahasiswaEntity mahasiswa) {
        MahasiswaEntity mahasiswaEntity = mahasiswaService.update(mahasiswa);
        return new DtoResponse(1, mahasiswaEntity, "Sukses");
    }

    @RequestMapping(value = "/mahasiswa-delete",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DtoResponse delete(@RequestParam(value = "id") Integer id) {
        mahasiswaService.delete(id);
        return new DtoResponse(1, null, "Sukses");
    }

}
