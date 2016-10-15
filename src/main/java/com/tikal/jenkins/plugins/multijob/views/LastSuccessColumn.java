package com.tikal.jenkins.plugins.multijob.views;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumnDescriptor;

public class LastSuccessColumn extends MultiJobListViewColumn {
	@DataBoundConstructor
	public LastSuccessColumn() {
	}

	@Extension
	public static class DescriptorImpl extends ListViewColumnDescriptor {
		@Override
		public String getDisplayName() {
			return "MultiJob - Last Success";
		}

		@Override
		public boolean shownByDefault() {
			return false;
		}
	}
}
