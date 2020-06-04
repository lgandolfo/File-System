import java.util.function.Consumer;

public class OpenedFile {

    private int fileDescriptor;
    private LowLevelFileSystem fileSystem;


    public OpenedFile(int fd,LowLevelFileSystem fileSystem){
        this.fileDescriptor = fd;
        this.fileSystem = fileSystem;
    }

    public void close(){
        this.fileSystem.closeFile(fileDescriptor);
    }

    public LowLevelFileSystem getFileSystem(){
        return fileSystem;
    }

    public int getFileDescriptor(){
        return fileDescriptor;
    }


    public void closeFile(){
        fileSystem.closeFile(this.getFileDescriptor());
    }


    public void syncRead(Memory buffer) {
        int readBytes = fileSystem.syncReadFile(this.getFileDescriptor(),
                buffer.getBytes(), buffer.getStart(), buffer.getEnd());
        buffer.addBytes(readBytes);
    }


    public void syncWrite(Memory buffer){
        fileSystem.syncWriteFile(this.getFileDescriptor(),
                buffer.getBytes(), buffer.getStart(), buffer.getEnd());
    }


    public void aSyncRead(Consumer<Memory> callback){
        Memory buffer = new Memory(100);
        fileSystem.asyncReadFile(this.getFileDescriptor(),
                buffer.getBytes(), buffer.getStart(), buffer.getEnd(), readBytes -> {
                    buffer.addBytes(readBytes);
                    callback.accept(buffer);
                });
    }

}
