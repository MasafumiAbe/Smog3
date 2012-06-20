/*=============================================================
 * Copyright(C) 2012 Masafumi Abe. All Rights Reserved.
 *
 * ------------------------------------------------------------
 * Smog3
 *
 * ------------------------------------------------------------
 * 2012/01/29    Masafumi Abe        êVãKçÏê¨
 *
===============================================================*/

package org.smog3.twitter4j;

import org.slim3.util.ApplicationMessage;
import org.smog3.common.Const;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * This class creates and does setting twitter4j.Twitter instance.
 * 
 * @author abemasafumi
 * 
 */
public class TwitterCreater {

    private TwitterCreater() {
    }

    /**
     * return the instance set the consumer.
     * 
     * @return
     */
    public static Twitter getInstance() {
        Twitter t = new TwitterFactory().getInstance();
        String key = ApplicationMessage.get(Const.PROPERTY_TWITTER_KEY);
        String secret = ApplicationMessage.get(Const.PROPERTY_TWITTER_SECRET);

        t.setOAuthConsumer(key, secret);

        return t;
    }

}
