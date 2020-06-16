package com.example.redis.client;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:14 on 2020/6/16
 * @version V0.1
 * @classNmae RedisCommand
 */
public class RedisCommand {
    private static final String _BLANK = " ";
    private static final String SEPARATOR = "\r\n";
    public static final String SET ="set";
    public static final String GET ="get";
    public static final String KEYS ="keys";
    public static final String PING ="ping";
    public static final String CLIENT_LIST = "client list";


    public static String build(String command,String... args){
        StringBuilder cmd = new StringBuilder();
        // 命令加参数个数
        cmd.append("*").append(args.length+1).append(SEPARATOR);
        cmd.append("$").append(command.length()).append(SEPARATOR);
        cmd.append(command).append(SEPARATOR);
        if(args!=null||args.length>0){
            for(String arg:args){
                cmd.append("$").append(arg.length()).append(SEPARATOR);
                cmd.append(arg).append(SEPARATOR);
            }
        }
       return cmd.toString();


    }



}
