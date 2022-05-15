/*
 * Copyright (c) ISOFT 2013.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 */


import java.io.*;
import java.nio.ByteBuffer;
import java.util.UUID;

public class test {

    public static void main(String[] args) throws IOException {


        PrintStream printStream = null;
        FileOutputStream fileInputStream = null;



            try {

                fileInputStream = new FileOutputStream(new File("log.txt"), true);
                printStream = new PrintStream(fileInputStream);

                printStream.println("process #  " + UUID.randomUUID().toString());
                sameProcessFileSeparator(printStream);

                readMessage(System.in, printStream);

                StringBuilder name = new StringBuilder();
                for (int i = 0; i < 1000; i++) {
                    name.append("test ");
                }

                String outMessage = "{\"name\": "+name.toString()+", \"age\": 12222222, \"exists\": true}";
                writeMessage(outMessage, printStream);


                printStream.println("Final");
                processFileSeparator(printStream);

            } catch (Exception ex) {
                ex.printStackTrace(printStream);
                System.out.println("sssssss");
            }
            fileInputStream.close();
            printStream.close();


            System.exit(0);

//        byte[] hello = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33, 10, 13};
//        System.out.write(hello);
    }

    static public void writeMessage(String pMsg, PrintStream printStream) {

        printStream.println("Start Write");
        sameProcessFileSeparator(printStream);

        try {
            System.out.write(getBytes(pMsg.length()));
            System.out.write(pMsg.getBytes("UTF-8"));
            System.out.flush();

            printStream.println("End Write");
            sameProcessFileSeparator(printStream);

        } catch (IOException ex) {
            ex.printStackTrace(printStream);
        }
    }

    private static String readMessage(InputStream in, PrintStream printStream) {

        printStream.println("Start Read");
        sameProcessFileSeparator(printStream);

        try {
            byte[] b = new byte[4];
            in.read(b);

            int size = getInt(b);
            printStream.println("Input Message Length= " + size);

            if (size == 0) {
                throw new InterruptedIOException("Blocked communication");
            }

            b = new byte[size];
            in.read(b);

            String message = new String(b, "UTF-8");

            printStream.println("Message: " + message);
            sameProcessFileSeparator(printStream);

            printStream.println("End Read");
            sameProcessFileSeparator(printStream);

            return message;

        } catch (Throwable ex) {
            ex.printStackTrace(printStream);
            sameProcessFileSeparator(printStream);
        }

        return null;
    }

    public static void processFileSeparator(PrintStream printStream) {
        printStream.println("\n");
        printStream.println("=============================================");
        printStream.println("\n");
    }

    public static void sameProcessFileSeparator(PrintStream printStream) {
        printStream.println("--------------------------");
    }

    public static int getInt(byte[] bytes) {
        return (bytes[3] << 24) & 0xff000000 |
                (bytes[2] << 16) & 0x00ff0000 |
                (bytes[1] << 8) & 0x0000ff00 |
                (bytes[0] << 0) & 0x000000ff;
    }

    public static byte[] getBytes(int length) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (length & 0xFF);
        bytes[1] = (byte) ((length >> 8) & 0xFF);
        bytes[2] = (byte) ((length >> 16) & 0xFF);
        bytes[3] = (byte) ((length >> 24) & 0xFF);
        return bytes;
    }

    public static byte[] getBytes1(int length) {
        ByteBuffer b = ByteBuffer.allocate(4);
        b.putInt(length);
        return b.array();
    }

    public static byte[] getBytes2(int length) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (length & 11);
        bytes[1] = (byte) ((length >> 8) & 0xFF);
        bytes[2] = (byte) ((length >> 16) & 0xFF);
        bytes[3] = (byte) ((length >> 24) & 0xFF);
        return bytes;
    }
}
