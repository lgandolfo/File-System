import java.io.File;

public class HighLevelFileSystem {

    LowLevelFileSystem fileSystem;

    public HighLevelFileSystem(LowLevelFileSystem fileSystem){
        this.fileSystem = fileSystem;
    }

    public File openFile(String path){
        FileReadWrite file = new FileReadWrite(path,fileSystem);
        return file.open();
    }

    public void closeFile(FileReadWrite file){
       file.close();
    }
}
