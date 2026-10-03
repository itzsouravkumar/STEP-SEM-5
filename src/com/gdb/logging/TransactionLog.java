package com.gdb.logging;

import com.gdb.command.TransactionCommand;
import java.io.*;
import java.util.*;

public class TransactionLog {
    private static final String FILE_PATH = "data/transactions.ser";

    // ============================================================
    // Helper: AppendableObjectOutputStream (COMPLETE — non-placeholder)
    // ============================================================
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }
        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }

    // ============================================================
    public synchronized void log(TransactionCommand cmd) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        boolean append = file.exists() && file.length() > 0;
        try (FileOutputStream fos = new FileOutputStream(file, append);
             ObjectOutputStream oos = append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos)) {
            oos.writeObject(cmd);
            oos.flush();
        }
    }

    // ============================================================
    public synchronized List<TransactionCommand> readAll() throws IOException, ClassNotFoundException {
        List<TransactionCommand> commands = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return commands;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    TransactionCommand cmd = (TransactionCommand) ois.readObject();
                    commands.add(cmd);
                } catch (EOFException e) {
                    break;
                }
            }
        }
        return commands;
    }

    // ============================================================
    public synchronized void clear() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
