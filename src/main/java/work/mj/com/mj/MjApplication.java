package work.mj.com.mj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("work.mj.com.mj.dao")//操作数据库的接口文件的位置
public class MjApplication {

    public static void main(String[] args) {
        SpringApplication.run(MjApplication.class, args);
    }

}
