package com.learn.csa.course_selling_app.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Utilities {

    public static boolean deleteFile(String path) {

        boolean f = false;
        try {
            File file = new File(path);
            f = file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public static boolean saveFile(InputStream is, String path) {

        boolean f = false;
        try {
            byte[] b = new byte[is.available()];
            is.read(b);

            // Convert backslashes to forward slashes for better cross-platform compatibility
            path = path.replace("\\", "/");

            // Create the 'course_pics' directory if it doesn't exist
            File directory = new File(path.substring(0, path.lastIndexOf('/')));
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(path);
            fos.write(b);

            fos.flush();
            fos.close();

            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;

    }

}
