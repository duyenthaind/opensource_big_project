package com.group7.fruitswebsite.utilities;

import com.github.slugify.Slugify;

public class StringUtil {
	public static String seo(String text) {
		return new Slugify().slugify(text);
	}
}
