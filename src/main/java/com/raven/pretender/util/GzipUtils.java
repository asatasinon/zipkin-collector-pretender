package com.raven.pretender.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author: raven
 * @date: 2022/5/24
 * @description:
 */
public class GzipUtils {

    private static final String DEFAULT_CHARSET_NAME = "UTF-8";

    public static byte[] compressToByte(String str) throws IOException {
        return compressToByte(str, DEFAULT_CHARSET_NAME);
    }

    public static byte[] compressToByte(String str, String encoding) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;

        gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes(encoding));
        gzip.close();

        return out.toByteArray();
    }

    public static String uncompressToString(byte[] b) throws IOException {
        return uncompressToString(b, DEFAULT_CHARSET_NAME);
    }

    public static String uncompressToString(byte[] b, String encoding) throws IOException {
        if (b == null || b.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(b);


        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[1024];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toString(encoding);


    }
}

