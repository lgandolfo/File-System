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

    @Override
    public void closeFile(OpenedFile file){
        file.close();
    }

    @Override
    public void syncRead(OpenedFile file, Memory buffer) {
        int readBytes = file.getFileSystem().syncReadFile(file.getFileDescriptor(),
                buffer.getBytes(), buffer.getStart(), buffer.getEnd());
        buffer.addBytes(readBytes);
    }

    @Override
    public void syncWrite(OpenedFile file, Memory buffer){
        file.getFileSystem().syncWriteFile(file.getFileDescriptor(),
                buffer.getBytes(), buffer.getStart(), buffer.getEnd());
    }

    @Override
    public void aSyncRead(OpenedFile file, Consumer<Memory> callback){
        Memory buffer = new Memory(100);
        file.getFileSystem().asyncReadFile(file.getFileDescriptor(),
                buffer.getBytes(), buffer.getStart(), buffer.getEnd(), readBytes -> {
                    buffer.addBytes(readBytes);
                    callback.accept(buffer);
                });
    }

}
