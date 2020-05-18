package net.chensee.platform.erp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : xx
 * @program: erp
 * @create: 2019-07-09 09:12
 * @description: Mybatis配置
 */
@Configuration
@MapperScan(basePackages = {"net.chensee.platform.erp.**.mapper", "net.chensee.base.**.mapper"})
public class MapperConfig {
}
