package app.sean.baseandroid.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.File;


/**
 * 图片压缩
 * Created by sean on 16/12/8.
 */

public class BitmapCompressHelper {
    private final int DEFAULT_WITH = 1080;
    private final int DEFAULT_HEIGHT = 1920;
    private CompressSize size;

    public BitmapCompressHelper() {
        size = new CompressSize( DEFAULT_HEIGHT, DEFAULT_WITH );
    }

    public static class CompressSize {
        private int with;
        private int height;

        public CompressSize( int height, int with ) {
            this.height = height;
            this.with = with;
        }

        public void setHeight( int height ) {
            this.height = height;
        }

        public void setWith( int with ) {
            this.with = with;
        }

        public int getHeight() {
            return height;
        }

        public int getWith() {
            return with;
        }

        public int getMinSideLength() {
            return Math.min( height, with );
        }

        public int getSize() {
            return with * height;
        }
    }

    public void setCompressSize( CompressSize compressSize ) {
        this.size = compressSize;
    }


    /**
     * @param dstFile
     */
    public Bitmap formFile( File dstFile ) {
        if( !dstFile.exists( ) ) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options( );
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile( dstFile.getPath( ), options );
        options.inSampleSize = computeSampleSize( options, size.getMinSideLength( ),
                size.getSize( ) );
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile( dstFile.getPath( ), options );
    }

    /**
     * @param filePath
     */
    public Bitmap formFilePath( String filePath ) {
        if( TextUtils.isEmpty( filePath ) ) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options( );
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile( filePath, options );
        options.inSampleSize = computeSampleSize( options, size.getMinSideLength( ),
                size.getSize( ) );
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile( filePath, options );
    }

    /**
     * @param bitmap
     */
    public Bitmap formBitmap( Bitmap bitmap ) {
        if( bitmap == null || bitmap.isRecycled( ) ) {
            return bitmap;
        }
        //转化为2进制流
        byte[] bytes = BitmapHelper.bitmapToByte( bitmap );
        //释放图片
        BitmapHelper.recycleBitmap( bitmap );

        BitmapFactory.Options options = new BitmapFactory.Options( );
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray( bytes, 0, bytes.length, options );

        options.inSampleSize = computeSampleSize( options, size.getMinSideLength( ),
                size.getSize( ) );
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray( bytes, 0, bytes.length, options );
    }


    /**
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public int computeSampleSize( BitmapFactory.Options options, int minSideLength,
            int maxNumOfPixels ) {
        int initialSize = computeInitialSampleSize( options, minSideLength, maxNumOfPixels );
        int roundedSize;
        if( initialSize <= 8 ) {
            roundedSize = 1;
            while( roundedSize < initialSize ) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = ( initialSize + 7 ) / 8 * 8;
        }
        return roundedSize;
    }

    /**
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    private int computeInitialSampleSize( BitmapFactory.Options options, int minSideLength,
            int maxNumOfPixels ) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = ( maxNumOfPixels == -1 ) ? 1 : (int) Math.ceil(
                Math.sqrt( w * h / maxNumOfPixels ) );
        int upperBound = ( minSideLength == -1 ) ? 128 : (int) Math.min(
                Math.floor( w / minSideLength ), Math.floor( h / minSideLength ) );
        if( upperBound < lowerBound ) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if( ( maxNumOfPixels == -1 ) && ( minSideLength == -1 ) ) {
            return 1;
        } else if( minSideLength == -1 ) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

}
