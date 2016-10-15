package com.tikal.jenkins.plugins.multijob;

import java.util.List;

import hudson.model.Action;
import hudson.model.Cause.UpstreamCause;
import hudson.model.CauseAction;
import hudson.model.Job;
import hudson.model.Result;
import hudson.model.Run;
import hudson.model.queue.QueueTaskFuture;
import java.util.Collections;
import jenkins.model.ParameterizedJobMixIn;

public final class SubTask {
	final public Job subJob;
	final public PhaseJobsConfig phaseConfig;
	final public List<Action> actions;
	public QueueTaskFuture<?> future;
	final public MultiJobBuild multiJobBuild;
	public Result result;
	private boolean cancel;
	private boolean isShouldTrigger;

	SubTask(Job subJob, PhaseJobsConfig phaseConfig, List<Action> actions, MultiJobBuild multiJobBuild,
			boolean isShouldTrigger) {
		this.subJob = subJob;
		this.phaseConfig = phaseConfig;
		this.actions = actions;
		this.multiJobBuild = multiJobBuild;
		this.cancel = false;
		this.isShouldTrigger = isShouldTrigger;
	}

	public boolean isShouldTrigger() {
		return isShouldTrigger;
	}

	public boolean isCancelled() {
		return cancel;
	}

	public void cancelJob() {
		this.cancel = true;
	}

	public void generateFuture() {
            actions.add(new CauseAction(new UpstreamCause((Run) multiJobBuild)));
            
		this.future = new ParameterizedJobMixIn() {
			@Override
			protected Job asJob() {
				return subJob;
			}
		}.scheduleBuild2(-1, actions.toArray(new Action[actions.size()]));
	}
}