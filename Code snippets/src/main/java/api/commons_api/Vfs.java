package main.java.api.commons_api;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

public class Vfs {
    public static void main(String[] args) throws FileSystemException {
        FileSystemManager fsManager = VFS.getManager();
        String filePath = "file:///src/main.java/api/commons_api/test.txt";
        FileObject file = fsManager.resolveFile(filePath);

        if (file.exists()){
            System.out.println(file.getContent().getSize());

        }else{
            System.out.println("File not found");
        }

    }
}