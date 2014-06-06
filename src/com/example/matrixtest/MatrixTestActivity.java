package com.example.matrixtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MatrixTestActivity extends Activity implements OnTouchListener {

	private TransformMatrixView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		view = new TransformMatrixView(this);
		view.setScaleType(ImageView.ScaleType.MATRIX);
		view.setOnTouchListener(this);

		setContentView(view);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Matrix matrix = new Matrix();

			// 平移
//			matrix.postTranslate(view.getImageBitmap().getWidth(), view
//					.getImageBitmap().getHeight());
			
			
			//旋转  围绕图像中心点
//			matrix.setRotate(45f, view.getImageBitmap().getWidth() / 2, view.getImageBitmap().getHeight() / 2);
//			matrix.postTranslate(view.getImageBitmap().getWidth() * 1.5f,  0f);
			
			//旋转 围绕坐标原点
//			matrix.setRotate(45f);
//			matrix.postTranslate(view.getImageBitmap().getWidth() * 1.5f,  0f);
			
			
			//缩放
			//matrix.setScale(2f, 2f);
			
			
			//对称
			float matrix_values[] = {1f, 0f, 0f, 0f, -1f, 0f,0f,0f, 1f};
			matrix.setValues(matrix_values);
			matrix.postTranslate(0f, view.getImageBitmap().getHeight());
			view.setImageMatrix(matrix);

			// 下面的代码是为了查看matrix中的元素
			float[] matrixValues = new float[9];
			matrix.getValues(matrixValues);
			for (int i = 0; i < 3; ++i) {
				String temp = new String();
				for (int j = 0; j < 3; ++j) {
					temp += matrixValues[3 * i + j] + "    ";
				}
				Log.e("TestTransformMatrixActivity", temp);
			}
			
			view.invalidate();

		}
		return true;
	}

	class TransformMatrixView extends ImageView {
		private Bitmap bitmap;
		private Matrix matrix;

		public TransformMatrixView(Context context) {
			super(context);
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.test);
			matrix = new Matrix();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawBitmap(bitmap, 0, 0, null);

			canvas.drawBitmap(bitmap, matrix, null);
			super.onDraw(canvas);
		}

		@Override
		public void setImageMatrix(Matrix matrix) {
			this.matrix.set(matrix);
			super.setImageMatrix(matrix);
		}

		public Bitmap getImageBitmap() {
			return bitmap;
		}

	}
}
