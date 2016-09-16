package com.stephen.furniturerepair.common.view.TitleBar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.interfaces.TitleBarListener;

/**
 * Created by Stephen on 2016/4/3 0003.
 * Emial: 895745843@qq.com
 */
public class TitleBar extends FrameLayout{

    private LinearLayout topbarLeft;
    private ImageButton topbarLeftImageButton;
    private Button topbarLeftButton;
    private TextView topbarTitle;
    private LinearLayout topbarRight;
    private ImageButton topbarRightImageButton;
    private Button topbarRightButton;

    private TitleBarListener.ListenerTitleBarLeft listenerTitleBarLeft;
    private TitleBarListener.ListenerTitleBarRight listenerTitleBarRight;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar_custom, this, true);

        initViews();

        setListener();
    }

    private void initViews() {
        topbarLeft = (LinearLayout) findViewById(R.id.topbar_left);
        topbarLeftImageButton = (ImageButton) findViewById(R.id.topbar_left_imageButton);
        topbarLeftButton = (Button) findViewById(R.id.topbar_left_button);
        topbarTitle = (TextView) findViewById(R.id.topbar_title);
        topbarRight = (LinearLayout) findViewById(R.id.topbar_right);
        topbarRightImageButton = (ImageButton) findViewById(R.id.topbar_right_imageButton);
        topbarRightButton = (Button) findViewById(R.id.topbar_right_button);
    }

    private void setListener() {
        topbarLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenerTitleBarLeft != null) {
                    listenerTitleBarLeft.clickTitleBarLeft();
                }
            }
        });

        topbarRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenerTitleBarRight != null) {
                    listenerTitleBarRight.clickTitleBarRight();
                }
            }
        });
    }

    private void setTitlBarLeftOnclickListener(TitleBarListener.ListenerTitleBarLeft leftOnclickListener){
        this.listenerTitleBarLeft = leftOnclickListener;
    }

    public void setTitlBartitle(String title){
        topbarTitle.setText(title);
        topbarTitle.setVisibility(VISIBLE);
    }

    public void setTitlBarRightOnclickListener(TitleBarListener.ListenerTitleBarRight rightOnclickListener){
        this.listenerTitleBarRight = rightOnclickListener;
    }

    public void setTitlBarLeftButtonText(String text){
        topbarLeftButton.setText(text);
        topbarLeftButton.setVisibility(VISIBLE);
    }

    public void setTitlBarLeftButtonText(String text,TitleBarListener.ListenerTitleBarLeft listenerTitleBarLeft){
        topbarLeftButton.setText(text);
        topbarLeftButton.setVisibility(VISIBLE);
        this.listenerTitleBarLeft = listenerTitleBarLeft;
    }

    public void setTitlBarLeftImageButtonResuource(int resuourceId){
        topbarLeftImageButton.setImageResource(resuourceId);
        topbarLeftImageButton.setVisibility(VISIBLE);
    }

    public void setTitlBarLeftImageButtonResuource(int resuourceId, TitleBarListener.ListenerTitleBarLeft listenerTitleBarLeft){
        topbarLeftImageButton.setImageResource(resuourceId);
        topbarLeftImageButton.setVisibility(VISIBLE);
        this.listenerTitleBarLeft = listenerTitleBarLeft;
    }

    public void setTitlBarRightButtonText(String text){
        topbarRightButton.setText(text);
        topbarRightButton.setVisibility(VISIBLE);
    }

    public void setTitlBarRightButtonText(String text,TitleBarListener.ListenerTitleBarRight listenerTitleBarRight){
        topbarRightButton.setText(text);
        topbarRightButton.setVisibility(VISIBLE);
        this.listenerTitleBarRight = listenerTitleBarRight;
    }

    public void setTitlBarRightImageButtonResuource(int resuourceId){
        topbarRightImageButton.setImageResource(resuourceId);
        topbarRightImageButton.setVisibility(VISIBLE);
    }

    public void setTitlBarRightImageButtonResuource(int resuourceId,TitleBarListener.ListenerTitleBarRight listenerTitleBarRight){
        topbarRightImageButton.setImageResource(resuourceId);
        topbarRightImageButton.setVisibility(VISIBLE);
        this.listenerTitleBarRight = listenerTitleBarRight;
    }

    public void setTitlBarLeftVisibitity(int visibitity){
        topbarLeft.setVisibility(visibitity);
    }

    public void setTitlBarRightVisibitity(int visibitity){
        topbarRight.setVisibility(visibitity);
    }
}
