import java.util.function.Consumer;

public class HighLevelImplementation implements HighLevelFileSystem {

    private LowLevelFileSystem fileSystem;

    public HighLevelImplementation(LowLevelFileSystem fileSystem){
        this.fileSystem = fileSystem;
    }

    public void setFileSystem(LowLevelFileSystem fileSystem){
        this.fileSystem = fileSystem;
    }

    @Override
    public OpenedFile openFile(String path) {
        int fd = this.fileSystem.openFile(path);
        return new OpenedFile(fd, this.fileSystem);
    }


}
