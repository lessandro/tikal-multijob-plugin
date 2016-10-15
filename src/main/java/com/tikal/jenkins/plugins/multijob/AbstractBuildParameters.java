package com.tikal.jenkins.plugins.multijob;

import java.io.IOException;

import hudson.model.AbstractBuild;
import hudson.model.AbstractDescribableImpl;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.TaskListener;

public abstract class AbstractBuildParameters extends AbstractDescribableImpl<AbstractBuildParameters> {

	public abstract Action getAction(AbstractBuild<?, ?> build, TaskListener listener, AbstractProject project)
			throws IOException, InterruptedException;

	public static class DontTriggerException extends Exception {
	}
}
