package com.wjs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * description
 *
 * @author wangjunshan
 * @date 2023/11/29 9:35
 */
@SpringBootApplication
@MapperScan(basePackages = "com.wjs.mapper")
@Import(FdfsClientConfig.class)
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class);
    }

}
