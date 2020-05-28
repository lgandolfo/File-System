public class Memory {

    private byte[] bytes;
    private int start;
    private int end;

    public Memory(int size) {
        this.bytes = new byte[size];
        this.start = 0;
        this.end = size-1;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void addBytes(int readBytes){
        this.end = this.start + readBytes;
    }

}
