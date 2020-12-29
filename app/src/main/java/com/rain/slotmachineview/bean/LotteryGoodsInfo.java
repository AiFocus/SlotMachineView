package com.rain.slotmachineview.bean;


public class LotteryGoodsInfo {


    boolean isWin;
    int goodsId;
    String goodsname;
    String goodsImagePath;
    int goodscount;
    double fbalance;
    int prizeCount;
    int needfbalance;



    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsImagePath() {
        return goodsImagePath;
    }

    public void setGoodsImagePath(String goodsImagePath) {
        this.goodsImagePath = goodsImagePath;
    }

    public int getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(int goodscount) {
        this.goodscount = goodscount;
    }

    public double getFbalance() {
        return fbalance;
    }

    public void setFbalance(double fbalance) {
        this.fbalance = fbalance;
    }

    public int getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(int prizeCount) {
        this.prizeCount = prizeCount;
    }

    public int getNeedfbalance() {
        return needfbalance;
    }

    public void setNeedfbalance(int needfbalance) {
        this.needfbalance = needfbalance;
    }

    @Override
    public String toString() {
        return "LotteryGoodsInfo{" +
                "isWin=" + isWin +
                ", goodsId=" + goodsId +
                ", goodsname='" + goodsname + '\'' +
                ", goodsImagePath='" + goodsImagePath + '\'' +
                ", goodscount=" + goodscount +
                ", fbalance=" + fbalance +
                ", prizeCount=" + prizeCount +
                ", needfbalance=" + needfbalance +
                '}';
    }
}
