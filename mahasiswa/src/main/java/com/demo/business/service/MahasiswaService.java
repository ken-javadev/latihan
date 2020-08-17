package com.demo.business.service;

import com.demo.bean.jpa.MahasiswaEntity;

import java.util.List;

public interface MahasiswaService { 

	MahasiswaEntity findById( Integer id  ) ;

	List<MahasiswaEntity> findAll();

	MahasiswaEntity save(MahasiswaEntity entity);

	MahasiswaEntity update(MahasiswaEntity entity);

	MahasiswaEntity create(MahasiswaEntity entity);

	void delete( Integer id );

	Integer maxId() ;

}
