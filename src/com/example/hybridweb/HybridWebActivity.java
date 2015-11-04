package com.example.hybridweb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HybridWebActivity extends Activity {

	WebView myweb;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hybrid_web);
		
		myweb = (WebView) findViewById(R.id.myweb);
		
		WebSettings settings = myweb.getSettings();
		settings.setJavaScriptEnabled(true);
		
		myweb.addJavascriptInterface(new MyJavascriptInterface(), "android");
		// 자바스크립트에서 자바를 호출하려고 android.~~ 으로 사용할거임
		myweb.setWebViewClient(new MyWebViewClient());
		// a href 을 쓸때 웹뷰안에서 페이지로 이동하려고,  (default 는 엥커테그를 눌럿을때 새로운 페이지가 뜬다.) 
		myweb.setWebChromeClient(new WebChromeClient());
		// 웹브라우저에서 alert 을 쓰기위해서
	
		myweb.loadUrl("http://192.168.10.10:8082/web/index.jsp");
		//myweb.loadUrl("http://192.168.10.10");
		
	}
	
	class MyJavascriptInterface {
		
		@JavascriptInterface   // 메소드마다 해주어야한다.
		public void showToast(String msg){
			Log.i("###", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	class MyWebViewClient extends WebViewClient{
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.i("###", "url = " + url);
			view.loadUrl(url);
			return true;
		}
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hybrid_web, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
