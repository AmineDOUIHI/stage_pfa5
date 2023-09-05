package com.example.controllers;

import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class JythonObjectFactory {
    private static JythonObjectFactory instance;

    private JythonObjectFactory() {
        // Initialize Jython interpreter
        PySystemState.initialize();
    }

    public static JythonObjectFactory getInstance() {
        if (instance == null) {
            instance = new JythonObjectFactory();
        }
        return instance;
    }

    public <T> T createObject(Class<T> clazz, String scriptPath) {
        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.execfile(scriptPath);
            PyObject pyObject = interpreter.get(clazz.getSimpleName());
            return (T) pyObject.__tojava__(clazz);
        }
    }

}
