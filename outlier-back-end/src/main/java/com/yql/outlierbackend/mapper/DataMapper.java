package com.yql.outlierbackend.mapper;

import com.yql.outlierbackend.model.Data;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DataMapper {

    @Insert("insert into outlier (ip, algorithm, param1, param2, filepath, imgpath) values(#{ip}, #{algorithm}, #{time}, #{param1}, #{param2}, #{filepath}, #{imgpath})")
    int insert(@Param("ip") int ip,
               @Param("algorithm") String algorithm,
               @Param("param1") int param1,
               @Param("param2") int param2,
               @Param("filepath") String filepath,
               @Param("imgpath") String imgpath);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "algorithm", column = "algorithm"),
            @Result(property = "param1", column = "param1"),
            @Result(property = "param2", column = "param2"),
            @Result(property = "filepath", column = "filepath"),
            @Result(property = "imgpath", column = "imgpath")
    })

    @Select("select * from dream where id = #{id}")
    Data findById(@Param("id") int id);

    @Update("update dream set ip=#{ip}, algorithm=#{algorithm}, param1=#{param1}, param2=#{param2}, filepath=#{filepath}, imgpath=#{imgpath} where id=#{id}")
    void update(Data data);

    @Delete("delete from dream where id =#{id}")
    void delete(int id);

}