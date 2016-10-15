package com.tikal.jenkins.plugins.multijob.views;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumnDescriptor;

public class LastFailureColumn extends MultiJobListViewColumn {
	@DataBoundConstructor
	public LastFailureColumn() {
	}

	@Extension
	public static class DescriptorImpl extends ListViewColumnDescriptor {
		@Override
		public String getDisplayName() {
			return "MultiJob - Last Failure";
		}

		@Override
		public boolean shownByDefault() {
			return false;
		}
	}
}
