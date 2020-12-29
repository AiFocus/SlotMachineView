package com.rain.slotmachineview.bean;

import java.util.List;

public class LotteryList {
	int prizeCount;
	double fbalance;
	List<LotteryInfo> drawList;

	public int getPrizeCount() {
		return prizeCount;
	}

	public void setPrizeCount(int prizeCount) {
		this.prizeCount = prizeCount;
	}

	public double getFbalance() {
		return fbalance;
	}

	public void setFbalance(double fbalance) {
		this.fbalance = fbalance;
	}

	public List<LotteryInfo> getDrawList() {
		return drawList;
	}

	public void setDrawList(List<LotteryInfo> drawList) {
		this.drawList = drawList;
	}

	@Override
	public String toString() {
		return "LotteryList{" +
				"prizeCount=" + prizeCount +
				", fbalance=" + fbalance +
				", drawList=" + drawList +
				'}';
	}
}
