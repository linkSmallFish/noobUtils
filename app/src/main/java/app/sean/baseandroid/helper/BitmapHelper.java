package app.sean.baseandroid.helper;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * bitmap工具类
 * Created by sean on 16/12/7.
 */
public class BitmapHelper {

    /**
     * 将图片转化成二进制流
     *
     * @param bitmap 图片
     * @return 转化成的二进制流
     */
    public static byte[] bitmapToByte( Bitmap bitmap ) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        bitmap.compress( Bitmap.CompressFormat.PNG, 100, outputStream );
        return outputStream.toByteArray( );
    }

    /**
     * 释放bitmap
     *
     * @param bitmap 图片
     */
    public static void recycleBitmap( Bitmap bitmap ) {
        if( bitmap != null && !bitmap.isRecycled( ) ) {
            bitmap.recycle( );
            System.gc( );
        }
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree( String path ) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface( path );
            int orientation = exifInterface.getAttributeInt( ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL );
            switch( orientation ) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        }
        catch( IOException e ) {
            e.printStackTrace( );
        }
        return degree;
    }

    /*
    * 旋转图片
    * @param angle
    * @param bitmap
    * @return Bitmap
    */
    public static Bitmap rotatingBitmap( int angle, Bitmap bitmap ) {
        //旋转图片 动作
        Matrix matrix = new Matrix( );
        matrix.postRotate( angle );
        // 创建新的图片
        return Bitmap.createBitmap( bitmap, 0, 0, bitmap.getWidth( ), bitmap.getHeight( ), matrix,
                true );
    }


}
