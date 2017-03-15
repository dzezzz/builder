package com.builder.services;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import com.builder.exceptions.BuilderException;

/**
 * @author marcinjaz
 */
public class ReadClassService {

	public Class<?> loadClass(String content, String className) throws ClassNotFoundException, MalformedURLException {

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		out.println(content);
		out.close();
		JavaFileObject file = new JavaSourceFromString(className, writer.toString());

		Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
		JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

		//TODO MJ should be logging, remove then
		boolean success = task.call();
		for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
			System.out.println(diagnostic.getCode());
			System.out.println(diagnostic.getKind());
			System.out.println(diagnostic.getPosition());
			System.out.println(diagnostic.getStartPosition());
			System.out.println(diagnostic.getEndPosition());
			System.out.println(diagnostic.getSource());
			System.out.println(diagnostic.getMessage(null));

		}
		System.out.println("Success: " + success);

		if (success) {
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File("").toURI().toURL()});
			return Class.forName(className, true, classLoader);
		}
		throw new BuilderException("Cannot compile and load the class.");
	}

	private class JavaSourceFromString extends SimpleJavaFileObject {
		final String code;

		JavaSourceFromString(String name, String code) {
			super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			this.code = code;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return code;
		}
	}
}
