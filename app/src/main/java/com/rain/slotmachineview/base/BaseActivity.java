package com.rain.slotmachineview.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rain.slotmachineview.R;
import com.rain.slotmachineview.utils.StatusBarUtil;

import butterknife.ButterKnife;


/**
 * @Author: rain
 * @Date: 2020/12/18
 * @Description  基础Activity
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener{


    protected GlobalApplication context;
    protected BaseActivity activity;

    //标题栏
    protected View viewTitleBar;
    protected LayoutInflater mLayoutInflater;
    //标题栏
    protected RelativeLayout llTitlebar;

    //是否已经初始化标题栏
    private boolean isInitTitleBar = false;
    // 基础控件,标题栏右侧更多
    protected ImageView mIvBack,mIvMore,mIvClose,mIvShare;
    protected View mBottomLine;
    //标题栏右侧更多按钮
    protected View flTitleMore;
    protected FrameLayout flContentBase;
    //内容,由子类做填充
    protected View mContentView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaseView(savedInstanceState);
        context = GlobalApplication.getInstance();
        activity = this;
        initView(savedInstanceState);
//        ButterKnife.bind(this);
        initBaseData(savedInstanceState);
        initData(savedInstanceState);
        initStatusbarColor();
    }

    /**
     *
     * @Title: findContentViewById
     * @Description: TODO(初始化容器中控件,子类一般都用此方法代替findViewById)
     * @param @param id
     * @param @return
     * @return View 返回类型
     * @throws
     */
    protected View findContentViewById(int id) {
        return mContentView.findViewById(id);
    }


    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initData(Bundle savedInstanceState);


    /**
     *
     * @Title: initBaseData
     * @Description: TODO(初始化基础数据)
     * @param
     * @return void 返回类型
     * @throws
     */
    protected void initBaseData(Bundle savedInstanceState) {

    }

    /**
     *
     * @Title: initBaseView
     * @Description: TODO(初始化基础UI控件)
     * @param
     * @return void 返回类型
     * @throws
     */
    protected void initBaseView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base);
        flContentBase = (FrameLayout) findViewById(R.id.fl_content_base);
//        mLoadingView = (ZLoadingView) findViewById(R.id.loadingView);
        mLayoutInflater = getLayoutInflater();
//        mLoadingDialog = new LoadingProgressDialog(this);
    }





    /**
     *
     * 设置业务内容
     *
     * @param layout
     *
     */
    protected void setCustomView(int layout) {

        mContentView = this.mLayoutInflater.inflate(layout, null);
        flContentBase.addView(mContentView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void initStatusbarColor() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.black6));
    }


    /**
     * 自定义标题栏
     * @param resId
     */
    protected void setCustomTitleBar(int resId) {

        if (viewTitleBar == null)
            viewTitleBar = this.mLayoutInflater.inflate(resId, null);
        initTitleBar();

    }

    /**
     * 自定义标题栏背景颜色
     * @param resId
     */
    protected void setCustomTitleBarBackground(int resId) {

        if (viewTitleBar != null)
            viewTitleBar.setBackgroundColor(resId);
    }

    protected void setCustomTitleColor(int resId) {
        if (viewTitleBar != null)
            ((TextView) viewTitleBar.findViewById(R.id.tv_title_text)).setTextColor(resId);
    }

    protected void setCustomTitle(int title) {

//        isSetTitle = true;
        if (viewTitleBar == null)
            viewTitleBar = this.mLayoutInflater.inflate(R.layout.activity_title_bar,
                    null);
        initTitleBar();
        ((TextView) viewTitleBar.findViewById(R.id.tv_title_text)).setText(title);
    }

    /**
     *
     * 设置自定义标题内容
     *
     * @param title
     */
    protected void setCustomTitle(String title) {

//        isSetTitle = true;
        if (viewTitleBar == null)
            viewTitleBar = this.mLayoutInflater.inflate(R.layout.activity_title_bar,
                    null);
        initTitleBar();
        ((TextView) viewTitleBar.findViewById(R.id.tv_title_text)).setText(title);
    }

    /**
     *
     * @Title: titleBarDoMore
     * @Description: TODO(标题栏右侧按钮功能，子类需要则重写)
     * @param @param view
     * @return void 返回类型
     * @throws
     */
    protected void titleBarDoMore(View view) {

    };

    /**
     *
     * 隐藏标题栏
     *
     * @param
     */
    protected void setHideTitle() {
//        isSetTitle = false;
        if (viewTitleBar == null)
            viewTitleBar = this.mLayoutInflater.inflate(R.layout.activity_title_bar,
                    null);
        initTitleBar();
        llTitlebar.setVisibility(View.GONE);
    }


    /**
     * 标题栏返回按钮事件响应
     */
    protected void doBack() {
        finish();
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar() {
        if(!isInitTitleBar) {//保证titlebar只初始化一次
            mIvBack = (ImageView) viewTitleBar.findViewById(R.id.iv_title_back);
            if (mIvBack != null)
                mIvBack.setOnClickListener(this);
            llTitlebar = (RelativeLayout) findViewById(R.id.ll_titlebar);
            llTitlebar.addView(viewTitleBar, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            llTitlebar.setVisibility(View.VISIBLE);
            isInitTitleBar = true;
        }

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_title_back) {
            doBack();
            return;
        }
    }




    /**
     * 设置业务内容是否显示
     * @param isVisiable
     */
    protected void setContentVisiable(boolean isVisiable) {
        //只执行一次
        if(flContentBase == null)return;
        flContentBase.setVisibility(isVisiable ? View.VISIBLE : View.GONE);

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
