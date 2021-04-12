package com.app.common.service.impl;

import com.app.common.entity.PageModel;
import com.app.common.exception.MessageException;
import com.app.common.mapper.BaseMapper;
import com.app.common.service.BaseService;
import com.app.common.util.AppServiceHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T> implements BaseService<T> {

    protected Class<T> entityClass;

    protected String entityClassName;

    protected Logger log;

    public BaseServiceImpl() {
        try {
            Object genericClz = getClass().getGenericSuperclass();
            if (genericClz instanceof ParameterizedType) {
                entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
                entityClassName = entityClass.getSimpleName();
                log = LoggerFactory.getLogger(this.getClass());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    protected BaseMapper<T> getBaseMapper() {
        BaseMapper<T> baseMapper = null;
        if (this.entityClass != null) {
            // 如果两个大写字母开头，spring注解扫描bean的规则为直接返回类名，所以需要特殊处理
            // 判断第二个字母是否为大写
            char c = entityClassName.charAt(1);
            if(c < 97 || c > 122) {
                baseMapper = (BaseMapper<T>) AppServiceHelper
                        .findBean(entityClassName + "Mapper");
            } else {
                baseMapper = (BaseMapper<T>) AppServiceHelper
                        .findBean(StringUtils.uncapitalize(entityClassName) + "Mapper");
            }
        }
        return baseMapper;
    }

    @Override
    public List findAll() {
        try {
            List<T> list = getBaseMapper().find(null);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
    }

    @Override
    public void insert(T entity) {
        getBaseMapper().insert(entity);
    }

    @Override
    public void insertAll(List<T> entityLs) {
        System.out.println("insertAll........."+entityLs);
        for (T entity : entityLs) {
            getBaseMapper().insert(entity);
        }
    }

    @Override
    public void update(T entity) {
        getBaseMapper().update(entity);
    }

    @Override
    public void updateAll(List<T> entityLs) {
        for (T entity : entityLs) {
            getBaseMapper().update(entity);
        }
    }

    @Override
    public void delete(T entity) {
        getBaseMapper().delete(entity);
    }

    @Override
    public void deleteAll(List<T> entityLs) {
        for (T entity : entityLs) {
            getBaseMapper().delete(entity);
        }
    }

    @Override
    public PageModel<T> findByPage(Map<String, String> params) {
        // 过滤params
        params = filterParams(params);
        Integer total = getBaseMapper().findCounts(params);
        Page<T> page = PageHelper.startPage(Integer.valueOf(params.get("pageNum")), Integer.valueOf(params.get("pageSize")));
        getBaseMapper().find(params);
        PageModel<T> result = PageModel.build(page,params);
        result.setTotal(total);
        return result;
    }

    @Override
    public List<T> findByParam(Map<String, String> params) {
        List<T> list = getBaseMapper().find(params);
        return list;
    }

    @Override
    public void uploadFile(MultipartFile file, File dir) throws IOException {
        dir.getParentFile().mkdirs();
        // 上传到指定位置
        file.transferTo(dir);
    }

    protected Map<String, String> filterParams(Map<String, String> params) {
        if(params==null) {
            params = new HashMap<>();
        }
        if(params.get("pageNum")==null) {
            params.put("pageNum", ""+PageModel.PAGENUM);
        }
        if(params.get("pageSize")==null) {
            params.put("pageSize", ""+PageModel.PAGESIZE);
        }
        return params;
    }
}
