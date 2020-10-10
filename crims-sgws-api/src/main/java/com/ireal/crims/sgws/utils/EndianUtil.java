package com.ireal.crims.sgws.utils;

public class EndianUtil {
    public static byte[] short2LittleEndianBytes(short val){
        return short2Bytes(short2LittleEndian(val));
    }
 
    public static byte[] int2LittleEndianBytes(int val){
        return int2Bytes(int2LittleEndian(val));
    }
 
    public static byte[] long2LittleEndianBytes(long val){
        return long2Bytes(long2LittleEndian(val));
    }
 
    /**
     * Reformats a Little Endian value to bigEndian
     * @param val the value to transform
     * @return Big Endian Value
     */
    public static short short2LittleEndian(short val){
        return (short)(((val&0xff00)>>>8) + ((val&0x00ff)<<8));
    }
 
    /**
     * Reformats a Little Endian value to bigEndian
     * @param val the value to transform
     * @return Big Endian Value
     */
    public static int int2LittleEndian(int val){
        return
                (  ( val&0xff000000 ) >>> 24 ) +
                        ( (( val&0x00ff0000 ) >>> 16 ) << 8 ) +
                        ( (( val&0x0000ff00 ) >>>  8 ) << 16 ) +
                        ( (( val&0x000000ff )        ) << 24 )
                ;
    }
 
    /**
     * Reformats a Little Endian value to bigEndian
     * @param val the value to transform
     * @return Big Endian Value
     */
    public static long long2LittleEndian(long val){
        return
                ( (val&0xff00000000000000L) >>> 56 ) +
                        (((val&0x00ff000000000000L) >>> 48 ) << 8 ) +
                        (((val&0x0000ff0000000000L) >>> 40 ) << 16 ) +
                        (((val&0x000000ff00000000L) >>> 32 ) << 24 ) +
                        (((val&0x00000000ff000000L) >>> 24 ) << 32 ) +
                        (((val&0x0000000000ff0000L) >>> 16 ) << 40 ) +
                        (((val&0x000000000000ff00L) >>> 8  ) << 48 ) +
                        (((val&0x00000000000000ffL)        ) << 56 )
                ;
    }
 
    /**
     * ================================================================================
     *          The other way arround
     *          Fun fact: If you turn the bytes, the bytes turn. If you do it twice, you undo it.
     *          Turn AB to BA and turn it again => AB. Magic :)
     *          So, it's all basically the same functions.
     *
     *          However, we create some alias functions to make it
     *          readable in your source code, what you assume to do :)
     */
 
    /**
     * Reformats a Little Endian value to bigEndian
     * @param val the value to transform
     * @return Big Endian Value
     */
    public static int intFromLittleEndian(int val){
        return int2LittleEndian(val);
    }
 
    public static short shortFromLittleEndian(short val){
        return short2LittleEndian(val);
    }
 
    public static long longFromLittleEndian(long val){
        return long2LittleEndian(val);
    }
 
    public static byte[] long2Bytes(long a) {
        return new byte[] {
                (byte) ((a >> 56) & 0xFF),
                (byte) ((a >> 48) & 0xFF),
                (byte) ((a >> 40) & 0xFF),
                (byte) ((a >> 32) & 0xFF),
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
 
    public static byte[] int2Bytes(int a) {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
 
    public static byte[] short2Bytes(short a) {
        return new byte[] {
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
 
 
 
    public static long bytes2Long(byte[]bytes) {
        return (0xffL & (long)bytes[7])
                | (0xff00L & ((long)bytes[6] << 8))
                | (0xff0000L & ((long)bytes[5] << 16))
                | (0xff000000L & ((long)bytes[4] << 24))
                | (0xff00000000L & ((long)bytes[3] << 32))
                | (0xff0000000000L & ((long)bytes[2] << 40))
                | (0xff000000000000L & ((long)bytes[1] << 48))
                | (0xff00000000000000L & ((long)bytes[0] << 56));
    }
 
    public static long bytes2LongBigEndian(byte[]bytes) {
        return (0xffL & (long)bytes[0])
                | (0xff00L & ((long)bytes[1] << 8))
                | (0xff0000L & ((long)bytes[2] << 16))
                | (0xff000000L & ((long)bytes[3] << 24))
                | (0xff00000000L & ((long)bytes[4] << 32))
                | (0xff0000000000L & ((long)bytes[5] << 40))
                | (0xff000000000000L & ((long)bytes[6] << 48))
                | (0xff00000000000000L & ((long)bytes[7] << 56));
    }
 
    public static int bytes2Int(byte[]bytes) {
        return (bytes[0]&0xff) << 24
                | (bytes[1]&0xff) << 16
                | (bytes[2]&0xff) << 8
                | (bytes[3]&0xff);
    }
 
    public static int bytes2IntBigEndian(byte[]bytes) {
        return (bytes[3]&0xff) << 24
                | (bytes[2]&0xff) << 16
                | (bytes[1]&0xff) << 8
                | (bytes[0]&0xff);
    }
 
    public static short bytes2Short(byte[]bytes) {
        return  (short)((bytes[0]&0xff) << 8
                | (bytes[1]&0xff));
    }
 
    public static int bytes2ShortBigEndian(byte[]bytes) {
        return  (short)((bytes[1]&0xff) << 8
                | (bytes[0]&0xff));
    }
}
