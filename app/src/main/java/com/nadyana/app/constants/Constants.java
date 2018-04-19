package com.nadyana.app.constants;

public class Constants {

    public static final String recent_posts_url = "http://www.nadyana.com/wp-json/wp/v2/posts/filter[category_name]=app&per_page=100&fields=id,link,title.rendered,better_featured_image.source_url";

    public static final String categories_list_url = "http://www.nadyana.com/wp-json/wp/v2/categories?fields=id,name";

    public static final String posts_of_single_category_url = "http://www.nadyana.com/wp-json/wp/v2/posts?categories=CATEGORY_ID&fields=id,link,title.rendered,better_featured_image.source_url";

    public static final String single_post_content_url = "http://www.nadyana.com/wp-json/wp/v2/posts/POST_ID?fields=content";

    public static final String single_page_content_url = "http://www.nadyana.com/wp-json/wp/v2/pages/PAGE_ID?fields=content";

}
