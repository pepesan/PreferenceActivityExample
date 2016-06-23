package com.lagoman.preferenceactivityexample;

import java.util.List;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected boolean isValidFragment(String fragmentName) {
		return NotificationOptionsFragment.class.getName().equals(fragmentName)
				|| CachingOptionsFragment.class.getName().equals(fragmentName)
				/*
				|| (or) any other fragment that represents header
				*/
				;
	}

	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.preferences, target);
	}

	public static class NotificationOptionsFragment extends PreferenceFragment {
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preference_notification_options);
		}
	}

	public static class CachingOptionsFragment extends PreferenceFragment {
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preference_caching_options);
			
			String totalCache = "3.28 MB"; //calculate cache size
			Preference clearCachePref = findPreference("com.lagoman.preferenceactivityexample.SettingsActivity.clear_cache");
			clearCachePref.setSummary(totalCache);
			clearCachePref.setOnPreferenceClickListener(clearCache);
		}
		
		private OnPreferenceClickListener clearCache = new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				//do some cache clearing
				Toast.makeText(getActivity(), "Cache cleared", Toast.LENGTH_SHORT).show();
				preference.setSummary("0.0 MB");
				return true;
			}
		};
	}
}
