package co.stormcorp.awdemo;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.cube.storm.ui.activity.StormActivity;

public class MainActivity extends StormActivity
{
	@Override public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.settings)
		{
			Intent settings = new Intent(this, SettingsActivity.class);
			startActivity(settings);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
