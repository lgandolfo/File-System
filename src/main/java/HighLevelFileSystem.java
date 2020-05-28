import java.io.File;
import java.nio.Buffer;
import java.util.function.Consumer;

public interface HighLevelFileSystem {

    public void setFileSystem(LowLevelFileSystem fileSystem);

    public OpenedFile openFile(String path);

    public void closeFile(OpenedFile file);

    public void syncRead(OpenedFile file, Memory buffer);

    public void syncWrite(OpenedFile file, Memory buffer);

    public void aSyncRead(OpenedFile file, Consumer<Memory> callback);
}
