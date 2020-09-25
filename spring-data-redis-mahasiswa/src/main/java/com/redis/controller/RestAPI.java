package com.redis.controller;

import com.redis.common.DtoResponse;
import com.redis.model.Mahasiswa;
import com.redis.repository.RedisMahsiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class RestAPI {

    @Autowired
    private RedisMahsiswaRepository redisRepository;

    @RequestMapping(value = "/mahasiswa-list",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Mahasiswa> findAll() {
        return redisRepository.findAll();
    }

    @RequestMapping(value = "/mahasiswa-get-by-id",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DtoResponse findOne(@RequestParam(value = "id") Integer id) {
        Mahasiswa entity = redisRepository.findById(id);
        return new DtoResponse(1, entity, "Sukses");
    }

    @RequestMapping(value = "/mahasiswa-validate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DtoResponse validate(@RequestBody Mahasiswa mahasiswa) {
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
    public DtoResponse save(@RequestBody Mahasiswa mahasiswa) {
        Mahasiswa Mahasiswa = redisRepository.save(mahasiswa);
        return new DtoResponse(1, Mahasiswa, "Sukses");
    }

    @RequestMapping(value = "/mahasiswa-delete",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DtoResponse delete(@RequestParam(value = "id") Integer id) {
        redisRepository.delete(id);
        return new DtoResponse(1, null, "Sukses");
    }

}
