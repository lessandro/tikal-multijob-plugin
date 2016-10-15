package com.tikal.jenkins.plugins.multijob.views;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumnDescriptor;

public class WeatherColumn extends MultiJobListViewColumn {
	@DataBoundConstructor
	public WeatherColumn() {
	}

	@Extension
	public static class DescriptorImpl extends ListViewColumnDescriptor {
		@Override
		public String getDisplayName() {
			return "MultiJob - Weather";
		}

		@Override
		public boolean shownByDefault() {
			return false;
		}
	}
}
