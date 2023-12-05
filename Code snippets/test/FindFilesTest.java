import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindFilesTest {
    private FileSystemManager fsm;

    @Before
    public void init() throws FileSystemException {
        fsm = VFS.getManager();
    }

    @Test
    public void testFileSizeInFolder() throws FileSystemException {
        String relFilePath = "Code snippets\\src\\main\\java\\api\\commons_api\\test.txt";
        String absFilePath = System.getProperty("user.dir") + "\\" + relFilePath;
        FileObject file = fsm.resolveFile(absFilePath);
        long actual = 0;
        if (file.exists()){
            actual = file.getContent().getSize();
        }
        assertEquals(2, actual);
    }

    @Test
    public void testFileSystemException(){
        try{
            String relFilePath = "Code snippets\\src\\main\\java\\api\\commons_api\\fraud.txt";
            String absFilePath = System.getProperty("user.dir") + "\\" + relFilePath;
            FileObject file = fsm.resolveFile(absFilePath);

        } catch (FileSystemException e) {
            // Expected
        }

    }
    }
