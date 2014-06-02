package com.truongtvd.baomoi.common;

public class Constants {

	public static final String FANPAGE_KEY_HOT = "1420720208144989";
	public static final String FANPAGE_KEY_TECH = "122102927812763";
	public static final String FANPAGE_URL_HOT = "http://graph.facebook.com/1420720208144989";
	public static final String FANPAGE_URL_TECH = "http://graph.facebook.com/122102927812763";
	public static String QUERY_INFO = "SELECT post_id, actor_id,created_time,description FROM stream WHERE filter_key in (SELECT filter_key FROM stream_filter WHERE uid =340505782696997 AND type = 'newsfeed') LIMIT ";
	public static final String APP_ID = "290657457761563";
	public static final String SERCEP_KEY = "ca8e5203308a8be8607bafbc3f28ff6b";
}
