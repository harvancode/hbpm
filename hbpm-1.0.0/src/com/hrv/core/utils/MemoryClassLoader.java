package com.hrv.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class MemoryClassLoader {

	private LoaderImpl impl;
	private List<String> classList = new ArrayList<String>();

	private class LoaderImpl extends ClassLoader {

		// The compiler tool
		private final JavaCompiler compiler = ToolProvider
				.getSystemJavaCompiler();

		// Compiler options
		private final Iterable<String> options = Arrays.asList("-verbose",
				"-classpath", getClassPath());

		// DiagnosticCollector, for collecting compilation problems
		private final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

		// Our FileManager
		private final MemoryFileManager manager = new MemoryFileManager(
				this.compiler);

		public LoaderImpl() {

		}

		public LoaderImpl(File sourceDirectory) {

			List<Source> list = new ArrayList<Source>();

			File[] files = sourceDirectory.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {

					return name.endsWith(Kind.SOURCE.extension);
				}
			});

			for (File file : files) {
				list.add(new Source(file));
			}

			CompilationTask task = compiler.getTask(null, manager, diagnostics,
					options, null, list);
			Boolean compilationSuccessful = task.call();

			// report on all errors to screen
			for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics
					.getDiagnostics()) {
			}
			classList = manager.getClassList();
		}

		public LoaderImpl loaderImplOneFile(File file) {
			List<Source> list = new ArrayList<Source>();
			list.add(new Source(file));

			CompilationTask task = compiler.getTask(null, manager, diagnostics,
					options, null, list);
			Boolean compilationSuccessful = task.call();

			// report on all errors to screen
			for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics
					.getDiagnostics()) {
			}

			classList = manager.getClassList();
			return this;
		}

		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			// synchronized (this.manager) {
			// Output output = manager.map.remove(name);
			// if (output != null) {
			// byte[] array = output.toByteArray();
			// return defineClass(name, array, 0, array.length);
			// }
			// }
			// return super.findClass(name);

			// edit
			return null;
		}

	}

	private String getClassPath() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		URL[] urls = ((URLClassLoader) classLoader).getURLs();

		StringBuilder buf = new StringBuilder(1000);
		buf.append(".");
		String separator = System.getProperty("path.separator");
		for (URL url : urls) {
			buf.append(separator).append(url.getFile());
		}

		return buf.toString();
	}

	// @Override
	public List<String> run(File file) {
		impl = new LoaderImpl(file);

		return classList;
	}

	public List<String> runOneFile(File file) {
		impl = new LoaderImpl().loaderImplOneFile(file);
		return classList;
		// return impl;
	}

	public void runOneSourceCode(String code) {

	}

	public LoaderImpl getLoaderImpl() {
		return impl;
	}
}

class MemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {

	private final Map<String, Output> map = new HashMap<String, Output>();
	List<String> classList = new ArrayList<String>();

	MemoryFileManager(JavaCompiler compiler) {
		super(compiler.getStandardFileManager(null, null, null));
	}

	public List<String> getClassList() {
		return classList;
	}

	@Override
	public Output getJavaFileForOutput(Location location, String name,
			Kind kind, FileObject source) {
		Output output = new Output(name, kind);
		map.put(name, output);
		classList.add(output.getCurrentClassName());

		return output;
	}

}

class Output extends SimpleJavaFileObject {

	private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private String currentClassName;

	Output(String name, Kind kind) {
		super(URI.create("memo:///" + name.replace('.', '/') + kind.extension),
				kind);
		setCurrentClassName(name);
	}

	public String getCurrentClassName() {
		return currentClassName;
	}

	private void setCurrentClassName(String currentClassName) {
		this.currentClassName = currentClassName;
	}

	byte[] toByteArray() {
		return this.baos.toByteArray();
	}

	@Override
	public ByteArrayOutputStream openOutputStream() {
		return this.baos;
	}
}

class Source extends SimpleJavaFileObject {

	public Source(File file) {
		super(file.toURI(), Kind.SOURCE);
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {

		StringBuilder sb = new StringBuilder("");
		try {
			File file = new File(uri);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			sb = new StringBuilder((int) file.length());
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}
}