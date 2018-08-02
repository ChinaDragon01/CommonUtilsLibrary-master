package com.chinadragon.commonutilslibrary.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/12/14 9:01
 * Name: 图片压缩工具类
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class ImageUtils {
    /**
     * 对图片质量进行压缩
     *
     * @param bitmap
     * @return
     */
    public static Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        //循环判断如果压缩后图片是否大于50kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 50) {
            // 清空baos
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;//每次都减少10
        }
        //把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //把ByteArrayInputStream数据生成图片
        Bitmap newBitmap = BitmapFactory.decodeStream(bais, null, null);
        return newBitmap;
    }

    /**
     * 对图片尺寸进行压缩
     * <p>
     * 按图片尺寸压缩 参数是bitmap
     *
     * @param bitmap
     * @param pixelW
     * @param pixelH
     * @return
     */
    public static Bitmap compressImageFromBitmap(Bitmap bitmap, int pixelW, int pixelH) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        if (os.toByteArray().length / 1024 > 512) {//判断如果图片大于0.5M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            os.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeStream(is, null, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = computeSampleSize(options, pixelW > pixelH ? pixelW : pixelH, pixelW * pixelH);
        is = new ByteArrayInputStream(os.toByteArray());
        Bitmap newBitmap = BitmapFactory.decodeStream(is, null, options);
        return newBitmap;
    }

    /**
     * 动态计算出图片的inSampleSize
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    /**
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
}
