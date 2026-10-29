package jp.ieneko.mannenreki;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
public class MainActivity extends Activity {
 private static final int PICK_FILE=41;
 private WebView webView;
 private ValueCallback<Uri[]> pendingChooser;
 @Override public void onCreate(Bundle state){
  super.onCreate(state);webView=new WebView(this);setContentView(webView);
  WebSettings s=webView.getSettings();
  s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(true);
  s.setAllowFileAccessFromFileURLs(false);s.setAllowUniversalAccessFromFileURLs(false);
  s.setJavaScriptCanOpenWindowsAutomatically(false);s.setDefaultTextEncodingName("UTF-8");
  webView.setWebViewClient(new WebViewClient(){
   @Override public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest req){
    String u=req.getUrl().toString();if(u.startsWith("file:///android_asset/"))return false;
    if(u.startsWith("https://")){try{startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(u)));}catch(Exception e){}}
    return true;
   }
  });
  webView.setWebChromeClient(new WebChromeClient(){
   @Override public boolean onShowFileChooser(WebView w,ValueCallback<Uri[]> cb,FileChooserParams p){
    if(pendingChooser!=null)pendingChooser.onReceiveValue(null);pendingChooser=cb;
    try{Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT);i.setType("*/*");i.addCategory(Intent.CATEGORY_OPENABLE);startActivityForResult(i,PICK_FILE);}
    catch(Exception e){pendingChooser.onReceiveValue(null);pendingChooser=null;return false;}return true;
   }
  });webView.loadUrl("file:///android_asset/index.html");
 }
 @Override protected void onActivityResult(int request,int result,Intent data){
  super.onActivityResult(request,result,data);if(request==PICK_FILE&&pendingChooser!=null){
   pendingChooser.onReceiveValue(result==RESULT_OK&&data!=null&&data.getData()!=null?new Uri[]{data.getData()}:null);pendingChooser=null;
  }
 }
 @Override public void onBackPressed(){if(webView.canGoBack())webView.goBack();else super.onBackPressed();}
 @Override protected void onDestroy(){if(pendingChooser!=null)pendingChooser.onReceiveValue(null);webView.destroy();super.onDestroy();}
}