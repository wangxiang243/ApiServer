package com.app;

import org.junit.Test;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class LocalTest {

    @Test
    public void test1() {
        long l = 50 << 20;
        System.out.println(l);
    }

    /**
     * The FieldPosition.
     */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /**
     * This Format for format the data to special format.
     */
    private final static Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");

    /**
     * This Format for format the number to special format.
     */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");

    /**
     * This int is the sequence number ,the default value is 0.
     */
    private static int seq = 0;

    private static final int MAX = 9999;

    /**
     * 时间格式生成序列
     *
     * @return String
     */
    @Test
    public void generateSequenceNo() {

        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }

        System.out.println("THE SQUENCE IS :" + sb.toString());

    }

    @Test
    public void testJoda(){
        String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        System.out.println(s);
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(uuid.length());

        String originalName = "abc.jpg";
        System.out.println(originalName.substring(originalName.lastIndexOf(".")+1));
    }

    @Test
    public void testI(){
        int r;
    }
}
