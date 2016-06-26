package uncle.egg.studysystem3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import uncle.egg.studysystem3.myFragment.MyFragmentAdapter;

public class MainActivity extends AppCompatActivity  implements OnClickListener,
		OnPageChangeListener
{

	MyFragmentAdapter fragmentAdapter;

	private ViewPager mViewPager;
//	private List<Fragment> mTabs = new ArrayList<Fragment>();
//	private String[] mTitles = new String[]
//	{ "First Fragment !", "Second Fragment !", "Third Fragment !",
//			"Fourth Fragment !" };
//	private FragmentPagerAdapter mAdapter;

	private String[] titleStrs = {"我的科目","练习&测试","错题集","我"};

	private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setOverflowButtonAlways();
	//	getActionBar().setDisplayShowHomeEnabled(false);
		setTitle("学习");

		initView();
		initDatas();
		//mViewPager.setAdapter(mAdapter);
		initEvent();

	}

	/**
	 * 初始化所有事件
	 */
	private void initEvent()
	{

		mViewPager.setOnPageChangeListener(this);

	}

	private void initDatas()
	{

	}

	private void initView()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
		mTabIndicators.add(one);
		ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
		mTabIndicators.add(two);
		ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
		mTabIndicators.add(three);
		ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
		mTabIndicators.add(four);

		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);

		one.setIconAlpha(1.0f);


		FragmentManager fm = getSupportFragmentManager();
		fragmentAdapter = new MyFragmentAdapter(fm);
		mViewPager.setAdapter(fragmentAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setOverflowButtonAlways()
	{
		try
		{
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKey = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKey.setAccessible(true);
			menuKey.setBoolean(config, false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 设置menu显示icon
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{

		if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
		{
			if (menu.getClass().getSimpleName().equals("MenuBuilder"))
			{
				try
				{
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onClick(View v)
	{
		clickTab(v);

	}

	/**
	 * 点击Tab按钮
	 *
	 * @param v
	 */
	private void clickTab(View v)
	{
		resetOtherTabs();

		switch (v.getId())
		{
			case R.id.id_indicator_one:
				mTabIndicators.get(0).setIconAlpha(1.0f);
				mViewPager.setCurrentItem(0, false);
				setTitle(titleStrs[0]);
				break;
			case R.id.id_indicator_two:
				mTabIndicators.get(1).setIconAlpha(1.0f);
				mViewPager.setCurrentItem(1, false);
				setTitle(titleStrs[1]);
				break;
			case R.id.id_indicator_three:
				mTabIndicators.get(2).setIconAlpha(1.0f);
				mViewPager.setCurrentItem(2, false);
				setTitle(titleStrs[2]);
				break;
			case R.id.id_indicator_four:
				mTabIndicators.get(3).setIconAlpha(1.0f);
				mViewPager.setCurrentItem(3, false);
				setTitle(titleStrs[3]);
				break;
		}
	}

	/**
	 * 重置其他的TabIndicator的颜色
	 */
	private void resetOtherTabs()
	{
		for (int i = 0; i < mTabIndicators.size(); i++)
		{
			mTabIndicators.get(i).setIconAlpha(0);
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
							   int positionOffsetPixels)
	{
		// Log.e("TAG", "position = " + position + " ,positionOffset =  "
		// + positionOffset);
		if (positionOffset > 0)
		{
			ChangeColorIconWithText left = mTabIndicators.get(position);
			ChangeColorIconWithText right = mTabIndicators.get(position + 1);
			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);

		}

	}

	@Override
	public void onPageSelected(int position)
	{
		// TODO Auto-generated method stub
		setTitle(titleStrs[position]);
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
		// TODO Auto-generated method stub

	}

}