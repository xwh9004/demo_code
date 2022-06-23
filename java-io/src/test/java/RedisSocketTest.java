import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:19 on 2020/6/15
 * @version V0.1
 * @classNmae RedisSocketTest
 */
public class RedisSocketTest {

    private static final String SEPARATOR = "\r\n";
    private static final String IP = "118.31.122.126";
    private static final int PORT = 6379;

    public static void main(String[] args) {
         socketConnect();
//        commandInput();
    }

    private static void commandInput() {
        Scanner scanner = new Scanner(System.in);

        String cmd;
        // bye作为退出的判断
        String exit = "bye";
        do {
            System.out.print(IP + PORT + "> ");
            cmd = scanner.nextLine();
            if (!exit.equalsIgnoreCase(cmd)) {
                parseCommand(cmd);
            }
        } while (!exit.equalsIgnoreCase(cmd));

        System.out.println("退出客户端~");
        scanner.close();
    }

    /**
     * 命令行解析, 这里只是演示，各种异常情况根据需要进行处理
     *
     * @param command 需要执行的命令
     */
    private static void parseCommand(String command) {
        Socket socket;
        try {
            socket = new Socket(IP, PORT);
        } catch (IOException e) {
            System.err.println("创建连接失败~");
            return;
        }
        try {
            socket.setSoTimeout(3000);
        } catch (SocketException e) {
            System.err.println("设置超时时间失败~");
            return;
        }
        // 空格或者制表符作为分隔依据
        String[] split = command.split("\\s+");

        // 命令解析
        StringBuilder cmd = new StringBuilder();
        cmd.append("*").append(split.length).append(SEPARATOR);
        for (String arg : split) {
            cmd.append("$").append(arg.length()).append(SEPARATOR);
            cmd.append(arg).append(SEPARATOR);
        }

        InputStream stream;
        String[] result;
        try {
            socket.getOutputStream().write(cmd.toString().getBytes(StandardCharsets.UTF_8));
            stream = socket.getInputStream();
            byte[] bytes = new byte[Short.MAX_VALUE];
            int read = stream.read(bytes);

            String response = new String(bytes, 0 , read, StandardCharsets.UTF_8);
            result = response.split(SEPARATOR);
        } catch (IOException e) {
            System.err.println("执行命令超时~");
            return;
        }

        // 简单命令如果没有错误最后一个是结果，实际情况中需要根据对应情况进行处理
        System.out.println(result[result.length - 1]);

        try {
            stream.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("关闭连接异常~");
        }
    }

    /**
     * socket连接测试
     */
    private static void socketConnect() {
        Socket socket;
        try {
            socket = new Socket(IP, 6379);
        } catch (IOException e) {
            System.err.println("创建连接失败~");
            return;
        }
        try {
            socket.setSoTimeout(3000);
        } catch (SocketException e) {
            System.err.println("设置超时时间失败~");
            return;
        }
        StringBuilder cmd = new StringBuilder();
        // 命令加参数个数
        cmd.append("*2").append(SEPARATOR);
        // 当前命令长度
        cmd.append("$3").append(SEPARATOR);
        cmd.append("get").append(SEPARATOR);
        // 命令参数长度
        cmd.append("$4").append(SEPARATOR);
        cmd.append("name").append(SEPARATOR);

        byte[] bytes;
        int read;
        try {
            socket.getOutputStream().write(cmd.toString().getBytes(StandardCharsets.UTF_8));

            InputStream stream = socket.getInputStream();
            bytes = new byte[Short.MAX_VALUE];
            read = stream.read(bytes);
            stream.close();
        } catch (IOException e) {
            System.err.println("执行命令超时~");
            return;
        }
        String response = new String(bytes, 0 , read, StandardCharsets.UTF_8);
        System.out.println("执行命令：\r\n" + cmd.toString());
        System.out.println("===========================");
        System.out.println("response = " + response);
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("关闭连接异常~");
        }
    }
}
