package com.rain.slotmachineview.bean;

import java.io.Serializable;

public class LotteryInfo implements Serializable {
    /**
     * 抽签列表item实体类
     */
    int id;
    int goods_id;
    String drawname;
    String startdate;
    String enddate;
    String goodsName;
    String goodsPrice;
    String goodsImagePath;
    int leftGoodsCount;
    int needfbalance;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getDrawname() {
        return drawname;
    }

    public void setDrawname(String drawname) {
        this.drawname = drawname;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImagePath() {
        return goodsImagePath;
    }

    public void setGoodsImagePath(String goodsImagePath) {
        this.goodsImagePath = goodsImagePath;
    }

    public int getLeftGoodsCount() {
        return leftGoodsCount;
    }

    public void setLeftGoodsCount(int leftGoodsCount) {
        this.leftGoodsCount = leftGoodsCount;
    }

    public int getNeedfbalance() {
        return needfbalance;
    }

    public void setNeedfbalance(int needfbalance) {
        this.needfbalance = needfbalance;
    }



    @Override
    public String toString() {
        return "LotteryInfo{" +
                "id=" + id +
                ", goods_id=" + goods_id +
                ", drawname='" + drawname + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsImagePath='" + goodsImagePath + '\'' +
                ", leftGoodsCount=" + leftGoodsCount +
                ", needfbalance=" + needfbalance +
                '}';
    }
}
