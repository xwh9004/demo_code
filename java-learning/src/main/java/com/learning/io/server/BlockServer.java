package com.learning.io.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 13:34 on 2020/5/14
 * @version V0.1
 * @classNmae BlockServer
 */
public class BlockServer {

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        SocketAddress socketAddress = new InetSocketAddress(8080);
        try {
            ServerSocket server = new ServerSocket();
            server.bind(socketAddress);

            System.out.println("服务开始启动......");
            StringBuilder sb = null;
            while (true) {
                System.out.println("等待客户端链接");
                sb = new StringBuilder();
                Socket socket = server.accept();
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            InetAddress clientAddress = socket.getInetAddress();
                            System.out.println("接收来自 " + clientAddress.getHostAddress() + "端口 " + socket.getPort());
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                            DataInputStream in = new DataInputStream(socket.getInputStream());
                            while (true) {
                                String request =  in.readUTF();
                                out.writeUTF(processRequest(request));
                                out.flush();
                            }
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomStr() {
        String str = "";
        int ID = (int) (Math.random() * 30);
        int x = (int) (Math.random() * 200);
        int y = (int) (Math.random() * 300);
        int z = (int) (Math.random() * 10);
        str = "ID:" + ID + "/x:" + x + "/y:" + y + "/z:" + z;
        return str;
    }


    public static String processRequest(String  req) {


        System.out.println("开始处理请求 request = " + req);

        String response = req.toUpperCase();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理请求结束");
        return response;
    }
}
