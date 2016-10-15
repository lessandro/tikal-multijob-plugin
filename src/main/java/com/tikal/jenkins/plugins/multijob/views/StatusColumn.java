package com.tikal.jenkins.plugins.multijob.views;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumnDescriptor;

public class StatusColumn extends MultiJobListViewColumn {
	@DataBoundConstructor
	public StatusColumn() {
	}

	@Extension
	public static class DescriptorImpl extends ListViewColumnDescriptor {
		@Override
		public String getDisplayName() {
			return "MultiJob  - Status";
		}

		@Override
		public boolean shownByDefault() {
			return false;
		}
	}
}
