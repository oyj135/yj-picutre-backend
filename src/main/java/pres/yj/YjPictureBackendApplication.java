package pres.yj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("pres.yj.mapper") // 扫描mapper接口
@EnableAspectJAutoProxy(exposeProxy = true) // 启动时候开启代理
@SpringBootApplication
public class YjPictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(YjPictureBackendApplication.class, args);
    }

}
