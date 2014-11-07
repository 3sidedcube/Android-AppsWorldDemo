package co.stormcorp.awdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import co.stormcorp.awdemo.R;

public class StormIntroFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View shakeItView = inflater.inflate(R.layout.fragment_storm_intro, container, false);

		TextView appName = (TextView)shakeItView.findViewById(R.id.app_text);
		appName.setText(getString(R.string.your_in) + getString(R.string.app_name));

		Animation[] anims = new Animation[6];
		int[] timings = {22976, 18346, 6862, 15832, 19649, 29917};

		for (int index = 0; index < anims.length; index++)
		{
			anims[index] = AnimationUtils.loadAnimation(getActivity().getBaseContext(), R.anim.float_anim);
			anims[index].setDuration(timings[index]);
		}

		shakeItView.findViewById(R.id.power_icon).startAnimation(anims[0]);
		shakeItView.findViewById(R.id.settings_icon).startAnimation(anims[1]);
		shakeItView.findViewById(R.id.settings_2_icon).startAnimation(anims[2]);
		shakeItView.findViewById(R.id.slideshow_icon).startAnimation(anims[3]);
		shakeItView.findViewById(R.id.tools_icon).startAnimation(anims[4]);
		shakeItView.findViewById(R.id.tools2_icon).startAnimation(anims[5]);

		return shakeItView;
	}
}
