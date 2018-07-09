/* Copyright (c) 2018.06 w-gao */

package wscore.util;

/**
 * Binary
 *
 * @author William Gao
 */
public class Binary {

    /**
     * Deserialize a big-endian unsigned short.
     * An unsigned short can have a value from 0 to 65535.
     */
    public static int unpackShort(byte[] bytes) {

        return ((bytes[0] & 0xFF) << 8) + (bytes[1] & 0xFF);
    }

    /**
     * Deserialize a big-endian signed short.
     * A signed short can have a value from -32768 to 32767.
     */
    public static short unpackSignedShort(byte[] bytes) {

        return (short) unpackShort(bytes);
    }

    /**
     * Serialize a big-endian signed or unsigned short.
     */
    public static byte[] packShort(int s) {

        return new byte[]{
                (byte) ((s >>> 8) & 0xFF),
                (byte) (s & 0xFF)
        };
    }

    /**
     * Deserialize a big-endian int32.
     * An integer can have a value from -2147483648 to 2147483647.
     */
    public static int unpackInt(byte[] bytes) {

        return ((bytes[0] & 0xff) << 24) +
                ((bytes[1] & 0xff) << 16) +
                ((bytes[2] & 0xff) << 8) +
                (bytes[3] & 0xff);
    }

    /**
     * Serialize a big-endian signed or unsigned int32.
     */
    public static byte[] packInt(int i) {

        return new byte[]{
                (byte) ((i >>> 24) & 0xFF),
                (byte) ((i >>> 16) & 0xFF),
                (byte) ((i >>> 8) & 0xFF),
                (byte) (i & 0xFF)
        };
    }

    /**
     * Deserialize a big-endian float32 according to the IEEE 754 floating-point standard.
     * Results can store up to 8 bits of exponent and 23 bits of fraction (mantissa)
     */
    public static float unpackFloat(byte[] bytes) {

        return Float.intBitsToFloat(unpackInt(bytes));
    }

    /**
     * Serialize a big-endian float32.
     */
    public static byte[] packFloat(float f) {

        return packInt(Float.floatToIntBits(f));
    }

    /**
     * Deserialize a big-endian long (int64).
     * A long can have a value from 0x8000000000000000L to 0x7fffffffffffffffL.
     */
    public static long unpackLong(byte[] bytes) {
        return ((long) bytes[0] << 56) +
                ((long) (bytes[1] & 0xFF) << 48) +
                ((long) (bytes[2] & 0xFF) << 40) +
                ((long) (bytes[3] & 0xFF) << 32) +
                ((long) (bytes[4] & 0xFF) << 24) +
                ((bytes[5] & 0xFF) << 16) +
                ((bytes[6] & 0xFF) << 8) +
                (bytes[7] & 0xFF);
    }

    /**
     * Serialize a big-endian signed or unsigned long (int64).
     */
    public static byte[] packLong(long l) {
        return new byte[]{
                (byte) (l >>> 56),
                (byte) (l >>> 48),
                (byte) (l >>> 40),
                (byte) (l >>> 32),
                (byte) (l >>> 24),
                (byte) (l >>> 16),
                (byte) (l >>> 8),
                (byte) l
        };
    }

    /**
     * Deserialize a big-endian double (float64) according to the IEEE 754 floating-point standard.
     * Results can store up to 11 bits of exponent and 52 bits of fraction (mantissa)
     */
    public static double unpackDouble(byte[] bytes) {

        return Double.longBitsToDouble(unpackLong(bytes));
    }

    /**
     * Serialize a big-endian double (float64).
     */
    public static byte[] packDouble(double d) {

        return packLong(Double.doubleToLongBits(d));
    }

    /**
     * Dump the given byte array in hexadecimal form to System.out
     */
    public static void hexDump(byte[] buffer) {

        if (buffer == null || buffer.length <= 0) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : buffer) {
            if (!(stringBuilder.length() == 0)) {
                stringBuilder.append(" ");
            }
            int v = b & 0xFF;
            String hex = Integer.toHexString(v);
            if (hex.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hex);
        }

        System.out.println(stringBuilder.toString().toUpperCase());
    }

    /**
     * Dump the given byte array bit by bit to System.out
     */
    public static void bitDump(byte[] buffer) {

        for (byte b : buffer) {
            System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0') + " ");
        }
        System.out.println();
    }

}
