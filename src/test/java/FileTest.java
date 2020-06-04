import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Consumer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FileTest {

        private LowLevelFileSystem lowLevelFileSystem;
        private HighLevelFileSystem highLevelFileSystem;
        private OpenedFile aFile;

        @Before
        public void setup() {
            lowLevelFileSystem = mock(LowLevelFileSystem.class);
            highLevelFileSystem = new HighLevelImplementation(lowLevelFileSystem);
        }

        @Test
        public void sePuedeAbrirUnArchivo() {
            highLevelFileSystem.openFile("unaRuta");
            verify(lowLevelFileSystem).openFile("unaRuta");
        }

        @Test
        public void alAbrirUnArchivoMeDevuelveUnArchivoConElFD() {
            when(lowLevelFileSystem.openFile("unaRuta")).thenReturn(100);
            OpenedFile file = highLevelFileSystem.openFile("unaRuta");
            Assert.assertEquals(file.getFileDescriptor(), 100);
        }

        @Test
        public void sePuedeCerrarUnArchivo() {
            when(lowLevelFileSystem.openFile("unaRuta")).thenReturn(100);
            aFile = highLevelFileSystem.openFile("unaRuta");
            aFile.close();
            verify(lowLevelFileSystem).closeFile(anyInt());
        }


        @Ignore
        public void sePuedeLeerUnArchivoSincronicamente() {
            when(lowLevelFileSystem.openFile("unaRuta")).thenReturn(100);

            aFile = highLevelFileSystem.openFile("unaRuta");
            Memory buffer = new Memory(10);
            aFile.syncRead(buffer);
            verify(lowLevelFileSystem)
                    .syncReadFile(eq(aFile.getFileDescriptor()),
                            same(buffer.getBytes()), eq(buffer.getStart()),
                            eq(buffer.getEnd()));
        }

        @Test
        public void cambiarInicioDeBuffer() {
            Memory buffer = new Memory(10);
            buffer.addBytes(5);
            Assert.assertEquals(buffer.getEnd(), 5);
        }

        @Test
        public void sePuedeLeerAsincronicamente() {
            when(lowLevelFileSystem.openFile("unaRuta")).thenReturn(100);
            aFile = highLevelFileSystem.openFile("unaRuta");

            aFile.aSyncRead((buffer -> {}));
            verify(lowLevelFileSystem).
                    asyncReadFile(anyInt(),
                            any(byte[].class),
                            anyInt(),
                            anyInt(),
                            any(Consumer.class));
        }

}
