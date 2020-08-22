package com.wakaka.basis.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务端
 * @author Crysmart
 * @date 2020/7/26 11:34
 */
public class SocketServerServ {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("连接中。。。");
        //客户机
        Socket client = serverSocket.accept();
        System.out.println("连接成功");
        Scanner scanner = new Scanner(client.getInputStream());
        boolean flag = true;
        while (flag){
            if (scanner.hasNext()) {
                String next = scanner.next();
                System.out.println("接收：" + next);
                if ("bye".equalsIgnoreCase(next)){
                    flag = false;
                    System.out.println("退出");
                }
            }
        }
    }

}
