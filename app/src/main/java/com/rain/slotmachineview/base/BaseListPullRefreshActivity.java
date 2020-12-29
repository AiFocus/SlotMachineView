package com.rain.slotmachineview.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.rain.slotmachineview.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;



/**
 * 
 * @ClassName: BaseYfListPullRefreshActivity
 * @Description: TODO(基础上拉加载下拉刷新RecyclerView类)
 * @author rain
 * 
 */
public abstract class BaseListPullRefreshActivity extends BaseRefreshActivity {

	protected GlobalApplication context;


	@Override
	protected void initBaseData(Bundle savedInstanceState) {
		super.initBaseData(savedInstanceState);
		context = (GlobalApplication) getApplicationContext();
		if (context == null&& null!=savedInstanceState) {

			Intent i = getBaseContext().getPackageManager()
					.getLaunchIntentForPackage(getBaseContext().getPackageName());
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		}

	}

	@Override
	protected void setCustomView(int layout) {
		super.setCustomView(layout);
		mRecyclerView = findViewById(R.id.recyclerview);
		mPullToRefreshLayout = findViewById(R.id.refreshLayout);

		mPullToRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh(RefreshLayout refreshlayout) {
				mIsPullRefresh = true;
				refresh();

			}
		});
		mPullToRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
			@Override
			public void onLoadmore(RefreshLayout refreshlayout) {
				mIsPullRefresh = false;
				nextPage();
				refreshData();
			}
		});
	}


	
}
