/* Copyright (c) 2018.06 w-gao */

package wscore.net;

import wscore.util.Binary;

/**
 * Packet
 *
 * @author William Gao
 */
public abstract class Packet {

    private int offset = 0;

    public byte[] buffer;

    /**
     * @return Unique Packet Id
     */
    public abstract byte getPacketId();

    /**
     * @return End of buffer
     */
    protected boolean eof() {

        return !(this.offset >= 0 && this.offset + 1 <= this.buffer.length);
    }

    protected byte[] unpack(int len) {

        if (len < 0) {
            this.offset = this.buffer.length - 1;
            return new byte[0];
        }

        byte[] buffer = new byte[len];
        for (int i = 0; i < len; i++) {
            buffer[i] = this.buffer[this.offset++];
        }

        return buffer;
    }

    protected void pack(byte[] bytes) {

        for (byte b : bytes) {
            this.buffer[this.offset++] = b;
        }
    }

    protected byte unpackByte() {

        return this.buffer[this.offset++];
    }

    protected void packByte(byte b) {

        this.buffer[this.offset++] = b;
    }

    protected int unpackShort() {

        return Binary.unpackShort(this.unpack(2));
    }

    protected void packShort(int v) {

        this.pack(Binary.packShort(v));
    }

    protected int unpackInt() {

        return Binary.unpackInt(this.unpack(4));
    }

    protected void packInt(int v) {

        this.pack(Binary.packInt(v));
    }

    protected float unpackFloat() {

        return Binary.unpackFloat(this.unpack(4));
    }

    protected void packFloat(float f) {

        this.pack(Binary.packFloat(f));
    }

    protected double unpackDouble() {

        return Binary.unpackDouble(this.unpack(8));
    }

    protected void packDouble(double d) {

        this.pack(Binary.packDouble(d));
    }

    protected long unpackLong() {

        return Binary.unpackLong(this.unpack(8));
    }

    protected void packLong(long l) {

        this.pack(Binary.packLong(l));
    }

    protected String unpackString() {

        int length = this.unpackShort();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append((char) this.unpackShort());
        }

        return str.toString();
    }

    protected void packString(String str) {

        this.packShort(str.length());
        for (char c : str.toCharArray()) {
            this.packShort(c);
        }
    }

    /**
     * @return The length of the packet
     */
    public int getLength() {

        return 1;
    }

    /**
     * Serialize the packet
     */
    public void encode() {

        this.buffer = new byte[this.getLength()];

        this.buffer[this.offset++] = getPacketId();

    }

    /**
     * Deserialize the packet from this.buffer
     */
    public void decode() {

        this.offset++;

    }

}
