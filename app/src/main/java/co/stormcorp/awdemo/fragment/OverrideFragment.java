package co.stormcorp.awdemo.fragment;

import android.os.Bundle;

import com.cube.storm.ui.fragment.StormListFragment;
import com.cube.storm.ui.model.list.TextListItem;
import com.cube.storm.ui.model.property.TextProperty;

/**
 * Overrides a storm fragment, page id 38
 *
 * @author Callum Taylor
 * @project StormExample
 */
public class OverrideFragment extends StormListFragment
{
	@Override public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		getActivity().setTitle("Overridden page");

		getListView().setAdapter(null);
		getAdapter().addItem(0, new TestView());
		getAdapter().notifyDataSetChanged();
		getListView().setAdapter(getAdapter());
	}

	public static class TestView extends TextListItem
	{
		public TestView()
		{
			this.description = new TextProperty()
			{
				@Override public String getContent()
				{
					return "_OVERRIDE_TEXT";
				}
			};
		}

		@Override public String getClassName()
		{
			return "TextListItem";
		}
	}
}
