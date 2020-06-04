import java.io.File;
import java.nio.Buffer;
import java.util.function.Consumer;

public interface HighLevelFileSystem {

    public void setFileSystem(LowLevelFileSystem fileSystem);

    public OpenedFile openFile(String path);

}
