package com.prime.pojo.smoketestdata;

import java.util.ArrayList;

public class SiteData {
	public SiteId siteId;
	public ArrayList<String> articleUris;
	public ArrayList<String> articleWithoutFullTextUris;
	public ArrayList<String> articleWithoutPdfUris;
	public ArrayList<String> issueUris;
	public ArrayList<String> journalUris;

	public SiteId getSiteId() {
		return siteId;
	}

	public void setSiteId(SiteId siteId) {
		this.siteId = siteId;
	}

	public ArrayList<String> getArticleUris() {
		return articleUris;
	}

	public void setArticleUris(ArrayList<String> articleUris) {
		this.articleUris = articleUris;
	}

	public ArrayList<String> getArticleWithoutFullTextUris() {
		return articleWithoutFullTextUris;
	}

	public void setArticleWithoutFullTextUris(ArrayList<String> articleWithoutFullTextUris) {
		this.articleWithoutFullTextUris = articleWithoutFullTextUris;
	}

	public ArrayList<String> getArticleWithoutPdfUris() {
		return articleWithoutPdfUris;
	}

	public void setArticleWithoutPdfUris(ArrayList<String> articleWithoutPdfUris) {
		this.articleWithoutPdfUris = articleWithoutPdfUris;
	}

	public ArrayList<String> getIssueUris() {
		return issueUris;
	}

	public void setIssueUris(ArrayList<String> issueUris) {
		this.issueUris = issueUris;
	}

	public ArrayList<String> getJournalUris() {
		return journalUris;
	}

	public void setJournalUris(ArrayList<String> journalUris) {
		this.journalUris = journalUris;
	}

	public ArrayList<String> getAssetUris() {
		return assetUris;
	}

	public void setAssetUris(ArrayList<String> assetUris) {
		this.assetUris = assetUris;
	}

	public ArrayList<String> getPdfUris() {
		return pdfUris;
	}

	public void setPdfUris(ArrayList<String> pdfUris) {
		this.pdfUris = pdfUris;
	}

	public ArrayList<String> assetUris;
	public ArrayList<String> pdfUris;

}
