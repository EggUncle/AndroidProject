
package com.example.administrator.mynotificationtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class OtherActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//设置该Activity显示的页面
		setContentView(R.layout.other);
		Log.v("MY_TAG","123");
	}
}