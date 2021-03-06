package com.tikal.jenkins.plugins.multijob.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import hudson.model.AbstractBuild;
import hudson.model.BallColor;
import hudson.model.Item;
import hudson.model.ItemGroup;
import hudson.model.Job;
import hudson.model.Result;
import jenkins.model.Jenkins;

@SuppressWarnings("rawtypes")
public class PhaseWrapper extends AbstractWrapper {

	final int nestLevel;

	final String phaseName;

	final boolean isConditional;

	public PhaseWrapper(int nestLevel, String phaseName, boolean isConditional) {
		this.nestLevel = nestLevel;
		this.phaseName = phaseName;
		this.isConditional = isConditional;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<? extends Job> getAllJobs() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public String getName() {
		return phaseName;
	}

	@Override
	public String getFullName() {
		return phaseName;
	}

	@Override
	public String getDisplayName() {
		return phaseName;
	}

	@Override
	public String getFullDisplayName() {
		return phaseName;
	}

	public int getNestLevel() {
		return nestLevel;
	}

	public boolean isConditional() {
		return isConditional;
	}

	// public AbstractProject getProject() {
	// return project;
	// }

	public BallColor getIconColor() {
		try {
			Result result = null;
			AbstractBuild worseBuild = null;
			for (BuildState buildState : childrenBuildState) {
				Job project = (Job) Jenkins.getInstance().getItemByFullName(buildState.getJobName());
				AbstractBuild build = (AbstractBuild) project.getBuildByNumber(buildState.getLastBuildNumber());
				if (build != null) {
					if (result == null) {
						result = build.getResult();
						worseBuild = build;
					} else {
						if (build.getResult().isWorseThan(worseBuild.getResult())) {
							worseBuild = build;
						}
					}
				}
			}
			if (worseBuild != null) {
				return worseBuild.getIconColor();
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public String getCss() {
		StringBuilder builder = new StringBuilder();
		builder.append("padding-left:");
		builder.append(String.valueOf((getNestLevel() + 1) * 20));
		builder.append("px;");
		builder.append("font-style:italic;font-size:smaller;font-weight:bold;");
		return builder.toString();
	}

	public String getPhaseName() {
		return phaseName;
	}

	public boolean isPhase() {
		return true;
	}

	List<BuildState> childrenBuildState = new ArrayList<BuildState>();

	public void addChildBuildState(BuildState jobBuildState) {
		childrenBuildState.add(jobBuildState);
	}

	@Override
	public String getRelativeNameFrom(ItemGroup g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRelativeNameFrom(Item item) {
		// TODO Auto-generated method stub
		return null;
	}
}
