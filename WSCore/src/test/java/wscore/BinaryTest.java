package wscore;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wscore.util.Binary;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * BinaryTest
 *
 * Unit test for {@code Binary}
 *
 * @author w.gao Copyright (c) 2018.06
 * @version 1.0
 */
@DisplayName("Binary")
class BinaryTest {

    @Test
    @DisplayName("Short")
    void shortTest() {

        assertAll(
                // unsigned
                () -> assertEquals(Binary.unpackShort(Binary.packShort(0)), 0),
                () -> assertEquals(Binary.unpackShort(Binary.packShort(127)), 127),
                () -> assertEquals(Binary.unpackShort(Binary.packShort(32767)), 32767),
                () -> assertEquals(Binary.unpackShort(Binary.packShort(65534)), 65534),

                // signed
                () -> assertEquals(Binary.unpackSignedShort(Binary.packShort(-1)), -1),
                () -> assertEquals(Binary.unpackSignedShort(Binary.packShort(-128)), -128),
                () -> assertEquals(Binary.unpackSignedShort(Binary.packShort(-32768)), -32768)
        );
    }

    @Test
    @DisplayName("Int")
    void intTest() {

        assertAll(
                // unsigned
                () -> assertEquals(Binary.unpackInt(Binary.packInt(0)), 0),
                () -> assertEquals(Binary.unpackInt(Binary.packInt(65534)), 65534),
                () -> assertEquals(Binary.unpackInt(Binary.packInt(Integer.MAX_VALUE)), Integer.MAX_VALUE),

                // signed
                () -> assertEquals(Binary.unpackInt(Binary.packInt(-1)), -1),
                () -> assertEquals(Binary.unpackInt(Binary.packInt(-65534)), -65534),
                () -> assertEquals(Binary.unpackInt(Binary.packInt(Integer.MIN_VALUE)), Integer.MIN_VALUE)
        );
    }

    @Test
    @DisplayName("Float")
    void floatTest() {

        assertAll(
                () -> assertEquals(Binary.unpackFloat(Binary.packFloat(0.0f)), 0.0f),
                () -> assertEquals(Binary.unpackFloat(Binary.packFloat(32766.2f)), 32766.2f),
                () -> assertEquals(Binary.unpackFloat(Binary.packFloat(Float.POSITIVE_INFINITY)), Float.POSITIVE_INFINITY),
                () -> assertEquals(Binary.unpackFloat(Binary.packFloat(Float.MAX_VALUE)), Float.MAX_VALUE),

                () -> assertEquals(Binary.unpackFloat(Binary.packFloat(-1.2f)), -1.2f),
                () -> assertEquals(Binary.unpackFloat(Binary.packFloat(Float.NEGATIVE_INFINITY)), Float.NEGATIVE_INFINITY),
                () -> assertEquals(Binary.unpackFloat(Binary.packFloat(Float.MIN_VALUE)), Float.MIN_VALUE)
        );
    }

    @Test
    @DisplayName("Long")
    void longTest() {

        assertAll(
                // unsigned
                () -> assertEquals(Binary.unpackLong(Binary.packLong(0L)), 0L),
                () -> assertEquals(Binary.unpackLong(Binary.packLong(Integer.MAX_VALUE)), Integer.MAX_VALUE),
                () -> assertEquals(Binary.unpackLong(Binary.packLong(Long.MAX_VALUE)), Long.MAX_VALUE),

                // signed
                () -> assertEquals(Binary.unpackLong(Binary.packLong(-1)), -1),
                () -> assertEquals(Binary.unpackLong(Binary.packLong(Integer.MIN_VALUE)), Integer.MIN_VALUE),
                () -> assertEquals(Binary.unpackLong(Binary.packLong(Long.MIN_VALUE)), Long.MIN_VALUE)
        );
    }

    @Test
    @DisplayName("Double")
    void doubleTest() {

        assertAll(
                () -> assertEquals(Binary.unpackDouble(Binary.packDouble(0.0)), 0.0),
                () -> assertEquals(Binary.unpackDouble(Binary.packDouble(32766.2)), 32766.2),
                () -> assertEquals(Binary.unpackDouble(Binary.packDouble(Double.MAX_VALUE)), Double.MAX_VALUE),
                () -> assertEquals(Binary.unpackDouble(Binary.packDouble(-1.2)), -1.2),
                () -> assertEquals(Binary.unpackDouble(Binary.packDouble(Double.MIN_VALUE)), Double.MIN_VALUE)
        );
    }

}
