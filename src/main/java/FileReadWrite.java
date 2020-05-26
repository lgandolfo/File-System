import java.io.File;

public class FileReadWrite {

    private int id;
    private File file;
    private String path;
    private LowLevelFileSystem fileSystem;


    public FileReadWrite(String path, LowLevelFileSystem fileSystem){
        this.path = path;
        this.file = new File(this.path);
        this.fileSystem = fileSystem;
    }

    public File open(){
        this.id = fileSystem.openFile(this.path);
        return file;
    }

    public void close(){
        fileSystem.closeFile(id);
    }






}
