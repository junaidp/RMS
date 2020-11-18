package com.leavemanagement.client.view;

public class Data {

	private float oldValue;
	private float sum = 0;
	private boolean dataDisplayed;
	private int day;

	public float getOldValue() {
		return oldValue;
	}

	public void setOldValue(float oldValue) {
		this.oldValue = oldValue;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public boolean isDataDisplayed() {
		return dataDisplayed;
	}

	public void setDataDisplayed(boolean dataDisplayed) {
		this.dataDisplayed = dataDisplayed;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
