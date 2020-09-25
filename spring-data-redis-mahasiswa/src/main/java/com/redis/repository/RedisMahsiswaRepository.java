package com.redis.repository;

import com.redis.model.Mahasiswa;
import java.util.List;

public interface RedisMahsiswaRepository {

     List<Mahasiswa> findAll();

     Mahasiswa findById(Integer id);

     Mahasiswa save(Mahasiswa mahasiswa);

    void delete(Integer id);
    
}
