package co.stormcorp.awdemo.fragment;

import android.os.Bundle;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.fragment.StormListFragment;
import com.cube.storm.util.lib.debug.Debug;

/**
 * // TODO: Add class description
 *
 * @author Matt Allen
 * @project Android-AppsWorldDemo
 */
public class GAFragment extends StormListFragment
{
	boolean sent = false;

	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		try
		{
			Debug.out(getPage());

			if (getPage() != null)
			{
				String appTitle = UiSettings.getInstance().getTextProcessor().process(getPage().getTitle().getContent());
				Debug.out(appTitle);
				//StatsManager.getInstance().registerPage(getString(R.string.app_name) + " > " + appTitle, getActivity());

				sent = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sent = false;
		}
	}

	@Override public void setUserVisibleHint(boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);

		if (isVisibleToUser && !sent)
		{
			Debug.out(getPage());

			if (getPage() != null)
			{
				String appTitle = UiSettings.getInstance().getTextProcessor().process(getPage().getTitle().getContent());
				Debug.out(appTitle);
				//StatsManager.getInstance().registerPage(getString(R.string.app_name) + " > " + appTitle, getActivity());
			}
		}
	}
}
