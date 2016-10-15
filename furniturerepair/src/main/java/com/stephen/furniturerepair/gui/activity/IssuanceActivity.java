package com.stephen.furniturerepair.gui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.stephen.furniturerepair.MainActivity;
import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.App;
import com.stephen.furniturerepair.app.URL;
import com.stephen.furniturerepair.common.base.BaseActivity;
import com.stephen.furniturerepair.common.interfaces.GlobalCallBack;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;
import com.stephen.furniturerepair.common.utils.FileUtils;
import com.stephen.furniturerepair.common.utils.LogUtils;
import com.stephen.furniturerepair.common.utils.SPUtils;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Stephen on 09/16/2016.
 * Emial: 895745843@qq.com
 * <p>
 * 发单
 */
public class IssuanceActivity extends BaseActivity implements TitleBarListener.ListenerTitleBarLeft {

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.tv_suance_type)
    TextView tvSuanceType;
    @Bind(R.id.ll_suance_type)
    LinearLayout llSuanceType;
    @Bind(R.id.iv_shop_neer_image)
    ImageView ivShopNeerImage;
    @Bind(R.id.ll_neer_upload)
    LinearLayout llNeerUpload;
    @Bind(R.id.iv_shop_far_image)
    ImageView ivShopFarImage;
    @Bind(R.id.ll_far_upload)
    LinearLayout llFarUpload;
    @Bind(R.id.tv_suance_color)
    TextView tvSuanceColor;
    @Bind(R.id.ll_suance_color)
    LinearLayout llSuanceColor;
    @Bind(R.id.et_suance_desc)
    EditText etSuanceDesc;
    @Bind(R.id.tv_suance_date)
    TextView tvSuanceDate;
    @Bind(R.id.ll_suance_date)
    LinearLayout llSuanceDate;
    @Bind(R.id.et_suance_name)
    EditText etSuanceName;
    @Bind(R.id.et_suance_phone)
    EditText etSuancePhone;
    @Bind(R.id.et_suance_address)
    EditText etSuanceAddress;
    @Bind(R.id.ll_suance_release)
    LinearLayout llSuanceRelease;
    @Bind(R.id.et_suance_title)
    EditText etSuanceTitle;
    private AlertDialog alertDialog;
    private TextView textViewCameral;
    private TextView textViewGalary;
    private TextView textViewCancel;
    private File mFile;
    private DisplayImageOptions options;
    private String neerImage;
    private String picturePath;

    @Override
    protected int setView() {
        return R.layout.activity_issuance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        titleBar.setTitlBartitle("发布信息");
        titleBar.setTitlBarLeftImageButtonResuource(R.mipmap.icon_back, this);

//        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag("one");
//        if (fragmentByTag == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.action_bar, new OneFragment()).addToBackStack("one").commit();
//        } else {
//            getSupportFragmentManager().popBackStack("one", 1);
//        }
//
//        Fragment fragmentByTag2 = getSupportFragmentManager().findFragmentByTag("two");
//        if (fragmentByTag2 == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.action_bar, new TwoFragment()).addToBackStack("two").commit();
//        }else {
//            getSupportFragmentManager().popBackStack();
//        }

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) //加载中
                .showImageForEmptyUri(R.mipmap.ic_launcher) //无图片
                .showImageOnFail(R.mipmap.ic_launcher)//加载失败
//                .cacheInMemory(true) //内存存储
                .cacheOnDisk(true)//磁盘缓存
                .considerExifParams(false)
                .displayer(new FadeInBitmapDisplayer(500))
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                        // .displayer(new RoundedBitmapDisplayer(15)) // 设置图片圆角
                .build();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void clickTitleBarLeft() {
        closeActivity();
    }

    @Override
    public void onBackPressed() {
        closeActivity();
    }

    private void closeActivity() {
//        setResult(Activity.RESULT_OK);
        finish();
    }

    @OnClick({R.id.ll_suance_type, R.id.ll_neer_upload, R.id.ll_far_upload, R.id.ll_suance_color, R.id.ll_suance_date, R.id.ll_suance_release})
    public void onClick(View view) {
        switch (view.getId()) {
//            修补类型
            case R.id.ll_suance_type:
                break;
//            上传近照
            case R.id.ll_neer_upload:
                selectCameraOrGallery();
                break;
//            上传远照
            case R.id.ll_far_upload:
                break;
//            颜色
            case R.id.ll_suance_color:
                break;
//            预订时间
            case R.id.ll_suance_date:
                break;
//            发布
            case R.id.ll_suance_release:
                String discr = etSuanceDesc.getText().toString().trim();
                String name = etSuanceName.getText().toString().trim();
                String phone = etSuancePhone.getText().toString().trim();
                String address = etSuanceAddress.getText().toString().trim();
                String title = etSuanceTitle.getText().toString().trim();

                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(IssuanceActivity.this, "请输入发布标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(discr)) {
                    Toast.makeText(IssuanceActivity.this, "请填写客户需求信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(IssuanceActivity.this, "请填写联系人姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(IssuanceActivity.this, "请填写联系人电话", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(IssuanceActivity.this, "请填写联系人地址", Toast.LENGTH_SHORT).show();
                    return;
                }

                publishInfo(discr, name, phone, address, title);

                break;
        }
    }

    private void selectCameraOrGallery() {
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
            return;
        }
        alertDialog = new AlertDialog.Builder(this).create();
        View view_avatar = LayoutInflater.from(this).inflate(R.layout.dialog_camera_gallery, null);
        alertDialog.show();
//                自定义对话框样式
        alertDialog.setContentView(view_avatar);
//                设置对话框横向满屏
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
//                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.y = 20;
        window.setAttributes(lp);
//                设置对话框位置
        window.setGravity(Gravity.BOTTOM);
        // findviews
        textViewCameral = (TextView) view_avatar.findViewById(R.id.textView_camera);
        textViewGalary = (TextView) view_avatar.findViewById(R.id.textView_gallery);
        textViewCancel = (TextView) view_avatar.findViewById(R.id.textView_cancel);
//                设置监听
        textViewCameral.setOnClickListener(onClickListener);
        textViewGalary.setOnClickListener(onClickListener);
        textViewCancel.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                拍照
                case R.id.textView_camera:
                    getPictureFromCamera();
                    break;
//                相册
                case R.id.textView_gallery:
                    //左起参数：选择行为权限，系统本地相册URI路径
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    //向onActivityResult发送intent，requestCode为GET_PIC_FROM_GALLERY
                    startActivityForResult(i, 1);
                    break;
//                上传照片取消
                case R.id.textView_cancel:
                    alertDialog.dismiss();
                    break;
            }
        }
    };

    private void getPictureFromCamera() {
        launchCamera();
    }

    public void launchCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(this.getPackageManager()) != null) {
            // set the output file of camera
            try {
                mFile = FileUtils.createTmpFile(this);
            } catch (IOException e) {
//                Log.e(TAG, "launchCamera: ", e);
            }
            if (mFile != null && mFile.exists()) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
                startActivityForResult(cameraIntent, 0);
            } else {
                Toast.makeText(this, "图片错误", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "无法启动相机！", Toast.LENGTH_SHORT).show();
        }

    }

    private void publishInfo(String descr, String name, String phone, String address, String title) {
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
//        list.add(new BasicNameValuePair("account", account));
        list.add(new BasicNameValuePair("title", title));
        list.add(new BasicNameValuePair("repair_type", tvSuanceType.getText().toString()));
        list.add(new BasicNameValuePair("recent_phone",neerImage ));//二进制流  base64字符串
//        list.add(new BasicNameValuePair("far_phone", ));
//        list.add(new BasicNameValuePair("recent_name", ));//上传近照文件名称（在相册里读出的文件名）
//        list.add(new BasicNameValuePair("far_name", ));//上传远照名称
        list.add(new BasicNameValuePair("contact_address", address));//"北京市海淀区西北旺东路10号院百度科技园1号楼"
        list.add(new BasicNameValuePair("contact_number", phone));
        list.add(new BasicNameValuePair("contact_man", name));
        list.add(new BasicNameValuePair("color", tvSuanceColor.getText().toString()));
        list.add(new BasicNameValuePair("customer_demand", descr));
        list.add(new BasicNameValuePair("repair_time", tvSuanceDate.getText().toString()));
        String userId = SPUtils.getInstance(IssuanceActivity.this).getUserId();
        list.add(new BasicNameValuePair("id", userId));
//        list.add(new BasicNameValuePair("coupon", ));//卷号（默认生成8位卷号）
//        list.add(new BasicNameValuePair("status",));//订单状态(0:订单已发布，1：订单已购买，2：订单已支付，9:订单已取消)

        try {
            getDataFromServer(list, URL.URL_PUBLISH, new GlobalCallBack() {
                @Override
                public void processData(String paramObject) {
                    if (TextUtils.isEmpty(paramObject)) {
                        Toast.makeText(IssuanceActivity.this, "error!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(paramObject);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("message");
                            if (!TextUtils.isEmpty(msg)) {
                                Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                            if (code == 100) {
                                MainActivity.showIndex = 0;
                                startActivity(new Intent(IssuanceActivity.this, MainActivity.class));
                                finish();
                            }
                        } catch (JSONException e) {
                            LogUtils.E(paramObject);
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void responseError(String string) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
//            拍照
                case 0:
                    if (mFile != null && mFile.length() > 0) {
                        // notify system
                        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(mFile)));
//                        ivCameramanOwnerImage.setImageURI(Uri.fromFile(mFile));
                        String imageUrl = mFile.getAbsolutePath();
                        if (!TextUtils.isEmpty(imageUrl))
                            ImageLoader.getInstance().displayImage("file://" + imageUrl, ivShopNeerImage, options, imageLoadingListener);
//                        imageLoader.displayImage("file://"+imageUrl, ivCameramanOwnerImage);
                        alertDialog.cancel();//关闭对话框
                    }
                    break;
//            相册
                case 1:
                    if (data != null)
                        getImageFromGallery(data);
                    if (alertDialog != null && alertDialog.isShowing())
                        alertDialog.cancel();
                    break;
            }
        }
    }

    private void getImageFromGallery(Intent data) {
        Uri selectedImage = data.getData();
        //用一个String数组存储相册所有图片
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        //用一个Cursor对象得到相册的所有内容
        Cursor cursor = this.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        if (cursor == null) {
//            Log.d(CameraManDataFragment.class.getSimpleName(), "call: " + "Empty images");
        } else if (cursor.moveToFirst()) {
            //得到选中图片下标
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //得到所选的相片路径
            picturePath = cursor.getString(columnIndex);//路径/storage/emulated/0/DCIM/Camera/IMG_20160721_104056.jpg

            //关闭Cursor，以免占用资源
            cursor.close();
            String imageUrl = ImageDownloader.Scheme.FILE.wrap(picturePath);
//            LogUtils.logJack("getImageFromGallery.picturePath==" + picturePath + ",imageUrl=" + imageUrl);
            if (ivShopNeerImage != null && !TextUtils.isEmpty(imageUrl))
                ImageLoader.getInstance().displayImage(imageUrl, ivShopNeerImage, options, imageLoadingListener);
        }
    }

    ImageLoadingListener imageLoadingListener=new ImageLoadingListener(){

        @Override
        public void onLoadingStarted(String s, View view) {

        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap loadedImage) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (loadedImage != null) {
                loadedImage.compress(Bitmap.CompressFormat.PNG, 100, baos);//参数100表示不压缩
                neerImage = Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);
            }
        }

        @Override
        public void onLoadingCancelled(String s, View view) {

        }
    };
}
