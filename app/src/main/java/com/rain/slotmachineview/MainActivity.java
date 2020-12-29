package com.rain.slotmachineview;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rain.slotmachineview.adapter.LotteryListAdapter;
import com.rain.slotmachineview.base.BaseActivity;
import com.rain.slotmachineview.bean.LotteryGoodsInfo;
import com.rain.slotmachineview.bean.LotteryInfo;
import com.rain.slotmachineview.bean.LotteryList;
import com.rain.slotmachineview.view.NumberAnimTextView;
import com.rain.slotmachineview.view.TicketGoodsDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
        protected SmartRefreshLayout mPullToRefreshLayout;
        protected int mPageIndex = 1;
        protected int mPageSize = 20;
        protected RecyclerView mRecyclerView;
        LinearLayout mLayoutEmpty;
        NumberAnimTextView mTvSum;
        NumberAnimTextView mTvSumWinProduct;
        private LotteryListAdapter mLotteryListAdapter;
        private List<LotteryInfo> mLotteryInfoList;
        private boolean isContinue = false;
        private boolean isStop = true;
        private double fbalance = 3000;
        private TicketGoodsDialog dialog ;
        Handler mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                switch (msg.what) {
                    case 0:
//                    Toast.makeText(Lottery2Activity.this,"单次抽签中签win",Toast.LENGTH_SHORT).show();
                        int position = bundle.getInt("position");
                        showPopPrompt(position);
                        break;
                    case 1:
//                    Toast.makeText(Lottery2Activity.this,"连续抽奖未中签",Toast.LENGTH_SHORT).show();
                        int position1 = bundle.getInt("position");
                        int drawId = bundle.getInt("drawId");
                        startLottery(drawId,position1);
                        break;
                    case 2:
//                    Toast.makeText(Lottery2Activity.this,"连续抽奖中签",Toast.LENGTH_SHORT).show();
                        int position3 = bundle.getInt("position");
                        mLotteryListAdapter.stopLottery(position3,true);
                        mLotteryListAdapter.notifyItemChanged(position3);
                        isStop = true;
                        showPopPrompt(position3);
                        break;
                    case 3:
                        Toast.makeText(context,"未抽中",Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        };



        @Override
        protected void initView(Bundle savedInstanceState) {
            setCustomView(R.layout.activity_main);
            setCustomTitle("抽签列表");
        }

        @Override
        protected void initData(Bundle savedInstanceState) {
            mRecyclerView = findViewById(R.id.recyclerview);
            mPullToRefreshLayout = findViewById(R.id.refreshLayout);
            mLayoutEmpty = findViewById(R.id.layout_empty);
            mTvSum = findViewById(R.id.tv_sum);
            mTvSumWinProduct = findViewById(R.id.tv_sum_win_product);
            mLotteryInfoList = new ArrayList<>();

            mLotteryListAdapter = new LotteryListAdapter(this,R.layout.layout_lottery_item,mLotteryInfoList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            mRecyclerView.setAdapter(mLotteryListAdapter);
            mLotteryListAdapter.setOnItemChildClickListener(this);
            getLotteryInfoList();

        }


        private void getLotteryInfoList() {
            LotteryList lotteryList = new LotteryList();
            List<LotteryInfo> list = new ArrayList<>();
            lotteryList.setFbalance(5000);
            for (int i = 0; i < 3; i++) {
                LotteryInfo info = new LotteryInfo();
                info.setLeftGoodsCount(100);
                info.setGoodsName("苏泊尔水壶"+i);
                info.setNeedfbalance(30);
                info.setGoodsPrice("108.02");
                mLotteryInfoList.add(info);
            }
            lotteryList.setDrawList(list);
            mLotteryInfoList.addAll(lotteryList.getDrawList());
            mLotteryListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            //跳转到详情页
            switch (view.getId()){
                case R.id.tv_lottery:
                    //TODO 先发送抽签请求，知道中签结果之后再转动滚轮盘
                    if (fbalance<25){
                        Toast.makeText(MainActivity.this,"饮币不足，不能抽签",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startLottery(mLotteryInfoList.get(position).getId(),position);
                    break;
                case R.id.tv_continuous_lottery:
                    if (isStop){
                        if (fbalance<25){
                            Toast.makeText(MainActivity.this,"饮币不足，不能抽签",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        startLottery(mLotteryInfoList.get(position).getId(),position);
                        mLotteryListAdapter.stopLottery(position,false);
                        isContinue = true;
                        isStop = false;
                    }else {
                        mLotteryListAdapter.stopLottery(position,true);
                        isContinue = false;
                        isStop = true;
                    }

                    break;
                case R.id.layout_goods_item:
                    break;
            }

        }


        @OnClick({R.id.iv_title_back,R.id.layout_ticket_goods})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.iv_title_back:
                    MainActivity.this.finish();
                    break;
                case R.id.layout_ticket_goods:
                    break;
            }
        }



        private void startLottery(final int drawId, final int position){
            LotteryGoodsInfo data = new LotteryGoodsInfo();
            data.setFbalance(3000);
            data.setGoodscount(200);
            data.setPrizeCount(3000);
            data.setGoodsname("酒杯");
            data.setWin(false);
            paseData(data,position,drawId);
        }

    private void paseData(LotteryGoodsInfo data,int position,int drawId) {
        mTvSum.setNumberString(data.getFbalance()+"");
        fbalance = data.getFbalance();
        if (data!=null){
            if (!data.isWin()&& !isContinue){
                mLotteryListAdapter.startScroll(position,false);
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                message.setData(bundle);
                message.what = 3;
                mHandler.sendMessageDelayed(message,3800);
                return;
            }else if (data.isWin()&& !isContinue){
                mLotteryListAdapter.startScroll(position,true);
                mTvSumWinProduct.setNumberString(data.getPrizeCount()+"");
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                message.setData(bundle);
                message.what = 0;
                mHandler.sendMessageDelayed(message,3800);
                return;
            }

            if (!data.isWin() && isContinue){
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putInt("drawId",drawId);
                message.setData(bundle);
                message.what = 1;
                mHandler.sendMessageDelayed(message,3800);
                mLotteryListAdapter.startScroll(position,false);
            }else if (data.isWin() && isContinue){
                mTvSumWinProduct.setNumberString(data.getPrizeCount()+"");
//                                Toast.makeText(context,"连续抽签结果返回: "+data.isWin(),Toast.LENGTH_SHORT).show();
                //弹窗口提示中签
                isContinue = false;
                mLotteryListAdapter.startScroll(position,true);
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                message.setData(bundle);
                message.what = 2;
                mHandler.sendMessageDelayed(message,3800);
                return;
            }
        }

    }


    private  void showPopPrompt(int position){
            if (dialog==null){
                dialog = new TicketGoodsDialog(this);
            }
            if (!dialog.isShowing()){
                dialog.setData(mLotteryInfoList.get(position),position);
                dialog.show();
                dialog.setOnSlotScrollListener(new TicketGoodsDialog.SlotScrollListener() {
                    @Override
                    public void onItemScroll(int position) {
                        startLottery(mLotteryInfoList.get(position).getId(),position);
                    }
                });
            }
        }


        @Override
        protected void onDestroy() {
            super.onDestroy();
            mHandler.removeCallbacksAndMessages(this);
        }
    }
