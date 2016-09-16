package com.stephen.furniturerepair.gui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.stephen.furniturerepair.R;
import com.stephen.furniturerepair.common.base.BaseFragment;
import com.stephen.furniturerepair.common.view.TitleBar.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Stephen on 2016/4/4 0004.
 * Emial: 895745843@qq.com
 */
public class EmptyFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.titleBar_shopCart)
    TitleBar titleBarShopCart;
    @Bind(R.id.button_empty_login)
    Button buttonEmptyLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            titleBarShopCart.setTitlBartitle(arguments.getString("title"));
        }
        buttonEmptyLogin.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
}
