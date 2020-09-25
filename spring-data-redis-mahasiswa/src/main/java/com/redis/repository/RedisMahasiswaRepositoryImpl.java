package com.redis.repository;

import com.redis.model.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RedisMahasiswaRepositoryImpl implements RedisMahsiswaRepository {
    private static final String KEY = "Mahasiswa";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisMahasiswaRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public List<Mahasiswa> findAll() {
        List<Mahasiswa> res = new ArrayList<>();
        Map<String, Mahasiswa> list = hashOperations.entries(KEY);
        for (Mahasiswa item : list.values()) {
            res.add(item);
        }
        return res;
    }

    @Override
    public Mahasiswa findById(Integer id) {
        Mahasiswa mhs = (Mahasiswa) hashOperations.get(KEY, id);
        return mhs;
    }

    @Override
    public Mahasiswa save(Mahasiswa mahasiswa) {
        hashOperations.put(KEY, mahasiswa.getId(), mahasiswa);
        return mahasiswa;
    }

    @Override
    public void delete(Integer id) {
        hashOperations.delete(KEY, id);
    }
}
