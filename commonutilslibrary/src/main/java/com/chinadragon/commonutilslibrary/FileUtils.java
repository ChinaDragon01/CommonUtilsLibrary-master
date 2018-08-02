package com.chinadragon.commonutilslibrary;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/6/29 14:55
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class FileUtils {
    public static final String IMG_PREFIX = "IMG";
    public static final String VIDEO_PREFIX = "VIDEO";
    public static final String PDF_PREFIX = "PDF";
    public static final String IMG_POSTFIX = ".jpg";
    public static final String VIDEO_POSTFIX = ".mp4";
    public static final String PDF_POSTFIX = ".pdf";
    public static final String POSTFIX = ".jpg";
    public static final String IMG_NAME = "DCIM";
    public static final String DIRECTORY_NAME = "commonutils";
    public static String SAVE_DIRECTORY_PATh = DIRECTORY_NAME + File.separator;
    public static final String[] FOLDER_NAME = {"imges", "videos", "pdf"};
    public static final String[] FOLDER_ZH_NAME = {"图片", "视频", "文档"};

    public static File createCameraFile(Context context) {
        return createMediaFile(context, null);
    }

    public static File createCropFile(Context context) {
        return createMediaFile(context, null);
    }

    private static File createMediaFile(Context context, String parentPath) {
        String state = Environment.getExternalStorageState();
        File rootDir = state.equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) : context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File folderDir = new File(rootDir.getAbsolutePath());
        if (!folderDir.exists() && folderDir.mkdirs()) {

        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String fileName = IMG_NAME + "_" + timeStamp + "";
        File tmpFile = new File(folderDir, fileName + POSTFIX);
        return tmpFile;
    }

    public static final String[] VIDEO_EXTENSIONS = {"3gp", "amv",
            "avb", "avd", "avi", "flh", "fli", "flv", "flx", "gvi", "gvp",
            "hdmov", "hkm", "ifo", "imovi", "imovi", "iva", "ivf", "ivr",
            "m4v", "m75", "meta", "mgv", "mj2", "mjp", "mjpg", "mkv", "mmv",
            "mnv", "mod", "modd", "moff", "moi", "moov", "mov", "movie",
            "mp21", "mp21", "mp2v", "mp4", "mp4v", "mpe", "mpeg", "mpeg4",
            "mpf", "mpg", "mpg2", "mpgin", "mpl", "mpls", "mpv", "mpv2", "mqv",
            "msdvd", "msh", "mswmm", "mts", "mtv", "mvb", "mvc", "mvd", "mve",
            "mvp", "mxf", "mys", "ncor", "nsv", "nvc", "ogm", "ogv", "ogx",
            "osp", "par", "pds", "pgi", "piv", "playlist", "pmf", "prel",
            "pro", "prproj", "psh", "pva", "pvr", "pxv", "qt", "qtch", "qtl",
            "qtm", "qtz", "rcproject", "rdb", "rec", "rm", "rmd", "rmp", "rms",
            "rmvb", "roq", "rp", "rts", "rts", "rum", "rv", "sbk", "sbt",
            "scm", "scm", "scn", "sec", "seq", "sfvidcap", "smil", "smk",
            "sml", "smv", "spl", "ssm", "str", "stx", "svi", "swf", "swi",
            "swt", "tda3mt", "tivo", "tix", "tod", "tp", "tp0", "tpd", "tpr",
            "trp", "ts", "tvs", "vc1", "vcr", "vcv", "vdo", "vdr", "veg",
            "vem", "vf", "vfw", "vfz", "vgz", "vid", "viewlet", "viv", "vivo",
            "wma"
    };
    //集合放置所有支持视频格式
    private static final List<String> listvideo = new ArrayList<>(
            Arrays.asList(VIDEO_EXTENSIONS));

    /**
     * 检测是否是视频文件
     */
    public static boolean isVideo(String path) {
        path = getFileExtension(path);
        return listvideo.contains(path);
    }

    /**
     * 获取文件后缀名
     */
    public static String getFileExtension(String path) {
        if (null != path) {
            // 后缀点 的位置
            int dex = path.lastIndexOf(".");
            // 截取后缀名
            return path.substring(dex + 1);
        }
        return null;
    }

    public static List<File> getFileListByDirPath(String path, String[] fileTypes) {
        if (null == path || null == fileTypes) {
            return new ArrayList<File>();
        }
        File directory = new File(path);
        FileFilterUtils fileFilterUtils = new FileFilterUtils(fileTypes);
        File[] files = directory.listFiles(fileFilterUtils);

        if (files == null) {
            return new ArrayList<File>();
        }

        List<File> result = Arrays.asList(files);
        Collections.sort(result, new FileComparator());
        return result;
    }

    public static String cutLastSegmentOfPath(String path) {
        return path.substring(0, path.lastIndexOf("/"));
    }

    /**
     * 获取文件大小
     *
     * @param size
     * @return
     */
    public static String getReadableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    /**
     * 创建文件名
     *
     * @param fileType 0：图片；1：视频；2：PDF文件
     * @return
     */
    public static File createFile(int fileType) {
        return getFile(fileType, null);
    }

    /**
     * 创建文件名
     *
     * @param fileType 0：图片；1：视频；2：PDF文件
     * @param fileId
     * @return
     */
    public static File createFile(int fileType, String fileId) {
        return getFile(fileType, fileId);
    }

    @NonNull
    private static File getFile(int fileType, String fileId) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String fileName = null;
        if (0 == fileType) {
            fileName = null == fileId ? IMG_PREFIX + "_" + timeStamp + IMG_POSTFIX : IMG_PREFIX + "_" + timeStamp + "_" + fileId + IMG_POSTFIX;
        } else if (1 == fileType) {
            fileName = null == fileId ? VIDEO_PREFIX + "_" + timeStamp + VIDEO_POSTFIX : VIDEO_PREFIX + "_" + timeStamp + "_" + fileId + VIDEO_POSTFIX;
        } else if (2 == fileType) {
            fileName = null == fileId ? PDF_PREFIX + "_" + timeStamp + PDF_POSTFIX : PDF_PREFIX + "_" + timeStamp + "_" + fileId + PDF_POSTFIX;
        }

        File dir = null;
        try {
            dir = makeDirs(FileUtils.SAVE_DIRECTORY_PATh + FileUtils.FOLDER_NAME[fileType]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            LogUtil.i("FileUtils 创建文件目录出现异常：" + e.toString());
        }
        File tmpFile = new File(dir, fileName);
        return tmpFile;
    }

    /**
     * 创建文件夹
     * 注意：使用的是 file.mkdirs();
     * 如果需要创建文件夹没有多级目录，请使用file.mkdir();
     *
     * @param directoryName
     */
    public static File makeDirs(String directoryName) {
        if (!TextUtils.isEmpty(directoryName)) {
            File fileDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + directoryName);
            if (!fileDirectory.exists() && !fileDirectory.isDirectory()) {
                fileDirectory.mkdirs();
            }
            return fileDirectory;
        }
        return null;
    }

}
