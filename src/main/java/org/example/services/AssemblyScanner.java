package org.example.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class AssemblyScanner<T> {
    public ArrayList<T> getAllCommandsInPackage(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .map(command -> {
                    try {
                        return (T) Objects.requireNonNull(command).getDeclaredConstructors()[0].newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        // ignore
                        return null;
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // ignore
        }
        return null;
    }
}
