package com.stephen.furniturerepair.gui.activity.three;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseFragment;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Stephen on 09/14/2016.
 * Emial: 895745843@qq.com
 */
public class ThreeFragment extends BaseFragment {

    @Bind(R.id.titleBar_shopCart)
    TitleBar titleBarShopCart;
    @Bind(R.id.textView)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_three, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        titleBarShopCart.setTitlBartitle("发单");
        textView.setText("发单");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void reLoadData() {
        // TODO: 09/14/2016 do something
    }
}
