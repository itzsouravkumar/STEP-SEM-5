package com.gdb.tests;

import com.gdb.domain.*;
import java.io.*;

public class TestSerialization {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting serialization test...");
        AbstractAccount acc = (AbstractAccount) AccountFactory.createAccount("SAVINGS", "1001", "John", 25, 1000, "ACTIVE", "1234", 0);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(acc);
        oos.close();
        System.out.println("Serialized.");

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        AbstractAccount acc2 = (AbstractAccount) ois.readObject();
        System.out.println("Deserialized: " + acc2.getClass().getName());
    }
}
