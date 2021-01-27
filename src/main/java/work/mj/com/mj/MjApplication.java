package work.mj.com.mj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("work.mj.com.mj.dao")
public class MjApplication {

    public static void main(String[] args) {
        SpringApplication.run(MjApplication.class, args);
    }

}
