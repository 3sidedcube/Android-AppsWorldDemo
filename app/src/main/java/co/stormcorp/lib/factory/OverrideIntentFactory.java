package co.stormcorp.lib.factory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cube.storm.UiSettings;
import com.cube.storm.ui.activity.StormActivity;
import com.cube.storm.ui.data.FragmentIntent;
import com.cube.storm.ui.lib.factory.IntentFactory;
import com.cube.storm.ui.model.Model;
import com.cube.storm.ui.model.descriptor.PageDescriptor;
import com.cube.storm.ui.model.page.Page;
import com.cube.storm.ui.model.page.PageCollection;
import com.cube.storm.util.lib.debug.Debug;

import co.stormcorp.awdemo.ContentActivity;
import co.stormcorp.awdemo.MainActivity;
import co.stormcorp.awdemo.fragment.OverrideFragment;
import co.stormcorp.awdemo.fragment.StormIntroFragment;

/**
 * Example intent factory for overriding fragment
 *
 * @author Callum Taylor
 */
public class OverrideIntentFactory extends IntentFactory
{
	@Nullable @Override public FragmentIntent getFragmentIntentForPageDescriptor(@NonNull PageDescriptor pageDescriptor)
	{
		FragmentIntent ret = super.getFragmentIntentForPageDescriptor(pageDescriptor);

		if ("app://native/pages/intro".equalsIgnoreCase(pageDescriptor.getSrc()))
		{
			if (ret != null)
			{
				ret.setFragment(StormIntroFragment.class);
			}
			else
			{
				Bundle args = new Bundle();
				args.putSerializable(StormActivity.EXTRA_URI, pageDescriptor.getSrc());

				ret = new FragmentIntent(StormIntroFragment.class, null, args);
			}
		}
		else if ("cache://pages/39.json".equalsIgnoreCase(pageDescriptor.getSrc()))
		{
			if (ret != null)
			{
				ret.setFragment(OverrideFragment.class);
			}
			else
			{
				Bundle args = new Bundle();
				args.putSerializable(StormActivity.EXTRA_URI, pageDescriptor.getSrc());

				ret = new FragmentIntent(OverrideFragment.class, null, args);
			}
		}

		return ret;
	}

	@Nullable @Override public Intent getIntentForPageDescriptor(@NonNull Context context, @NonNull PageDescriptor pageDescriptor)
	{
		Intent ret = super.getIntentForPageDescriptor(context, pageDescriptor);

		Class<? extends Model> pageType = UiSettings.getInstance().getViewFactory().getModelForView(pageDescriptor.getType());

		Debug.out(ret);
		Debug.out(pageType);

		if (pageType != null)
		{
			if (PageCollection.class.isAssignableFrom(pageType))
			{
				if (ret != null)
				{
					Bundle extras = ret.getExtras();
					ret = new Intent(context, MainActivity.class);
					ret.putExtras(extras);
				}
				else
				{
					Bundle args = new Bundle();
					args.putSerializable(StormActivity.EXTRA_URI, pageDescriptor.getSrc());

					ret = new Intent(context, MainActivity.class);
					ret.putExtras(args);
				}
			}
			else if (Page.class.isAssignableFrom(pageType))
			{
				if (ret != null)
				{
					Bundle extras = ret.getExtras();
					ret = new Intent(context, ContentActivity.class);
					ret.putExtras(extras);
				}
				else
				{
					Bundle args = new Bundle();
					args.putSerializable(StormActivity.EXTRA_URI, pageDescriptor.getSrc());

					ret = new Intent(context, ContentActivity.class);
					ret.putExtras(args);
				}
			}
		}

		return ret;
	}
}
