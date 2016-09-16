package com.stephen.furniturerepair.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;

/**
 * Created by zsx on 2015/6/30.
 */
public class UniverImageLoadConfig {
    private static final long discCacheLimitTime = 3600 * 24 * 30L;//sd缓存的时间限制

    public static void initUniverImageLoder(Context context, int default_2) {
        if (ImageLoader.getInstance().isInited()) {
            return;
        }
        File cacheDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/qingniwan/ImageCache");
      /*  File cacheDir = StorageUtils.getOwnCacheDirectory(
                UIUtils.getContext(), "UniversalImageLoader/Cache");*/
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)
                .cacheOnDisk(false)
                .showImageOnFail(context.getResources().getDrawable(default_2))
                .showImageForEmptyUri(context.getResources().getDrawable(default_2))
                .showImageOnLoading(context.getResources().getDrawable(default_2))
                .imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
                .build();// 构建完成

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .memoryCacheExtraOptions(480, 850)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .threadPoolSize(5)
                .threadPriority(Thread.MIN_PRIORITY + 3)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new UsingFreqLimitedMemoryCache(5 * 1024 * 1024))
                .diskCache(new LimitedAgeDiscCache(cacheDir, null, new Md5FileNameGenerator(), discCacheLimitTime))
                .defaultDisplayImageOptions(options).build();
        ImageLoader.getInstance().init(config);
        ImageLoader.getInstance().isInited();
    }

}
