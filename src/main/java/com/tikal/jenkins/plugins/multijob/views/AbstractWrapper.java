package com.tikal.jenkins.plugins.multijob.views;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.acegisecurity.AccessDeniedException;

import hudson.model.HealthReport;
import hudson.model.Hudson;
import hudson.model.Item;
import hudson.model.ItemGroup;
import hudson.model.TopLevelItem;
import hudson.model.TopLevelItemDescriptor;
import hudson.search.Search;
import hudson.search.SearchIndex;
import hudson.security.ACL;
import hudson.security.Permission;

abstract public class AbstractWrapper implements TopLevelItem {
	@Override
	public void onLoad(ItemGroup<? extends Item> parent, String name) throws IOException {
	}

	@Override
	public void onCopiedFrom(Item src) {
	}

	@Override
	public void onCreatedFromScratch() {
	}

	@Override
	public void save() throws IOException {
	}

	@Override
	public void delete() throws IOException, InterruptedException {
	}

	@Override
	public void checkPermission(Permission permission) throws AccessDeniedException {
	}

	@Override
	public String getUrl() {
		return null;
	}

	@Override
	public String getShortUrl() {
		return null;
	}

	@Override
	@Deprecated
	public String getAbsoluteUrl() {
		return null;
	}

	@Override
	public File getRootDir() {
		return null;
	}

	@Override
	public Search getSearch() {
		return null;
	}

	@Override
	public String getSearchName() {
		return null;
	}

	@Override
	public String getSearchUrl() {
		return null;
	}

	@Override
	public SearchIndex getSearchIndex() {
		return null;
	}

	@Override
	public ACL getACL() {
		return null;
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return true;
	}

	@Override
	public Hudson getParent() {
		return Hudson.getInstance();
	}

	@Override
	public TopLevelItemDescriptor getDescriptor() {
		return null;
	}

	public HealthReport getBuildHealth() {
		return null;
	}

	public List<HealthReport> getBuildHealthReports() {
		return null;
	}

	public boolean isBuildable() {
		return false;
	}

}
