package xyz.kail.demo.java.io.tool;

import java.nio.channels.SelectionKey;

public class SelectKeyTool {

    public static void printStatus(SelectionKey key) {
        System.out.print("readyOps: " + key.readyOps() + ", ");
        System.out.print("isAcceptable: " + key.isAcceptable() + ", ");
        System.out.print("isConnectable: " + key.isConnectable() + ", ");
        System.out.print("isReadable: " + key.isReadable() + ", ");
        System.out.print("isWritable: " + key.isWritable() + ", ");
        System.out.print("attachment: " + key.attachment());
        System.out.println();
    }

}
