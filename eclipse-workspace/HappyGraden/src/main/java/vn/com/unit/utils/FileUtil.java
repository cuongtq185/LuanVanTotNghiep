/*******************************************************************************
 * Class        FileUtils
 * Created date 2016/06/01
 * Lasted date  2016/06/01
 * Author       KhoaNA
 * Change log   2016/06/0101-00 KhoaNA create a new
 ******************************************************************************/
package vn.com.unit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * FileUtils
 * 
 */
public class FileUtil {
    
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
    /**
     * Get fileName and fileExtension from fileName
     * 
     * @param fileName
     *            String
     * @return String[]
     */
    public static String[] getFileNameAndFileExtension(String fileNameFull) {
        String[] result = null;
        
        if ( StringUtils.isNotEmpty(fileNameFull) ) {
            int midIndex = fileNameFull.lastIndexOf('.');
            if( midIndex != -1  ) {
                result = new String[2];
                
                midIndex = midIndex + 1;
                int endIndex = fileNameFull.length();

                // Get fileName
                result[0] = fileNameFull.substring(0, midIndex);
                // Get extension
                result[1] = fileNameFull.substring(midIndex, endIndex)
                                    .toLowerCase();
            } else {
                result = new String[2];
                
                // Get fileName
                result[0] = fileNameFull;
                // Get extension
                result[1] = null;
            }
        }
        
        return result;
    }
    
    public static String getFileExtension(String fileNameFull) {
        String result = null;
        
        if ( StringUtils.isNotEmpty(fileNameFull) ) {
            int beginIndex = fileNameFull.lastIndexOf('.');
            
            if( beginIndex != -1 ) {
                beginIndex = beginIndex + 1;
                int endIndex = fileNameFull.length();

                result = fileNameFull.substring(beginIndex, endIndex)
                                    .toLowerCase();
            }
        }
        
        return result;
    }
    
    /**
     * Move file with new file name from folderSource to folderTarget
     * 
     * @param fileName
     *            String
     * @param folderSource
     *            String
     * @param newfileName
     *            String
     * @param folderTarget
     *            String
     */
//    public static void moveFile(String fileName, String folderSource,
//            String newFileName, String folderTarget) throws IOException {
//            File source = new File(folderSource + fileName);
//            File target = new File(folderTarget + newFileName);
//            FileUtil.setPermission(target);
//            try {
//                FileUtils.copyFile(source, target);
//                if(!source.delete()){
//                    logger.error("source not delete");
//                }
//            } catch (IOException e) {
//                StringBuilder msgError = new StringBuilder("IOException:");
//                msgError.append("Can not move file ");
//                msgError.append(fileName);
//                msgError.append(" from folderSource ");
//                msgError.append(folderSource);
//                msgError.append(" to folderTarget ");
//                msgError.append(folderTarget);
//                msgError.append(":");
//                throw new SystemException(msgError.toString()+e.toString());
//            }
//    }
    
    /**
     * Move file from folderSource to folderTarget
     * 
     * @param fileName
     *            String
     * @param folderSource
     *            String
     * @param folderTarget
     *            String
     */
//    public static void moveFile(String fileName, String folderSource,
//            String folderTarget) throws IOException {
//        moveFile(fileName, folderSource, fileName, folderTarget);
//    }
    
    /**
     * Get contentType file
     * 
     * @param fileNameFull

     */
    public static String getContentType(String fileNameFull) {
        String contentType = "application/octet-stream";
        
        String extensionFile = getFileExtension(fileNameFull);
        
        if( StringUtils.isNotEmpty(extensionFile) ) {
            if (extensionFile.equals("pdf"))
                contentType = "application/pdf";
            else if (extensionFile.equals("txt"))
                contentType = "text/plain";
            else if (extensionFile.equals("exe"))
                contentType = "application/octet-stream";
            else if (extensionFile.equals("zip"))
                contentType = "application/zip";
            else if (extensionFile.equals("doc"))
                contentType = "application/msword";
            else if (extensionFile.equals("xls"))
                contentType = "application/vnd.ms-excel";
            else if (extensionFile.equals("xlsx"))
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            else if (extensionFile.equals("ppt"))
                contentType = "application/vnd.ms-powerpoint";
            else if (extensionFile.equals("gif"))
                contentType = "image/gif";
            else if (extensionFile.equals("png"))
                contentType = "image/png";
            else if (extensionFile.equals("jpeg"))
                contentType = "image/jpeg";
            else if (extensionFile.equals("jpg"))
                contentType = "image/jpeg";
            else if (extensionFile.equals("mp3"))
                contentType = "audio/mpeg";
            else if (extensionFile.equals("wav"))
                contentType = "audio/x-wav";
            else if (extensionFile.equals("mpeg"))
                contentType = "video/mpeg";
            else if (extensionFile.equals("mpg"))
                contentType = "video/mpeg";
            else if (extensionFile.equals("mpe"))
                contentType = "video/mpeg";
            else if (extensionFile.equals("mov"))
                contentType = "video/quicktime";
            else if (extensionFile.equals("avi"))
                contentType = "video/x-msvideo";
            else if (extensionFile.equals("flv"))
                contentType = "video/flv";
        }

        return contentType;
    }
    
    public static void setPermission(File file) throws IOException{
    	if(!file.setReadable(true, false)){
    		logger.error("file not set Readable");
    	}
    	if(!file.setWritable(true, false)){
    		logger.error("file not set Writable");
    	}
    	if(!file.setExecutable(true, false)){
    		logger.error("file not set Executable");
    	}
    }
    
//    public static void setPermission777(Path path) {
//        try {
//            if (OsCheck.getSystemType() == OsCheck.OSType.Linux) {
//                Set<PosixFilePermission> perms = new HashSet<>();
//                // owner
//                perms.add(PosixFilePermission.OWNER_READ);
//                perms.add(PosixFilePermission.OWNER_WRITE);
//                perms.add(PosixFilePermission.OWNER_EXECUTE);
//
//                // group
//                perms.add(PosixFilePermission.GROUP_READ);
//                perms.add(PosixFilePermission.GROUP_WRITE);
//                perms.add(PosixFilePermission.GROUP_EXECUTE);
//                
//                // other
//                perms.add(PosixFilePermission.OTHERS_READ);
//                perms.add(PosixFilePermission.OTHERS_WRITE);
//                perms.add(PosixFilePermission.OTHERS_EXECUTE);
//                
//                Files.setPosixFilePermissions(path, perms);
//            }
//        } catch (IOException e) {
//            logger.error("##setPermission777##", e);
//        }
//        
//    }
//    
    /**
     * Delete file by filePath
     * 
     * @param filePath
     *         type String
     * @return
     */
    public static void deleteFile(String filePath) {
        File source = new File(filePath);
        source.deleteOnExit();
    }
    
    /**
     * Check file existed
     * 
     * @param filePath
     *         type String
     * @return boolean
     */
    public static boolean isFileExisted(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
    
    /**
     * Create directory if path not exists
     *
     * @param path
     *             type String
     * @throws IOException 
     */
    public static void createDirectoryNotExists(String path) throws IOException {
        // file path
        File file = new File(FilenameUtils.separatorsToSystem(path));
        // not exists directory
        if (!file.exists()) {
            // create directory
            setPermission(file);
            file.mkdirs();
        }
    }
    
    /**
     * 
     * Wire sxssfWorkbook to file and save file to folder
     * @param folder
     * @param fileNamePath
     * @param sxssfWorkbook
     * @throws IOException
     */
//    public static void exportToFile(String folder, Path fileNamePath, SXSSFWorkbook sxssfWorkbook) throws IOException {
//        // folderPath
//        Path folderPath = Paths.get(folder);
//        if (!Files.exists(folderPath)) {
//            Files.createDirectories(folderPath);
//
//            FileUtil.setPermission777(folderPath);
//        }
//
//        if (Files.exists(fileNamePath)) {
//            Files.delete(fileNamePath);
//        }

        // outputStream
//        OutputStream outputStream = new FileOutputStream(fileNamePath.toString());
//        sxssfWorkbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();
//        FileUtil.setPermission777(fileNamePath);
//    }
}
