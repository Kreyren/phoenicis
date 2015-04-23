package scripts;

import ui.impl.api.EventHandler;
import org.python.core.PyException;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

public class Script {
    enum Type {
        RECENT,
        LEGACY
    }



    private final File script;

    public Script(File script) {
        this.script = script;
    }

    public Type detectScriptType() throws IOException {
        BufferedReader bufferReader = new BufferedReader(new FileReader(this.script));
        String firstLine = bufferReader.readLine();
        if("#!/bin/bash".equals(firstLine)) {
            return Type.LEGACY;
        } else {
            return Type.RECENT;
        }
    }

    public void run() {
        new Thread()
        {
            public void run() {
                try {
                    File pythonPath = new File("src/main/python");
                    System.getProperties().setProperty("python.path", pythonPath.getAbsolutePath());

                    PythonInterpreter pythonInterpreter = new PythonInterpreter();

                    if(detectScriptType() == Type.LEGACY) {
                        File v4wrapper = new File("src/main/python/v4wrapper.py");
                        String filePath = v4wrapper.getAbsolutePath();
                        pythonInterpreter.set("__file__", filePath);
                        pythonInterpreter.set("__scriptToWrap__", script.getAbsolutePath());
                        pythonInterpreter.execfile(filePath);
                    } else {
                        pythonInterpreter.execfile(script.getAbsolutePath());
                    }
                } catch (PyException e) {
                    if(e.getCause() instanceof CancelException || e.getCause() instanceof InterruptedIOException) {
                        System.out.println("The script was canceled! "); // Fixme: better logging system
                    } else {
                        throw e;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
