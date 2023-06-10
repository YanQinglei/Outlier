package com.yql.outlierbackend.service;

import com.yql.outlierbackend.mapper.DataMapper;
import com.yql.outlierbackend.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    @Autowired
    private DataMapper dataMapper;

    public String insert(int ip, String algorithm, int param1, int param2, String filepath, String imgpath) {
        dataMapper.insert(ip, algorithm, param1, param2, filepath, imgpath);
        return "succeed";
    }

    public Data findById(int id) {
        return dataMapper.findById(id);
    }

    public void update(Data data) {
        dataMapper.update(data);
    }

    public void delete(int id) {
        dataMapper.delete(id);
    }

}