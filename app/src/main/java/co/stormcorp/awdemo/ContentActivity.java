package co.stormcorp.awdemo;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.cube.storm.ui.activity.StormActivity;

public class ContentActivity extends StormActivity
{
	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		getSupportActionBar().setElevation(0);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == android.R.id.home)
		{
			try
			{
				NavUtils.navigateUpFromSameTask(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				finish();
			}
		}

		return super.onOptionsItemSelected(item);
	}
}
