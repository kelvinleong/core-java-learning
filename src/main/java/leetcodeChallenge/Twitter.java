package leetcodeChallenge;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Twitter {
    private Map<Integer, List<Integer>> relations;
    private Map<Integer, List<TwittMsg>> personalTweets;
    private Map<Integer, PriorityQueue<TwittMsg>> newsFeeds;
    private static AtomicInteger t = new AtomicInteger(0);

    private class TwittMsg {
        private int id;
        private int timeStamp;

        public TwittMsg (int id) {
            this.id = id;
            this.timeStamp = t.addAndGet(1);
        }

        public int getTimeStamp() { return timeStamp; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof TwittMsg)) {
                return false;
            }

            return ((TwittMsg) o).id == this.id;
        }
    }

    /** Initialize your data structure here. */
    public Twitter() {
        relations = new HashMap<>();
        personalTweets = new HashMap<>();
        newsFeeds = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        TwittMsg msg = new TwittMsg(tweetId);
        personalTweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(msg);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<TwittMsg> q = new PriorityQueue<>(10, Comparator.comparing(TwittMsg::getTimeStamp).reversed());
        q.addAll(personalTweets.getOrDefault(userId, new ArrayList<>()));
        relations.getOrDefault(userId, new ArrayList<>()).forEach(f -> q.addAll(personalTweets.getOrDefault(f, new ArrayList<>())));

        List<Integer> feeds = new ArrayList<>();
        int count = 0;
        while (q.peek() != null && count < 10) {
            feeds.add(q.poll().id);
            ++count;
        }
        return feeds;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followeeId == followerId || relations.getOrDefault(followerId, new ArrayList<>()).contains(followeeId)) return;

        relations.computeIfAbsent(followerId, k -> new ArrayList<>(Arrays.asList())).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) return;

        relations.computeIfAbsent(followerId, k -> new ArrayList<>(Arrays.asList())).remove(Integer.valueOf(followeeId));
    }

}
