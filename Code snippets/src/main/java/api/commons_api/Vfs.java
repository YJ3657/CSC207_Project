package main.java.api.commons_api;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.*;

public class Vfs {
    public static void main(String[] args) throws FileSystemException {
        FileSystemManager fsManager = VFS.getManager();
        String relFilePath = "Code snippets\\src\\main\\java\\api\\commons_api\\test.txt";
        String absFilePath = System.getProperty("user.dir") + "\\" + relFilePath;
        FileObject file = fsManager.resolveFile(absFilePath);

        if (file.exists()){
            System.out.println(file.getContent().getSize());

        }else{
            System.out.println("File not found");
        }

    }
}