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


}
