package co.stormcorp.lib.factory;

import android.content.Context;

public class StatsManager
{
	private static StatsManager instance;
	public static StatsManager getInstance()
	{
		if (instance == null)
		{
			instance = new StatsManager();
		}

		return instance;
	}

	public void stopTrackers()
	{
		//if (ModuleSettings.MODULE_GANALYTICS_ENABLED)
		{
//			EasyTracker.getInstance().dispatch();
		}
	}

	public void registerEvent(String pageName, String action, String label)
	{
		//f (ModuleSettings.MODULE_GANALYTICS_ENABLED)
		{
//			EasyTracker.getTracker().sendEvent(pageName, action, label, 1L);
//			EasyTracker.getInstance().dispatch();
		}
	}

	/**
	 * TODO: Add page ID field
	 * @param pageName
	 */
	public void registerPage(String pageName, Context context)
	{
		//if (ModuleSettings.MODULE_GANALYTICS_ENABLED)
		{
//			EasyTracker.getTracker().sendView(pageName);
//			EasyTracker.getInstance().dispatch();
		}
	}
}
