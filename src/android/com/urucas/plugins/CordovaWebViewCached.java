package com.urucas.plugins;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.IceCreamCordovaWebViewClient;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CordovaWebViewCached extends IceCreamCordovaWebViewClient{

	private static final String TAG = "CordovaWebViewCached";

	public CordovaWebViewCached(CordovaInterface cordova, CordovaWebView view) {
		super(cordova, view);
	}

	@Override
	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

		if(url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".gif") || url.endsWith(".mp4") ||
		   url.endsWith(".avi") || url.endsWith(".flv") || url.endsWith(".wmv") || url.endsWith(".mov") || url.endsWith(".webm")){
			try {
				Log.i(TAG, "Overriding file url load");
				Log.i(TAG, url);

				ImageCache _cache = ImageCachePlugin.getCache();
				String cacheFileName = ImageCache.md5(url);
				if(url.endsWith(".jpg") || url.endsWith(".jpeg")){
					_cache.register(url, cacheFileName, "image/jpg", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".png")){
					_cache.register(url, cacheFileName, "image/png", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".gif")){
					_cache.register(url, cacheFileName, "image/gif", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".mp4")){
					_cache.register(url, cacheFileName, "video/mp4", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".avi")){
					_cache.register(url, cacheFileName, "video/avi", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".flv")){
					_cache.register(url, cacheFileName, "video/flv", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".wmv")){
					_cache.register(url, cacheFileName, "video/wmv", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".mov")){
					_cache.register(url, cacheFileName, "video/mov", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".webm")){
					_cache.register(url, cacheFileName, "video/webm", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}
				return _cache.load(url);
				
			}catch(Exception e){}
		}
		return super.shouldInterceptRequest(view, url);
	}

}

