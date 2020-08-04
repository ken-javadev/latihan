package com.demo.service;

import com.demo.bean.BarangEntity;
import com.demo.common.AdvanceSearch;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface BarangService {
    public List<BarangEntity> findAll();
    public List<BarangEntity> findAllPagging(AdvanceSearch params);
    public Long findAllPaggingCount(AdvanceSearch params);
    public BarangEntity findOne(Integer idBarang);
    public void save(BarangEntity barang);
    public void delete(Integer idBarang);
    public Workbook generateExcel(List<BarangEntity> list);
}
