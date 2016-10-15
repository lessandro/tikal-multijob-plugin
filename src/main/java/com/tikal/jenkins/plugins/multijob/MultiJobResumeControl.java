package com.tikal.jenkins.plugins.multijob;

import hudson.model.Action;
import hudson.model.Run;

public class MultiJobResumeControl implements Action {

	private final Run<?, ?> run;

	public MultiJobResumeControl(Run<?, ?> run) {
		this.run = run;
	}

	@Override
	public String getIconFileName() {
		return null;
	}

	@Override
	public String getDisplayName() {
		return null;
	}

	@Override
	public String getUrlName() {
		return null;
	}

	public Run<?, ?> getRun() {
		return run;
	}
}
