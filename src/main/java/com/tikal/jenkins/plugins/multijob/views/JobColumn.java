package com.tikal.jenkins.plugins.multijob.views;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumnDescriptor;

public class JobColumn extends MultiJobListViewColumn {
	@DataBoundConstructor
	public JobColumn() {
	}

	@Extension
	public static class DescriptorImpl extends ListViewColumnDescriptor {
		@Override
		public String getDisplayName() {
			return " MultiJob - Job";
		}

		@Override
		public boolean shownByDefault() {
			return false;
		}
	}
}
