package org.fox.agent.monitor.os;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarLoader;

import java.io.*;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 10:20
 */
public class SigarNativeBindingLoader {
    /**
     * This system property tells sigar where to find the native bindings
     */
    private static final String SIGAR_PATH = "org.hyperic.sigar.path";

    /**
     * This is the directory that contains the native bindings
     */
    private static final String SIGAR_RESOURCE_DIR = "/sigar/";

    private SigarNativeBindingLoader() {
    }

    /**
     * Extracts the native bindings for sigar and tells sigar where to find them.
     *
     * @return the folder that contains the extracted nativeLibs
     * @throws Exception
     */
    public static String loadNativeSigarBindings() throws Exception {
        final File tempDirectory = new File(System.getProperty("java.io.tmpdir"), "sigar");
        tempDirectory.mkdir();
        return loadNativeSigarBindings(tempDirectory);
    }

    /**
     * Extracts the native bindings for sigar and tells sigar where to find them.
     *
     * @param nativeLibDir the directory the native libs should be stored to
     * @return the folder that contains the extracted nativeLibs
     * @throws Exception
     */
    private static String loadNativeSigarBindings(File nativeLibDir) throws Exception {
        extractFromJarToTemp(SIGAR_RESOURCE_DIR + new SigarLoader(Sigar.class).getLibraryName(), nativeLibDir);
        String path = nativeLibDir.getAbsolutePath();
        //set sigar lib path
        System.setProperty(SIGAR_PATH, path);
        return path;
    }

    /**
     * Loads library from current JAR archive
     * <p/>
     * The file from JAR is copied into system temporary directory and then loaded. The temporary file is deleted after exiting.
     * Method uses String as filename because the pathname is "abstract", not system-dependent.
     *
     * @param path          The filename inside JAR as absolute path (beginning with '/'), e.g. /package/File.ext
     * @param tempDirectory
     * @throws IOException              If temporary file creation or read/write operation fails
     * @throws IllegalArgumentException If source file (param path) does not exist
     * @throws IllegalArgumentException If the path is not absolute or if the filename is shorter than three characters (restriction of {@see File#createTempFile(java.lang.String, java.lang.String)}).
     */
    public static void extractFromJarToTemp(String path, File tempDirectory) throws IOException {
        File temp = createTempFile(path, tempDirectory);
        if (temp == null) {
            // already extracted
            return;
        }
        writeFromJarToTempFile(path, temp);
    }

    private static File createTempFile(String path, File tempDirectory) throws IOException {
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }

        // Obtain filename from path
        String[] parts = path.split("/");

        // Prepare temporary file
        File temp = new File(tempDirectory, parts[parts.length - 1]);
        if (temp.exists()) {
            // already extracted
            return null;
        }
        if (!temp.createNewFile()) {
            throw new IOException("Could not create new File");
        }

        if (!temp.exists()) {
            throw new FileNotFoundException("File " + temp.getAbsolutePath() + " does not exist.");
        }
        return temp;
    }

    private static void writeFromJarToTempFile(String path, File temp) throws IOException {
        // Prepare buffer for data copying
        byte[] buffer = new byte[1024];
        int readBytes;

        // Open and check input stream
        InputStream is = SigarNativeBindingLoader.class.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }

        // Open output stream and copy data between source file in JAR and the temporary file
        OutputStream os = new FileOutputStream(temp);
        try {
            while ((readBytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, readBytes);
            }
        } finally {
            // If read/write fails, close streams safely before throwing an exception
            os.close();
            is.close();
        }
    }
}
