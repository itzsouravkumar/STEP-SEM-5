package com.gdb.tests;

import java.io.*;

public class TestAppending {
    static class Appender extends ObjectOutputStream {
        public Appender(OutputStream out) throws IOException { super(out); }
        @Override protected void writeStreamHeader() throws IOException { reset(); }
    }
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos1 = new ObjectOutputStream(baos);
        oos1.writeObject("First");
        oos1.close();
        
        Appender oos2 = new Appender(baos);
        oos2.writeObject("Second");
        oos2.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());
    }
}
