package com.demo.io.client;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 13:48 on 2020/5/14
 * @version V0.1
 * @classNmae BlockClient
 */

public class BlockClient {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);

            Scanner scanner = new Scanner(System.in);
            DataInputStream dis = null;
            DataOutputStream dout = null;
            dout = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            while (true) {
                //发送数据
                dout.writeUTF(scanner.nextLine());
                //接收数据
                System.out.println("server:" + dis.readUTF());
            }

//


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
