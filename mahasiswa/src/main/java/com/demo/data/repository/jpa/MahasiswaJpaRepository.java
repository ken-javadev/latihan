package com.demo.data.repository.jpa;

import com.demo.bean.jpa.MahasiswaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MahasiswaJpaRepository extends PagingAndSortingRepository<MahasiswaEntity, Integer> {

    @Query("SELECT MAX(id) FROM MahasiswaEntity ")
    Integer getMaxId();
}
