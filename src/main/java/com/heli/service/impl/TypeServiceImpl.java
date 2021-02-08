package com.heli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heli.entity.Type;
import com.heli.mapper.TypeMapper;
import com.heli.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * date: 2021/1/31 13:41
 * @author heli
 * @since JDK 1.8
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper,Type> implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Transactional(readOnly = false)
    @Override
    public int saveType(Type type) {

        return typeMapper.saveType(type);
    }

    @Transactional(readOnly = true)
    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Transactional(readOnly = true)
    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Type> getBlogType() {
        return typeMapper.getBlogType();
    }

    @Transactional(readOnly = false)
    @Override
    public void updateType(Long id,Type type) {

        typeMapper.updateType(type);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteTypeById(Long id) {

        typeMapper.deleteTypeById(id);
    }
}
