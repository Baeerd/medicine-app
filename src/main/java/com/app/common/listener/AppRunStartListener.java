package com.app.common.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.common.entity.Constant;
import com.app.common.entity.DataConfig;
import com.app.common.service.DataConfigService;
import com.app.shops.entity.Shops;
import com.app.shops.service.ShopsService;

/**
 * 服务器启动监听，加载数据
 */
@Component
public class AppRunStartListener implements ApplicationRunner {

    private Logger log = LoggerFactory.getLogger(AppRunStartListener.class);

    @Autowired
    private DataConfigService dataConfigService;

    @Autowired
    private ShopsService shopsService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        // 加载data_config 配置表数据
        List<DataConfig> allList = dataConfigService.findAll();
        if(allList != null && allList.size() > 0) {
            for (DataConfig dataConfig : allList) {
                if(Constant.dataConfigMap.containsKey(dataConfig.getTypeId())) {
                    Constant.dataConfigMap.get(dataConfig.getTypeId()).put(dataConfig.getValue(), dataConfig.getName());
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put(dataConfig.getValue(), dataConfig.getName());
                    Constant.dataConfigMap.put(dataConfig.getTypeId(), map);
                }
            }
        }
        // 加载商铺信息
        List<Shops> allList2 = shopsService.findAll();
        if(allList2 != null && allList2.size() > 0) {
            Map<String, String> map = new HashMap<>();
            for (Shops shops : allList2) {
                map.put(shops.getId().toString(), shops.getShopsName());
            }
            Constant.dataConfigMap.put(Constant.SHOPS, map);
        }
        log.info("服务器启动.......................加载缓存数据成功...............");
        log.info("缓存数据：" + Constant.dataConfigMap);
        
        
    }
}
