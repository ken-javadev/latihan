package com.demo.repository;

import com.demo.bean.PenjualanEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PenjualanJpaRepository extends PagingAndSortingRepository<PenjualanEntity, Integer> {

}
