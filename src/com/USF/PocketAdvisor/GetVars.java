package com.USF.PocketAdvisor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.widget.TextView;

public class GetVars  extends AsyncTask<String, Void, String>{
	String termVar = "Error";
	@Override
	protected String doInBackground(String... params){
		Document searchPage = null;
		try {
			searchPage = Jsoup.connect("http://www.registrar.usf.edu/ssearch/search.php").get();
		} catch (IOException e) {
			termVar = "Error";
		}
		Elements termVarE = searchPage.select("td:matchesOwn(TERM) + td > select");
		termVar = termVarE.attr("name");
		return termVar;
	}
	@Override
	protected void onPostExecute(String result){
	}
	@Override
	protected void onPreExecute(){
	}
	public static String getTermVar(){
		new GetVars().execute();
		return termVar;
	}
}