package com.stephen.furniturerepair.common.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.stephen.furniturerepair.R;


public  class BufferDialog extends Dialog implements DialogInterface {

	private BufferDialog dialog_buffer;
	private String title;
	private String content;
    private int theme;
	private Context context;
    private ImageView buffer_iv;
    private AnimationDrawable animationDrawable;


	public BufferDialog(Context context, String title, String content,int theme) {
		super(context, theme);
		this.context = context;
		this.title = title;
		this.content = content;
	}
	public BufferDialog(Context context) {
        super(context, R.style.MyDialogStyleTop );
		this.context = context;
		this.title =context.getResources().getString(R.string.app_name);
		this.content =context.getResources().getString(R.string.xlistview_header_hint_loading);
        setCanceledOnTouchOutside(false);
	}
	public BufferDialog(Context context, String content) {
        super(context, R.style.MyDialogStyleTop );
		this.context = context;
		this.title =context.getResources().getString(R.string.app_name);
		this.content =content;
        setCanceledOnTouchOutside(false);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custormdialog_buffer);
        buffer_iv = (ImageView) findViewById(R.id.buffer_iv);
        animationDrawable = (AnimationDrawable) buffer_iv.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }
}
