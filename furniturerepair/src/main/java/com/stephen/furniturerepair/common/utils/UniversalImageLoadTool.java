package com.stephen.furniturerepair.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.stephen.furniturerepair.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * String imageUri = "http://site.com/image.png"; // from Web String imageUri =
 * "file:///mnt/sdcard/image.png"; // from SD card String imageUri =
 * "content://media/external/audio/albumart/13"; // from content provider String
 * imageUri = "assets://image.png"; // from assets String imageUri =
 * "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
 * <p/>
 * 图片的缩放方式
 * <p/>
 * imageScaleType(ImageScaleType imageScaleType) imageScaleType: EXACTLY
 * :图像将完全按比例缩小的目标大小 EXACTLY_STRETCHED:图片会缩放到目标大小完全 IN_SAMPLE_INT:图像将被二次采样的整数倍
 * IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小 NONE:图片不会调整
 *
 * @author fengbb
 */
public class UniversalImageLoadTool {
	private static final String TAG = "UniversalImageLoadTool";
	private static ImageLoader imageLoader = ImageLoader.getInstance();
	private static DisplayImageOptions optionsMsg = null;

	public static ImageLoader getImageLoader() {
		return imageLoader;
	}

	public static boolean checkImageLoader() {
		return imageLoader.isInited();
	}

	/*public static void disPlay(String uri, ImageAware imageAware, int default_pic) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(default_pic)
				.showImageForEmptyUri(default_pic).showImageOnFail(default_pic).cacheInMemory(true).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY).build();
		imageLoader.displayImage(uri, imageAware, options);
	}*/

	public static void disPlayUserFace(String uri, ImageView imageView) {

		imageLoader.displayImage(uri, imageView, getDisplayImageOptions(R.mipmap.ic_launcher));
	}


	/*public static void disPlayImgSmall(String uri, ImageView imageView, int imgdef) {
        if (imgdef > 0) {
            imageLoader.displayImage(uri, imageView, getDisplayImageOptions(imgdef));
        } else {
            imageLoader.displayImage(uri, imageView, getDisplayImageOptions(anim.loading_small_anim));
        }
    }

    public static void disPlayImg(String uri, ImageView imageView) {
        try {
            if (TextUtils.isEmpty(uri) || uri.startsWith("http") || uri.startsWith("drawable")) {
                imageLoader.displayImage(uri, imageView, getDisplayImageOptions(drawable.ic_image_default));
            } else {
                imageView.setImageURI(Uri.parse(uri));
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace() ;
        }
    }*/
	public static void disPlayImg(String uri, ImageView imageView, int imgDefault) {
		try {
			if (TextUtils.isEmpty(uri))
				return;
			imageLoader.displayImage(uri, imageView, getDisplayImageOptions(imgDefault));
           /* if (uri.startsWith("http") || uri.startsWith("drawable")) {
                imageLoader.displayImage(uri, imageView, getDisplayImageOptions(imgDefault));
            } else {
				imageLoader.displayImage(uri, imageView, getDisplayImageOptions(imgDefault));
                imageView.setImageURI(Uri.parse(uri));
            }*/
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
	}

	public static void disPlayImgOnly(String uri, ImageView imageView) {
		try {
			imageLoader.displayImage(uri, imageView);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
	}

	public static void disPlayImg(Context context, String uri, ImageView imageView) {
		try {
			if (!TextUtils.isEmpty(uri) || uri.startsWith("http") || uri.startsWith("drawable")) {
				imageLoader.displayImage(uri, imageView, getDisplayImageOptions(R.mipmap.ic_launcher));
			} else {
				imageView.setImageBitmap(uriToBitmap(context, Uri.parse(uri)));
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}


	}

	/**
	 * 带进度的可回调的展示图片方式
	 *
	 * @param uri
	 * @param imageView
	 * @param width
	 * @param height
	 * @param listener
	 */
	public static void disPlayImgWH(String uri, ImageView imageView, int width, int height, SimpleImageLoadingListener listener) {
		imageLoader.loadImage(uri, new ImageSize(width, height), listener);
	}

	public static Bitmap uriToBitmap(Context context, Uri uri) {
		try {
			return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return null;

	}

	/*public static void disPlayImgWH(String uri, final ImageView imageView, int width, int height) {
        ImageSize targetSize = new ImageSize(width, height);
		imageLoader.loadImage(uri, targetSize, getDisplayImageOptions(anim.loading_mid_anim),
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						super.onLoadingStarted(imageUri, view);
						// imageView.setImageBitmap(bm)
					}

					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
						// TODO Auto-generated method stub
						super.onLoadingFailed(imageUri, view, failReason);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						imageView.setImageBitmap(loadedImage);
					}
				});
	}*/

/*	@Deprecated
	public static void displayFakeImg(final Context context, String uri, final ImageView imageView,
			final ImageView centerOvely, final ImageView iv_image_show_anim, final TextView tv_indicaotor, int width,
			int height) {
		imageLoader.displayImage(uri, imageView, getDisplayImageOptions(drawable.ic_image_default),
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						super.onLoadingStarted(imageUri, view);
						centerOvely.setVisibility(View.VISIBLE);
						iv_image_show_anim.setVisibility(View.VISIBLE);
						tv_indicaotor.setVisibility(View.VISIBLE);
						RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tv_indicaotor
								.getLayoutParams();
						layoutParams.addRule(RelativeLayout.BELOW, R.id.iv_image_show_anim);
						tv_indicaotor.setLayoutParams(layoutParams);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
						super.onLoadingFailed(imageUri, view, failReason);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

					}
				});
		// 倒计时
		CountDownTimer _timer = new CountDownTimer(1 * 1000, 50) {

			@Override
			public void onTick(long millisUntilFinished) {
				if (millisUntilFinished > 950) {

				}
			}

			@Override
			public void onFinish() {
				// 最后显示录音时间太长了，结束画面

			}
		};

	}*/



/*	// 带有遮罩和进度的方法调用
	public static void disPlayImgWH(final Context context, String uri, final ImageView imageView,
			final ImageView centerOvely, final ImageView iv_image_show_anim, final TextView tv_indicaotor, int width,
			int height) {
		imageLoader.displayImage(uri, imageView, getDisplayImageOptions(drawable.ic_image_default),
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						super.onLoadingStarted(imageUri, view);
						centerOvely.setVisibility(View.VISIBLE);
						iv_image_show_anim.setVisibility(View.VISIBLE);
						tv_indicaotor.setVisibility(View.VISIBLE);
						RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tv_indicaotor
								.getLayoutParams();
						layoutParams.addRule(RelativeLayout.BELOW, R.id.iv_image_show_anim);
						tv_indicaotor.setLayoutParams(layoutParams);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
						super.onLoadingFailed(imageUri, view, failReason);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						iv_image_show_anim.setVisibility(view.GONE);
						centerOvely.setVisibility(view.GONE);
						tv_indicaotor.setVisibility(View.GONE);
						imageView.setImageBitmap(loadedImage);
					}
				}, new ImageLoadingProgressListener() {

					@Override
					public void onProgressUpdate(String imageUri, View view, int current, int total) {
						int[] drawableImgArr = new int[] { R.drawable.img_loading01, R.drawable.img_loading02,
								R.drawable.img_loading03, R.drawable.img_loading04, R.drawable.img_loading05,
								R.drawable.img_loading06, R.drawable.img_loading07, R.drawable.img_loading08,
								R.drawable.img_loading09, R.drawable.img_loading10, R.drawable.img_loading11,
								R.drawable.img_loading12, R.drawable.img_loading13, R.drawable.img_loading14,
								R.drawable.img_loading15, R.drawable.img_loading16, R.drawable.img_loading17,
								R.drawable.img_loading18, R.drawable.img_loading19, R.drawable.img_loading20,
								R.drawable.img_loading21 };
						// LogUtils.d(UIUtils.TAG,"current:"+current+",total:"+total);
						int progress = Math.round(100.0f * current / total);
						if (progress < 101) {
							tv_indicaotor.setText(progress + "%");
						}
						if (progress < 5) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading01);
						} else if (progress < 10) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading02);
						} else if (progress < 15) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading03);
						} else if (progress < 20) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading04);
						} else if (progress < 25) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading05);
						} else if (progress < 30) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading06);
						} else if (progress < 35) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading07);
						} else if (progress < 40) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading08);
						} else if (progress < 45) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading09);
						} else if (progress < 50) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading10);
						} else if (progress < 55) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading11);
						} else if (progress < 60) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading12);
						} else if (progress < 65) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading13);
						} else if (progress < 70) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading14);
						} else if (progress < 75) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading15);
						} else if (progress < 80) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading16);
						} else if (progress < 85) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading17);
						} else if (progress < 90) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading18);
						} else if (progress < 95) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading19);
						} else if (progress < 100) {
							iv_image_show_anim.setImageResource(R.drawable.img_loading20);
						} else {
							iv_image_show_anim.setImageResource(R.drawable.img_loading21);
						}

					}
				});
	}*/

	public static DisplayImageOptions getDisplayImageOptionsMsg(int imgid) {
		if (optionsMsg == null) {
			optionsMsg = new DisplayImageOptions.Builder().showImageOnLoading(imgid).showImageForEmptyUri(imgid)
					.showImageOnFail(imgid).resetViewBeforeLoading(true).cacheInMemory(true).cacheOnDisk(true)
					.considerExifParams(true)
							// .displayer(new FadeInBitmapDisplayer(10))
					.bitmapConfig(Bitmap.Config.ARGB_8888).imageScaleType(ImageScaleType.EXACTLY).build();
		}
		return optionsMsg;
	}

	public static DisplayImageOptions getDisplayImageOptions(int imgid) {

		/* * options = new DisplayImageOptions.Builder() .displayer(new
		 * FadeInBitmapDisplayer(300))*/

		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(imgid)
				.showImageForEmptyUri(imgid).showImageOnFail(imgid).resetViewBeforeLoading(true).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
						// .displayer(new FadeInBitmapDisplayer(10))
				.bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).build();

		return options;
	}

	/*public static void displayImage(String imgurl, final ImageView imageView, final ProgressBar progressBar) {
		imageLoader.displayImage(imgurl, imageView, getDisplayImageOptions(drawable.ic_image_default),
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						progressBar.setProgress(0);
						progressBar.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
						progressBar.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						progressBar.setVisibility(View.GONE);
					}
				}, new ImageLoadingProgressListener() {
					@Override
					public void onProgressUpdate(String imageUri, View view, int current, int total) {
						progressBar.setProgress(Math.round(100.0f * current / total));
					}
				});

	}*/

	/***
	 * 清除单个缓存
	 *
	 * @param imageUri
	 */
	public static void clearImgOneCache(String imageUri) {
		imageLoader.clearMemoryCache();
		File imgfile = new File(""/*GFile.DEFAULT_SAVE_NOSTRA13_PATH + "/" + new Md5FileNameGenerator().generate(imageUri)*/);
		if (imgfile != null && imgfile.exists()) {
			boolean delete = imgfile.delete();
		}

	}

	/**
	 * 改变单个缓存的内容
	 *
	 * @param imageUri
	 * @param bitmap
	 */
	public static void changeImgOneCache(String imageUri, Bitmap bitmap) {
		imageLoader.clearMemoryCache();
		File imgfile = new File(""/*GFile.DEFAULT_SAVE_NOSTRA13_PATH + "/" + new Md5FileNameGenerator().generate(imageUri)*/);
		if (imgfile != null && imgfile.exists()) {
			try {
				if (bitmap != null) {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(imgfile));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void clear() {
		imageLoader.clearMemoryCache();
		imageLoader.clearDiskCache();
	}

	public static void clearMemoryCache() {
		imageLoader.clearMemoryCache();
	}

	public static void resume() {
		imageLoader.resume();
	}

	/**
	 * 暂停加载
	 */
	public static void pause() {
		imageLoader.pause();
	}

	/**
	 * 停止加载
	 */
	public static void stop() {
		imageLoader.stop();
	}

	/**
	 * 销毁加载
	 */
	public static void destroy() {
		imageLoader.destroy();
	}

}
