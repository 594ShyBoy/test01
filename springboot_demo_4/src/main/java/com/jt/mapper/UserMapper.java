package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

//@Mapper //主启动类加了扫描
public interface UserMapper extends BaseMapper<User> {


}
