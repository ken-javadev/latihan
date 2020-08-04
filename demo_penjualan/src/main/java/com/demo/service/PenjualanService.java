package com.demo.service;

import com.demo.bean.PenjualanEntity;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface PenjualanService {
    public List<PenjualanEntity> findAll();
    public PenjualanEntity findOne(Integer idPenjualan);
    public void save(PenjualanEntity Penjualan);
    public void delete(Integer idPenjualan);
    public Workbook generateExcel(List<PenjualanEntity> list);
}
