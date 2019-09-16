package org.easyarch.springboot_netty;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @ClassName Test
 * @Description https://github.com/wanghongfei/spring-boot-starter-vertx
 * @Author Liyihe
 * @Date 2019/09/15 下午11:29
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args){
        try {
            Socket socket=new Socket("localhost",8080);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter=new PrintWriter(outputStream);
            printWriter.write("$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$");
            printWriter.flush();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
