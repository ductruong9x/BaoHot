package com.truongtvd.baomoi.common;

public class Constants {

	public static final String FANPAGE_KEY = "462803120472407";
	public static final String FANPAGE_KEY_SOICAU="268593233235338";
	public static final String FANPAGE_URL = "http://graph.facebook.com/462803120472407";
	public static final String FANPAGE_URL_SOICAU = "http://graph.facebook.com/268593233235338";
	public static String QUERY_INFO = "SELECT post_id, actor_id,created_time,description FROM stream WHERE filter_key in (SELECT filter_key FROM stream_filter WHERE uid =340505782696997 AND type = 'newsfeed') LIMIT ";
	public static final String APP_ID="290657457761563";
	public static final String SERCEP_KEY="ca8e5203308a8be8607bafbc3f28ff6b";
}
