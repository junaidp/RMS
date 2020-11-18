package com.leavemanagement.client;

public class Logging {
	public static native void console(String text)
	/*-{
		console.log(text);
	}-*/;
}
