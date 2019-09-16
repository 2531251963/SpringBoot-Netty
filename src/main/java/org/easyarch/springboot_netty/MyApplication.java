package org.easyarch.springboot_netty;

import org.easyarch.springboot_netty.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan(value = "org.easyarch.springboot_netty.mapper")
public class MyApplication implements CommandLineRunner {
        @Autowired
        private NettyServer discardServer;
        public static void main(String[] args) {
            new SpringApplicationBuilder(MyApplication.class).web(WebApplicationType.NONE).run(args);
        }
        @Override
        public void run(String... args) throws Exception {
            discardServer.run(8080);
        }
}
