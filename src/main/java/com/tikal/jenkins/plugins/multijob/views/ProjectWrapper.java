package com.tikal.jenkins.plugins.multijob.views;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.acegisecurity.AccessDeniedException;

import com.tikal.jenkins.plugins.multijob.MultiJobProject;

import hudson.model.AbstractProject;
import hudson.model.BallColor;
import hudson.model.HealthReport;
import hudson.model.Hudson;
import hudson.model.Item;
import hudson.model.ItemGroup;
import hudson.model.Job;
import hudson.model.Result;
import hudson.model.Run;
import hudson.model.TopLevelItemDescriptor;
import hudson.search.Search;
import hudson.search.SearchIndex;
import hudson.security.ACL;
import hudson.security.Permission;

@SuppressWarnings("rawtypes")
public class ProjectWrapper extends AbstractWrapper {

	final MultiJobProject multijob;

	final BuildState buildState;

	final Job project;

	final int nestLevel;

	public ProjectWrapper(MultiJobProject multijob, Job project, BuildState buildState, int nestLevel) {
		this.project = project;
		this.multijob = multijob;
		this.nestLevel = nestLevel;
		this.buildState = buildState;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<? extends Job> getAllJobs() {
		return project.getAllJobs();
	}

	@Override
	public String getName() {
		return project.getName();
	}

	@Override
	public String getFullName() {
		return project.getFullName();
	}

	@Override
	public String getDisplayName() {
		return project.getDisplayName();
	}

	@Override
	public String getFullDisplayName() {
		return project.getFullDisplayName();
	}

	@Override
	public String getUrl() {
		return project.getUrl();
	}

	@Override
	public String getShortUrl() {
		return project.getShortUrl();
	}

	@Override
	@Deprecated
	public String getAbsoluteUrl() {
		return project.getAbsoluteUrl();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void onLoad(ItemGroup<? extends Item> parent, String name) throws IOException {
		project.onLoad(parent, name);
	}

	@Override
	public void onCopiedFrom(Item src) {
		project.onCopiedFrom(src);
	}

	@Override
	public void onCreatedFromScratch() {
		project.onCreatedFromScratch();
	}

	@Override
	public void save() throws IOException {
		project.save();
	}

	@Override
	public void delete() throws IOException, InterruptedException {
		project.delete();
	}

	@Override
	public File getRootDir() {
		return project.getRootDir();
	}

	@Override
	public Search getSearch() {
		return project.getSearch();
	}

	@Override
	public String getSearchName() {
		return project.getSearchName();
	}

	@Override
	public String getSearchUrl() {
		return project.getSearchUrl();
	}

	@Override
	public SearchIndex getSearchIndex() {
		return project.getSearchIndex();
	}

	@Override
	public ACL getACL() {
		return project.getACL();
	}

	@Override
	public void checkPermission(Permission permission) throws AccessDeniedException {
		project.checkPermission(permission);
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return project.hasPermission(permission);
	}

	@Override
	public Hudson getParent() {
		return Hudson.getInstance();
	}

	public int getNestLevel() {
		return nestLevel;
	}

	@Override
	public TopLevelItemDescriptor getDescriptor() {
		return (TopLevelItemDescriptor) project.getDescriptorByName(project.getClass().getName());
	}

	Run findLastBuildForResult(Result result) {
		if (buildState == null) {
			return null;
		}
		if (Result.SUCCESS.equals(result)) {
			return project.getBuildByNumber(buildState.getLastSuccessBuildNumber());
		}
		if (Result.FAILURE.equals(result)) {
			return project.getBuildByNumber(buildState.getLastFailureBuildNumber());
		}
		return project.getBuildByNumber(buildState.getLastBuildNumber());
	}

	public Run getLastFailedBuild() {
		return findLastBuildForResult(Result.FAILURE);
	}

	public Run getLastSuccessfulBuild() {
		return findLastBuildForResult(Result.SUCCESS);
	}

	public Run getLastBuild() {
		return findLastBuildForResult(null);
	}

	public Job getProject() {
		return project;
	}

	public BallColor getIconColor() {
		if (project instanceof AbstractProject && ((AbstractProject) project).isDisabled())
			return BallColor.DISABLED;
		Run lastBuild = getLastBuild();
		while (lastBuild != null && lastBuild.hasntStartedYet())
			lastBuild = lastBuild.getPreviousBuild();

		if (lastBuild != null)
			return lastBuild.getIconColor();
		else
			return BallColor.GREY;
	}

	public String getCss() {
		StringBuilder builder = new StringBuilder();
		if (project instanceof MultiJobProject) {
			builder.append("font-weight:bold;");
		}
		builder.append("padding-left:");
		builder.append(String.valueOf((getNestLevel() + 1) * 20));
		builder.append("px");
		return builder.toString();
	}

	@Override
	public HealthReport getBuildHealth() {
		return getProject().getBuildHealth();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HealthReport> getBuildHealthReports() {
		return getProject().getBuildHealthReports();
	}

	@Override
	public boolean isBuildable() {
		return multijob == null && getProject().isBuildable();
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