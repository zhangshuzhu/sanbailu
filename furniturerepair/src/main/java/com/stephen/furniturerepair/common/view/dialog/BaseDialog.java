package com.stephen.furniturerepair.common.view.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.app.App;
import com.stephen.furniturerepair.common.utils.ScreenUtils;


public class BaseDialog {

    private Dialog mTipDlg;
    private Context mContext;
    private View rootView;

    public BaseDialog(Context context,View rootView) {
        mContext = context;
        this.rootView = rootView;
    }

    /**
     * 创建对话框
     */
    public void createDlg() {
        if (mTipDlg == null) {
            int width = ScreenUtils.getScreenWidth(App.getContext());
            mTipDlg = new Dialog(mContext, R.style.MyDialogStyleTop);
            mTipDlg.setContentView(rootView);
            WindowManager.LayoutParams lp = mTipDlg.getWindow().getAttributes();
            lp.width = (int) (width * 0.9); // 设置宽度
            mTipDlg.getWindow().setAttributes(lp);
            mTipDlg.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
        }
    }

    /**
     * 显示对话框
     */
    public void show() {
        createDlg();
        if (mTipDlg != null && !mTipDlg.isShowing()) {
            mTipDlg.show();
        }
    }

    /**
     * 取消显示对话框
     */
    public void dismiss() {
        if (mTipDlg != null && mTipDlg.isShowing()) {
            mTipDlg.dismiss();
            mTipDlg = null;
        }
    }
}