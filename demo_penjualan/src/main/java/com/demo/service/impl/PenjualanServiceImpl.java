package com.demo.service.impl;

import com.demo.bean.PenjualanDetailEntity;
import com.demo.bean.PenjualanEntity;
import com.demo.repository.BarangJpaRepository;
import com.demo.repository.PenjualanDetailJpaRepository;
import com.demo.repository.PenjualanJpaRepository;
import com.demo.service.PenjualanService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional(readOnly = true)
public class PenjualanServiceImpl implements PenjualanService {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private BarangJpaRepository barangJpaRepository;

    @Autowired
    private PenjualanJpaRepository penjualanJpaRepository;

    @Autowired
    private PenjualanDetailJpaRepository penjualanDetailJpaRepository;

    @Override
    public List<PenjualanEntity> findAll() {
        List<PenjualanEntity> result = (List<PenjualanEntity>) penjualanJpaRepository.findAll();
        for (PenjualanEntity item : result) {
            convertPenjualanDetail(item);
        }
        return result;
    }

    @Override
    public PenjualanEntity findOne(Integer idPenjualan) {
        PenjualanEntity penjualanEntity = penjualanJpaRepository.findOne(idPenjualan);
        convertPenjualanDetail(penjualanEntity);
        return penjualanEntity;
    }

    private void convertPenjualanDetail(PenjualanEntity item) {
        item.setPenjualanDetail(item.getListOfPenjualanDetail());
        for (PenjualanDetailEntity obj : item.getListOfPenjualanDetail()) {
            obj.setKodeBarang(obj.getBarang().getKodeBarang());
            obj.setNamaBarang(obj.getBarang().getNamaBarang());
            obj.setHarga(obj.getBarang().getHargaJual());
            obj.setTotalHarga(obj.getBarang().getHargaJual().multiply(BigDecimal.valueOf(obj.getJumlah())));
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void save(PenjualanEntity penjualan) {
        PenjualanEntity penjualanEntity = penjualanJpaRepository.save(penjualan);
        List<PenjualanDetailEntity> listOfPenjualanDetail = penjualan.getPenjualanDetail();
        for (PenjualanDetailEntity item : listOfPenjualanDetail) {
            item.setPenjualan(penjualanEntity);

            String kodeBarang = item.getKodeBarang();
            item.setBarang(barangJpaRepository.findByKodeBarang(kodeBarang));

            penjualanDetailJpaRepository.save(item);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer idPenjualan) {
        PenjualanEntity one = findOne(idPenjualan);
        for (PenjualanDetailEntity item : one.getListOfPenjualanDetail()) {
            penjualanDetailJpaRepository.delete(item);
        }
        penjualanJpaRepository.delete(idPenjualan);
    }

    @Override
    public Workbook generateExcel(List<PenjualanEntity> list) {
        InputStream inputStream;
        XSSFWorkbook wb = null;
        try {
            String filePathAwal = "template/excel/templatePenjualan.xlsx";
            inputStream = getClass().getClassLoader().getResourceAsStream(filePathAwal);
            List<PenjualanEntity> data = list;

            wb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowAwal = 1;
            for (PenjualanEntity item : data) {
                Row rowHeader = sheet.createRow(rowAwal);
                rowHeader.createCell(0).setCellValue(item.getIdPenjualan() != null ? item.getIdPenjualan().toString() : "-");
                rowHeader.createCell(1).setCellValue(item.getNoFaktur() != null ? item.getNoFaktur() : "-");
                rowHeader.createCell(2).setCellValue(item.getTglPenjualan() != null ? sdf.format(item.getTglPenjualan()) : "-");
                rowHeader.createCell(3).setCellValue(item.getNamaPelanggan() != null ? item.getNamaPelanggan() : "-");

                rowAwal++;
            }
            inputStream.close();
        } catch (IOException e) {
            Logger.getLogger(BarangServiceImpl.class.getName()).log(Level.SEVERE, "ALL", e);
        }
        return wb;
    }
}
