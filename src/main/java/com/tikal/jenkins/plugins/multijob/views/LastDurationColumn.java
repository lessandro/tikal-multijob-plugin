package com.tikal.jenkins.plugins.multijob.views;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumnDescriptor;

public class LastDurationColumn extends MultiJobListViewColumn {
	@DataBoundConstructor
	public LastDurationColumn() {
	}

	@Extension
	public static class DescriptorImpl extends ListViewColumnDescriptor {
		@Override
		public String getDisplayName() {
			return "MultiJob - Last Duration";
		}

		@Override
		public boolean shownByDefault() {
			return false;
		}
	}
}
