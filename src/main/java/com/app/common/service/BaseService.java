package com.app.common.service;

import com.app.common.entity.PageModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BaseService<T> {

    /**
     * 查询所有（不分页）
     * @return
     */
    public List<T> findAll();

    /**
     * 添加
     * @param entity
     */
    public void insert(T entity);

    /**
     * 添加所有
     * @param entityLs
     */
    public void insertAll(List<T> entityLs);

    /**
     * 更新
     * @param entity
     */
    public void update(T entity);

    /**
     * 更新所有
     * @param entityLs
     */
    public void updateAll(List<T> entityLs);

    /**
     * 删除
     * @param entity
     */
    public void delete(T entity);

    /**
     * 删除所有
     * @param entityLs
     */
    public void deleteAll(List<T> entityLs);

    /**
     * 分页查询
     * @return
     */
    public PageModel<T> findByPage(Map<String, String> params);


    /**
     * 文件上传
     * @param file 需要上传的文件
     * @param dir 需要放的文件路径
     */
    public void uploadFile(MultipartFile file, File dir) throws IOException;

    /**
     * 参数查询
     * @return
     */
    public List<T> findByParam(Map<String, String> params);
}
