package br.com.onder.cleanflyingos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Marco on 08/06/2015.
 */
public class PintarTela extends View {

    private static final float  TOUCH_TOLERANCE = 4;
    private static final String DIR_ROOT_NAME = "CF";
    private static final String DIR_SIGNATURE_NAME = "Assinatura";
    public static final String EXTRA_SIGNATURE = "SIGNATURE_EXTRA";
    private static Bitmap mBitmap;

    private int   mWidth;
    private int   mHeight;
    private int   mPaintColor;
    private int   mBoardColor;
    private float mStrokeSize;
    private float mX, mY;
    private boolean mIsEnable = true;
    private boolean mIsSigned = false;

    //private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private final Paint mBitmapPaint;
    private final EmbossMaskFilter mEmboss;
    private String mFullName = null;
    private Paint mPaint2;
    private Rect mBoundsName;
    private Paint mTextPaint;

    public PintarTela(final Context context) {
        super(context);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
        mPaintColor = Color.BLACK;
        mBoardColor = Color.TRANSPARENT;
    }

    public PintarTela(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
        mPaintColor = Color.BLACK;
        mBoardColor = Color.TRANSPARENT;
    }

    public PintarTela(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
        mPaintColor = Color.BLACK;
        mBoardColor = Color.TRANSPARENT;
    }

    @Override
    protected void onSizeChanged(final int width, final int height, final int oldWidth, final int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        mWidth = width;
        mHeight = height;
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        canvas.drawColor(mBoardColor);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        if (mPaint == null) {
            final float dimen = Float.parseFloat(getContext().getString(Integer.parseInt(String.valueOf(R.dimen.text_stroke_signature))).substring(0, 1));
            mStrokeSize = dimen;
            mPaint = createNewPaint(mPaintColor, mStrokeSize);
        }
        canvas.drawPath(mPath, mPaint);

        if(!mIsSigned && mIsEnable) {
            mPaint2 = new Paint();
            mPaint2.setColor(Color.BLACK);
            mPaint2.setTextSize(30f);
            final Rect bounds = new Rect();
            mPaint2.getTextBounds("Assine aqui...", 0, "Assine aqui...".length(), bounds);
            canvas.drawText("Assine aqui...", (mWidth / 2) - bounds.centerX(), (mHeight - 100), mPaint2);
        }

        canvas.drawLine(25, (mHeight - 50), (mWidth - 25), (mHeight - 50), mPaint2);
        if (mBoundsName == null) {
            mBoundsName = new Rect();
            mTextPaint = new Paint();
            final float dimen = Float.parseFloat(getContext().getString(Integer.parseInt(String.valueOf(R.dimen.text_signature))).substring(0, 2));
            if (isInEditMode()) {
                mTextPaint.setTextSize(18);
            } else {
                mTextPaint.setTextSize(dimen);
            }
        }
        if(mFullName != null) {
            mTextPaint.getTextBounds(mFullName, 0, mFullName.length(), mBoundsName);
            canvas.drawText(mFullName, (mWidth/2 - mBoundsName.centerX()), (mHeight - 25), mTextPaint);
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (mIsEnable) {
            draw(new Canvas());

            final float x = event.getX();
            final float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchStart(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchMove(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touchUp();
                    invalidate();
                    mIsSigned = true;
                    break;
                default:
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    private Paint createNewPaint(final int color, final float strokeSize) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeSize);
        return paint;
    }

    private void touchStart(final float x, final float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(final float x, final float y) {
        final float dx = Math.abs(x - mX);
        final float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
        if (mPaint == null) {
            mPaint = createNewPaint(mPaintColor, mStrokeSize);
        }
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
    }

    public void setPaint(final Paint paint) {
        mPaint = paint;
    }

    public void clearBoard() {
        mPath = new Path();
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mIsSigned = false;
        invalidate();
    }

    private static void createDir() {
        final File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath().concat(File.separator).concat(DIR_ROOT_NAME)
                .concat(File.separator).concat(DIR_SIGNATURE_NAME));
        file.mkdirs();
    }
    boolean persistsSignatureAtCache() {
        final boolean canWrite = getContext().getCacheDir().canWrite();
        if (canWrite) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            FileOutputStream fileOutputStream = null;
            File file = null;
            String outputDir = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.PNG, 60, byteArrayOutputStream);

                outputDir = getContext().getCacheDir().getAbsolutePath();
                file = new File(outputDir
                        .concat(File.separator)
                        .concat("signature").concat(".png"));
                file.createNewFile();

                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return canWrite;
    }

    public static boolean deleteTemporarySignature() {
        return false;
    }

    @Deprecated
    public static boolean persistSignatureAtSDCard(String nome) {
        final boolean canWrite = Environment.getExternalStorageDirectory().canWrite();
        if (canWrite) {
            createDir();

            Calendar date = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            FileOutputStream fileOutputStream = null;
            File file = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.PNG, 40, byteArrayOutputStream);

                date = Calendar.getInstance();
                file = new File(Environment
                        .getExternalStorageDirectory()
                        .getAbsolutePath()
                        .concat(File.separator)
                        .concat(DIR_ROOT_NAME)
                        .concat(File.separator)
                        .concat(DIR_SIGNATURE_NAME)
                        .concat(File.separator)
                        .concat(nome)
                        /*
                        .concat(date.get(Calendar.DAY_OF_MONTH) + "-"
                                + date.get(Calendar.MONTH) + "-"
                                + date.get(Calendar.YEAR) + "-"
                                + date.get(Calendar.HOUR_OF_DAY) + "-"
                                + date.get(Calendar.MINUTE) + "-"
                                + date.get(Calendar.MILLISECOND))
                        */
                        .concat(".png"));
                file.createNewFile();

                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return canWrite;
    }

    public boolean temporarySignature() {
        return Environment.getExternalStorageDirectory().canWrite();
    }

    public void setColor(final int mColor) {
        if (mPaint == null) {
            mPaint = createNewPaint(mPaintColor, mStrokeSize);
        }

        this.mPaintColor = mColor;
        mPaint.setColor(mColor);
    }

    public void setBoardColor(final int mColor) {
        if (mPaint == null) {
            mPaint = createNewPaint(mPaintColor, mStrokeSize);
        }

        this.mBoardColor = mColor;
        clearBoard();
    }

    public void setStrokeSize(final float strokeSize) {
        if (mPaint == null) {
            mPaint = createNewPaint(mPaintColor, mStrokeSize);
        }

        this.mStrokeSize = strokeSize;
        mPaint.setStrokeWidth(strokeSize);
    }

    public void setEraseMode(boolean eraseMode) {
        if (mPaint == null) {
            mPaint = createNewPaint(mPaintColor, mStrokeSize);
        }

        if(eraseMode) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        } else {
            mPaint.setXfermode(null);
            mPaint.setColor(mPaintColor);
        }
    }

    public void setEmbossMode(final boolean embossMode) {
        if (mPaint == null) {
            mPaint = createNewPaint(mPaintColor, mStrokeSize);
        }

        if(embossMode) {
            if(mPaint.getMaskFilter() != mEmboss) {
                mPaint.setMaskFilter(mEmboss);
            }
        } else {
            mPaint.setMaskFilter(null);
        }
    }

    public Bitmap getSignature() {
        return mBitmap;
    }

    public void setFullName(final String name) {
        this.mFullName = name;
    }

    public boolean isSigned() {
        return mIsSigned;
    }

    public void disable() {
        mIsEnable = false;
        clearBoard();
    }

    public void enable() {
        mIsEnable = true;
        clearBoard();
    }

}
