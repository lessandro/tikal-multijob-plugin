package com.tikal.jenkins.plugins.multijob.views;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumnDescriptor;

public class ConsoleColumn extends MultiJobListViewColumn {
	@DataBoundConstructor
	public ConsoleColumn() {
	}

	@Extension
	public static class DescriptorImpl extends ListViewColumnDescriptor {
		@Override
		public String getDisplayName() {
			return "MultiJob - Console";
		}

		@Override
		public boolean shownByDefault() {
			return false;
		}
	}
}
