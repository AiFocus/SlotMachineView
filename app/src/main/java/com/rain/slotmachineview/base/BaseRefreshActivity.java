package com.rain.slotmachineview.base;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;


/**
 * 
 **************************************************
 * @ClassName: BaseListPullRefreshActivity 
 * @Description: TODO(带下拉刷新的RecyclerView)
 * @author rain
 * 
 ***************************************************
 */
public abstract class BaseRefreshActivity extends BaseActivity {

	protected SmartRefreshLayout mPullToRefreshLayout;

	protected int mPageIndex = 1;

	protected int mPageSize = 20;
	protected boolean mIsPullRefresh = true;
	/**
	 * 全局变量
	 */

	protected RecyclerView mRecyclerView;

	/**
	 * 初始化控件
	 */
	@Override
	protected void initBaseView(Bundle savedInstanceState) {
		super.initBaseView(savedInstanceState);	
	}
	
	protected abstract void refreshData();

	/**
	 * 设置容器中布局
	 */

//	protected void setCustomView(int layout) {
//		super.setCustomView(layout);
//		mContentView = this.mLayoutInflater.inflate(layout, null);
//		flContentBase.addView(mContentView, new LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//	}

	/**
	 * 重新查询数据
	 */
	protected void refresh() {
		
		mIsPullRefresh = true;
		mPageIndex = 1;
		refreshData();
	}

	public void nextPage() {

		mPageIndex++;
	}
	
	public void backPage() {

		mPageIndex--;
	}


}
