package xyz.kail.demo.java.io.tool;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChannelTool {

    /**
     * 获取 Resource 下的文件路径，文件名是 clazz.getSimpleName.txt
     */
    public static Path getResourcePath(Class<?> clazz) {
        final URL resource = clazz.getResource("/");
        final String srcPath = resource.getPath().replace("target/classes", "src/main/resources");
        return Paths.get(srcPath, clazz.getSimpleName() + ".txt");
    }

}
