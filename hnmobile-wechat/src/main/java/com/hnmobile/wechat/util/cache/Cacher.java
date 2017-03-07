package com.hnmobile.wechat.util.cache;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Cacher {

	private static final Logger log = Logger.getLogger(Cacher.class);
	
	private static final String path = "/ehcache.xml";

	private URL url;

	private static CacheManager manager;

	private static Cacher ehCache;
	
	private static boolean isStartup=false;

	protected Cacher() {
		url = getClass().getResource(path);
		manager = CacheManager.create(url);
	}
	
	public static void startupCache(){
		synchronized (Cacher.class) {
		    if (ehCache== null) {
				ehCache= new Cacher();
			}
			isStartup = true;
		}
	}
	
	public static void shutdownCache(){
		synchronized (Cacher.class) {
			isStartup=false;
			manager.shutdown();
		}
	}

	protected static Cacher getInstance() {
		synchronized (Cacher.class) {
			if(!isStartup){
				log.warn(" warning , cache do not be started .............");
				return null;
			}
			if (ehCache==null) {
				ehCache= new Cacher();
			}
		}
		return ehCache;
	}

	protected void put(String cacheName, String key, Object value) {
		if( !isStartup ){
			log.warn(" warning , cache do not be started .............");
			return;
		}
		Cache cache = manager.getCache(cacheName);
		if(cache==null){
			manager.addCache(cacheName); 
			cache = manager.getCache(cacheName);
		}
		Element element = new Element(key, value);
		cache.put(element);
	}

	protected void addCache( String cacheName ){
		if( !isStartup ){
			log.warn(" warning , cache do not be started .............");
			return;
		}
		synchronized (this){
			Cache cache = manager.getCache(cacheName);
			if(cache==null){
				manager.addCache(cacheName); 
			}
		}
	}
	
	protected Object get(String cacheName, String key) {
		if( !isStartup ) {
			log.warn(" warning , cache do not be started .............");
			return null;
		}
		Cache cache = manager.getCache(cacheName);
		if(cache==null){
			addCache(cacheName );
			cache = manager.getCache(cacheName);
		}
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	protected Cache get(String cacheName) {
		if( !isStartup ) {
			log.warn(" warning , cache do not be started .............");
			return null;
		}
		return manager.getCache(cacheName);
	}

	protected void remove(String cacheName, String key) {
		if( !isStartup ) {
			log.warn(" warning , cache do not be started .............");
			return;
		}
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);
	}
	
	public static void main( String[]args ){
		Cacher.startupCache();
		Cacher cache = Cacher.getInstance();
		cache.put(CacheNames.USER_FUNC, "test", "tse");
		System.out.println(cache.get(CacheNames.USER_FUNC, "test"));
		Cacher.shutdownCache();
	}

}


