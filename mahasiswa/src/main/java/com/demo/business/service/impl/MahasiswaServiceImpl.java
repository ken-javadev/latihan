package com.demo.business.service.impl;

import com.demo.bean.jpa.MahasiswaEntity;
import com.demo.business.service.MahasiswaService;
import com.demo.data.repository.jpa.MahasiswaJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService {

	@Resource
	private MahasiswaJpaRepository mahasiswaJpaRepository;
	
	@Override
	public MahasiswaEntity findById(Integer id) {
		return mahasiswaJpaRepository.findOne(id);
	}

	@Override
	public List<MahasiswaEntity> findAll() {
		return (List<MahasiswaEntity>) mahasiswaJpaRepository.findAll();
	}

	@Override
	public MahasiswaEntity save(MahasiswaEntity mahasiswa) {
		return update(mahasiswa) ;
	}

	@Override
	public MahasiswaEntity create(MahasiswaEntity mahasiswa) {
		MahasiswaEntity mahasiswaEntity = mahasiswaJpaRepository.findOne(mahasiswa.getId());
		if( mahasiswaEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		mahasiswaEntity = mahasiswa;
		return mahasiswaJpaRepository.save(mahasiswaEntity);
	}

	@Override
	public MahasiswaEntity update(MahasiswaEntity mahasiswa) {
		return mahasiswaJpaRepository.save(mahasiswa);
	}

	@Override
	public void delete(Integer id) {
		mahasiswaJpaRepository.delete(id);
	}

	@Override
	public Integer maxId() {
		return mahasiswaJpaRepository.getMaxId();
	}

	public MahasiswaJpaRepository getMahasiswaJpaRepository() {
		return mahasiswaJpaRepository;
	}

	public void setMahasiswaJpaRepository(MahasiswaJpaRepository mahasiswaJpaRepository) {
		this.mahasiswaJpaRepository = mahasiswaJpaRepository;
	}

}
