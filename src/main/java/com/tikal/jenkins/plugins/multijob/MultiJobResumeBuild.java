package com.tikal.jenkins.plugins.multijob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import hudson.model.Action;
import hudson.model.Cause;
import hudson.model.CauseAction;
import hudson.model.ParametersAction;
import hudson.model.Queue;
import hudson.model.Run;
import jenkins.model.Jenkins;
import jenkins.model.RunAction2;

public class MultiJobResumeBuild implements RunAction2 {

	private final Run<?, ?> run;

	public MultiJobResumeBuild(Run<?, ?> run) {
		this.run = run;
	}

	@Override
	public String getIconFileName() {
		return "plugin/jenkins-multijob-plugin/tool32.png";
	}

	@Override
	public String getDisplayName() {
		return Messages.MultiJobResumeBuild_DisplayName();
	}

	@Override
	public String getUrlName() {
		return "resume";
	}

	public String getInfo() {
		return "Resume build";
	}

	public void doIndex(StaplerRequest req, StaplerResponse rsp) throws IOException {
		final MultiJobResumeControl control = new MultiJobResumeControl(run);
		List<Action> actions = copyBuildCauses();
		actions.add(control);
		actions.add(new CauseAction(new ResumeCause(run)));
		Jenkins.getInstance().getQueue().schedule2((Queue.Task) run.getParent(), 0, actions);
		rsp.sendRedirect2(Jenkins.getInstance().getRootUrl() + run.getParent().getUrl());
	}

	@Override
	public void onAttached(Run<?, ?> run) {
	}

	@Override
	public void onLoad(Run<?, ?> run) {
	}

	private List<Action> copyBuildCauses() {
		List<Action> actions = new ArrayList<Action>();
		boolean hasUserIdCause = false;
		for (Object cause : run.getCauses()) {
			if (cause instanceof Cause.UserIdCause) {
				hasUserIdCause = true;
				actions.add(new CauseAction(new Cause.UserIdCause()));
			} else {
				actions.add(new CauseAction((Cause) cause));
			}
		}
		if (!hasUserIdCause) {
			actions.add(new CauseAction(new Cause.UserIdCause()));
		}
		actions.addAll(run.getActions(ParametersAction.class));
		return actions;
	}
}
