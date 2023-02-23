import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: xwh90
 * @date: 2023/2/23 14:51
 * @description:
 */
public class FileChannelTest {


    @Test
    public void fileReadTest() {
        String filePath = "D:\\我的文档";
        String fileName = "我的账号.txt";
        readFile(filePath, fileName);
    }

    @Test
    public void fileCopyTest() {
        String source = "D:\\我的文档\\学习资料\\e-book\\[JAVA][Effective Java 3rd Edition].pdf";
        String destination = "D:\\我的文档\\学习资料\\e-book\\[JAVA][Effective Java 3rd Edition]_1.pdf";
        copyFile(source, destination);
    }

    private void readFile(String filePath, String fileName) {
        try (FileInputStream fis = new FileInputStream(new File(filePath, fileName))) {
            final FileChannel fileChannel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len =0;
            while ((len =fileChannel.read(buffer)) != -1) {
                //变成读模式
                buffer.flip();
                byte[] bytes = new byte[len];
                buffer.get(bytes);
                //写入到目标
                System.out.println(new String(bytes));
                //变成写模式
                buffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void copyFile(String source, String dest) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(dest)) {
            final FileChannel inChannel = fis.getChannel();
            final FileChannel outChannel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len =0;
            while ((len =inChannel.read(buffer)) != -1) {
                //变成读模式
                buffer.flip();
                //写入到目标
                outChannel.write(buffer);
                //变成写模式
                buffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
