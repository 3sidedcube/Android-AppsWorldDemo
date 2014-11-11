package co.stormcorp.lib.factory;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.android.gms.wallet.fragment.Dimension;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.obj.entity.JsonEntity;
import net.callumtaylor.asynchttp.response.JsonResponseHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
			EasyTracker.getInstance().dispatch();
		}
	}

	public void registerEvent(String pageName, String action, String label)
	{
		//f (ModuleSettings.MODULE_GANALYTICS_ENABLED)
		{
			EasyTracker.getTracker().sendEvent(pageName, action, label, 1L);
			EasyTracker.getInstance().dispatch();
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
			EasyTracker.getTracker().sendView(pageName);
		}
	}
}
