package com.demo.repository;

import com.demo.bean.BarangEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BarangJpaRepository extends PagingAndSortingRepository<BarangEntity, Integer> {

    @Query("SELECT b FROM BarangEntity b WHERE b.kodeBarang = :kodeBarang ")
    BarangEntity findByKodeBarang(@Param("kodeBarang") String kodeBarang);
}
