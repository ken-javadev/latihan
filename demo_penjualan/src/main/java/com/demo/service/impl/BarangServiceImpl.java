package com.demo.service.impl;

import com.demo.bean.BarangEntity;
import com.demo.common.AdvanceSearch;
import com.demo.repository.BarangJpaRepository;
import com.demo.service.BarangService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional(readOnly = true)
public class BarangServiceImpl extends AbstractService<BarangEntity> implements BarangService{

    @Autowired
    private BarangJpaRepository barangJpaRepository;

    @Override
    public List<BarangEntity> findAll() {
        return (List<BarangEntity>) barangJpaRepository.findAll();
    }

    @Override
    public List<BarangEntity> findAllPagging(AdvanceSearch params) {
        String param = params.getSearch()!=null ? params.getSearch() : "";

        String order = "";
        if (params.getSort() != null) {
            if (params.getSort().equalsIgnoreCase("kodeBarang")) {
                if(params.getOrder().equalsIgnoreCase("desc")){
                    order = "order by kode_barang desc";
                }else{
                    order = "order by kode_barang asc";
                }
            }else if (params.getSort().equalsIgnoreCase("namaBarang")) {
                if(params.getOrder().equalsIgnoreCase("desc")){
                    order = "order by nama_barang desc";
                }else{
                    order = "order by nama_barang asc";
                }
            }else if (params.getSort().equalsIgnoreCase("hargaJual")) {
                if(params.getOrder().equalsIgnoreCase("desc")){
                    order = "order by harga_jual desc";
                }else{
                    order = "order by harga_jual asc";
                }
            }else if (params.getSort().equalsIgnoreCase("hargaBeli")) {
                if(params.getOrder().equalsIgnoreCase("desc")){
                    order = "order by harga_beli desc";
                }else{
                    order = "order by harga_beli asc";
                }
            }else if (params.getSort().equalsIgnoreCase("stok")) {
                if(params.getOrder().equalsIgnoreCase("desc")){
                    order = "order by stok desc";
                }else{
                    order = "order by stok asc";
                }
            }
        }

        String query = "SELECT * FROM barang WHERE UPPER(nama_barang) LIKE UPPER(:param) "+order+" ";
        Query q = getSession().createSQLQuery(query);
        q.setParameter("param","%"+ param +"%");
        q.setFirstResult(params.getOffset());
        q.setMaxResults(params.getLimit());

        List<Map<String, Object>> list = q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        List<BarangEntity> result = new ArrayList<BarangEntity>();
        for (Map<String, Object> item : list) {
            BarangEntity obj = new BarangEntity();
            obj.setIdBarang((Integer) item.get("id_barang"));
            obj.setKodeBarang((String) item.get("kode_barang"));
            obj.setNamaBarang((String) item.get("nama_barang"));
            obj.setHargaJual((BigDecimal) item.get("harga_jual"));
            obj.setHargaBeli((BigDecimal) item.get("harga_beli"));
            obj.setStok((Integer) item.get("stok"));
            result.add(obj);
        }
        return result;
    }

    @Override
    public Long findAllPaggingCount(AdvanceSearch params) {
        String param = params.getSearch()!=null ? params.getSearch() : "";

        String queryCount = "SELECT COUNT(*) FROM barang WHERE UPPER(nama_barang) LIKE UPPER(:param)";
        Query q = getSession().createSQLQuery(queryCount);
        q.setParameter("param","%"+ param +"%");
        Long count = (Long) q.uniqueResult();
        return count;
    }

    @Override
    public BarangEntity findOne(Integer idBarang) {
        return barangJpaRepository.findOne(idBarang);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(BarangEntity barang) {
        barangJpaRepository.save(barang);
    }

    @Override
    public void delete(Integer idBarang) {
        barangJpaRepository.delete(idBarang);
    }

    @Override
    public Workbook generateExcel(List<BarangEntity> list) {
        InputStream inputStream;
        XSSFWorkbook wb = null;
        try {
            String filePathAwal = "template/excel/templateBarang.xlsx";
            inputStream = getClass().getClassLoader().getResourceAsStream(filePathAwal);
            List<BarangEntity> data = list;

            wb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowAwal = 1;
            for (BarangEntity item : data) {
                Row rowHeader = sheet.createRow(rowAwal);
                rowHeader.createCell(0).setCellValue(item.getIdBarang() != null ? item.getIdBarang().toString() : "-");
                rowHeader.createCell(1).setCellValue(item.getKodeBarang() != null ? item.getKodeBarang() : "-");
                rowHeader.createCell(2).setCellValue(item.getNamaBarang() != null ? item.getNamaBarang() : "-");
                rowHeader.createCell(3).setCellValue(item.getHargaJual() != null ? item.getHargaJual().toString() : "-");
                rowHeader.createCell(4).setCellValue(item.getHargaBeli() != null ? item.getHargaBeli().toString() : "-");
                rowHeader.createCell(5).setCellValue(item.getStok() != null ? item.getStok().toString() : "-");
                rowAwal++;
            }
            inputStream.close();
        } catch (IOException e) {
            Logger.getLogger(BarangServiceImpl.class.getName()).log(Level.SEVERE, "ALL", e);
        }
        return wb;
    }

}
