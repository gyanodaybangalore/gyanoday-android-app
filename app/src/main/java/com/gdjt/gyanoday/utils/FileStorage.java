package com.gdjt.gyanoday.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStorage {

    public static FileStorage smFileStorage;
    public static FileStorage getInstance(){
        if(smFileStorage == null){
            smFileStorage = new FileStorage();
        }

        return smFileStorage;
    }

    public static final String FILE_SEPARATOR = File.separator;
    private static boolean externalStorageReadable, externalStorageWritable;

    public static boolean isExternalStorageReadable() {
        checkStorage();
        return externalStorageReadable;
    }

    public static boolean isExternalStorageWritable() {
        checkStorage();
        return externalStorageWritable;
    }

    public static boolean isExternalStorageReadableAndWritable() {
        checkStorage();
        return externalStorageReadable && externalStorageWritable;
    }

    private static void checkStorage() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            externalStorageReadable = externalStorageWritable = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            externalStorageReadable = true;
            externalStorageWritable = false;
        } else {
            externalStorageReadable = externalStorageWritable = false;
        }
    }

    public static String getRootFilePath(Context aContext, String aRootFolderName){
        String rootFolder = aRootFolderName;
        String rootFilePath = Environment.getExternalStorageDirectory() +
                File.separator + rootFolder;;
        if(!FileStorage.isDirectoryAvailable(rootFolder)){
            FileStorage.getInstance().createDirectoryInStorage(rootFolder);
        }
        return rootFilePath;
    }


    public static String getDictionaryPath(String aDirectoryName){
        String rootFilePath = Environment.getExternalStorageDirectory() +
                File.separator + aDirectoryName;;
        if(!FileStorage.isDirectoryAvailable(aDirectoryName)){
            FileStorage.getInstance().createDirectoryInStorage(aDirectoryName);
        }
        return rootFilePath;
    }


    public static boolean isFileExist(String aRootPath, String aFileName){
        File f = new File(aRootPath + FILE_SEPARATOR + aFileName);
        return  f.exists();
    }

    public static  boolean isDirectoryAvailable(String aDirName){
        File f = new File(Environment.getExternalStorageDirectory() + FILE_SEPARATOR + aDirName);
        return  f.exists();
    }

    public boolean createDirectoryInStorage(String aDirName){
        boolean success = false;
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + aDirName);
        if (!folder.exists()) {
            success = folder.mkdirs();
        }else{
            success = true;
        }
        return  success;
    }

    private boolean deleteDirectoryImpl(String path) {
        File directory = new File(path);
        // If the directory exists then delete
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files == null) {
                return true;
            }
            // Run on all sub files and folders and delete them
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectoryImpl(files[i].getAbsolutePath());
                } else {
                    files[i].delete();
                }
            }
        }
        return directory.delete();
    }

    public boolean createFile(String aBasePath, String directoryName, String fileName, String content) {
        return createFile(aBasePath, directoryName, fileName, content.getBytes());
    }


    private String getBaseBuildPath(String aBasePath, String aDirectoryName, String aFileName){
        return aBasePath + FILE_SEPARATOR + aDirectoryName + FILE_SEPARATOR + aFileName;
    }

    public boolean createFile(String aBasePath, String directoryName, String fileName, byte[] content) {
        String path = getBaseBuildPath(aBasePath, directoryName, fileName);
        try {
            OutputStream stream = new FileOutputStream(new File(path));
            stream.write(content);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create", e);
        }
        return true;
    }

    public boolean createFile(String aBasePath, String directoryName, String fileName, Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return createFile(aBasePath, directoryName, fileName, byteArray);
    }

    public boolean deleteFile(String aBasePath, String directoryName, String fileName) {
        String path = getBaseBuildPath(aBasePath, directoryName, fileName);
        File file = new File(path);
        return file.delete();
    }

    public boolean isFileExist(String aBasePath, String directoryName, String fileName) {
        String path = getBaseBuildPath(aBasePath, directoryName, fileName);
        return new File(path).exists();
    }

    public byte[] readFile(String aBasePath, String directoryName, String fileName) {
        String path = getBaseBuildPath(aBasePath, directoryName, fileName);
        return readFile(new File(path));
    }

    private byte[] readFile(final File aFile) {
        final FileInputStream stream;
        int size = (int) aFile.length();
        byte[] bytes = new byte[size];
        try {
            stream = new FileInputStream(aFile);
            BufferedInputStream buf = new BufferedInputStream(stream);
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public String readTextFile(String aBasePath, String directoryName, String fileName) {
        byte[] bytes = readFile(aBasePath, directoryName, fileName);
        String content = new String(bytes);
        return content;
    }

    public void appendFile(String aBasePath, String directoryName, String fileName, String content) {
        appendFile(aBasePath, directoryName, fileName, content.getBytes());
    }

    public void appendFile(String aBasePath, String directoryName, String fileName, byte[] bytes) {
        if (!isFileExist(aBasePath, directoryName, fileName)) {
            throw new RuntimeException("Impossible to append content, because such file doesn't exist");
        }

        try {
            String path = getBaseBuildPath(aBasePath, directoryName, fileName);
            FileOutputStream stream = new FileOutputStream(new File(path), true);
            stream.write(bytes);
            stream.write(System.getProperty("line.separator").getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to append content to file", e);
        }
    }

    public List<File> getNestedFiles(String aBasePath, String directoryName) {
        String buildPath = aBasePath + FILE_SEPARATOR + directoryName;
        File file = new File(buildPath);
        List<File> out = new ArrayList<File>();
        getDirectoryFilesImpl(file, out);
        return out;
    }

    private void getDirectoryFilesImpl(File directory, List<File> out) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files == null) {
                return;
            } else {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        getDirectoryFilesImpl(files[i], out);
                    } else {
                        out.add(files[i]);
                    }
                }
            }
        }
    }



    public List<File> getFiles(String aBasePath, String directoryName, final String matchRegex) {
        String buildPath = aBasePath  + FILE_SEPARATOR + directoryName;
        File file = new File(buildPath);
        List<File> out = null;
        if (matchRegex != null) {
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String fileName) {
                    if (fileName.matches(matchRegex)) {
                        return true;
                    }
                    return false;
                }
            };
            out = Arrays.asList(file.listFiles(filter));
        } else {
            out = Arrays.asList(file.listFiles());
        }
        return out;
    }

    public File getFile(String aBasePath, String name) {
        String path = aBasePath + FILE_SEPARATOR + name;
        File file = new File(path);
        return file;
    }

    public File getFile(String aBasePath, String directoryName, String fileName) {
        String path = getBaseBuildPath(aBasePath, directoryName, fileName);
        return new File(path);
    }

    public void rename(File file, String newName) {
        String name = file.getName();
        String newFullName = file.getAbsolutePath().replaceAll(name, newName);
        File newFile = new File(newFullName);
        file.renameTo(newFile);
    }


    public static String readTextFromSD(String aRootPath, String aFileName){
        File file = new File(aRootPath, aFileName);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static ArrayList<String> getAllFilesNameList(String aPath){
        ArrayList<String> fileNameList = new ArrayList<>();
        File directory = new File(aPath);
        File[] files = directory.listFiles();
        if(files != null && files.length > 0){
            for (File file : files) {
                fileNameList.add(file.getName());
            }
        }
        return fileNameList;
    }

    public static void deleteFile(String aPath){
        try{
            File file = new File(aPath);
            deleteRecursive(file);
        }catch (Exception e){

        }

    }

    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }


}
