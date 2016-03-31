package com.proxerme.library.connection;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.Conference;
import com.proxerme.library.entity.LoginData;
import com.proxerme.library.entity.Message;
import com.proxerme.library.entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for parsing all different answers, received by the {@link ProxerConnection}.
 *
 * @author Ruben Gees
 */
class ProxerParser {

    public static final String MESSAGES_ARRAY = "messages";
    public static final String MESSAGES_ID = "id";
    public static final String MESSAGES_FROM_ID = "fromid";
    public static final String MESSAGES_MESSAGE = "message";
    public static final String MESSAGES_ACTION = "action";
    public static final String MESSAGES_TIME = "timestamp";
    public static final String MESSAGES_DEVICE = "device";
    public static final String MESSAGES_USERNAME = "username";
    public static final String MESSAGES_IMAGE = "avatar";
    private static final String NEWS_ARRAY = "notifications";
    private static final String NEWS_ID = "nid";
    private static final String NEWS_TIME = "time";
    private static final String NEWS_DESCRIPTION = "description";
    private static final String NEWS_IMAGE_ID = "image_id";
    private static final String NEWS_SUBJECT = "subject";
    private static final String NEWS_HITS = "hits";
    private static final String NEWS_THREAD = "thread";
    private static final String NEWS_USER_ID = "uid";
    private static final String NEWS_USER_NAME = "uname";
    private static final String NEWS_POSTS = "posts";
    private static final String NEWS_CATEGORY_ID = "catid";
    private static final String NEWS_CATEGORY = "catname";
    private static final String LOGIN_ID = "uid";
    private static final String LOGIN_IMAGE = "avatar";
    private static final String CONFERENCES_ARRAY = "conferences";
    private static final String CONFERENCES_ID = "id";
    private static final String CONFERENCES_TOPIC = "topic";
    private static final String CONFERENCES_PARTICIPANT_AMOUNT = "count";
    private static final String CONFERENCES_IS_CONFERENCE = "conference";
    private static final String CONFERENCES_TIME = "timestamp_end";
    private static final String CONFERENCES_IS_READ = "read";
    private static final String CONFERENCES_IMAGE = "image";

    /**
     * Parses a raw JSON, returned by the server and returns a List of {@link News}.
     *
     * @param object The JSON object.
     * @return The List of {@link News}.
     * @throws JSONException If the JSON is not formatted as expected.
     */
    @NonNull
    public static List<News> parseNewsJSON(@NonNull JSONObject object) throws JSONException {
        JSONArray newsArray = object.getJSONArray(NEWS_ARRAY);
        List<News> result = new ArrayList<>(newsArray.length());

        for (int i = 0; i < newsArray.length(); i++) {
            JSONObject newsObject = newsArray.getJSONObject(i);

            result.add(new News(newsObject.getString(NEWS_ID), newsObject.getLong(NEWS_TIME),
                    newsObject.getString(NEWS_DESCRIPTION), newsObject.getString(NEWS_IMAGE_ID),
                    newsObject.getString(NEWS_SUBJECT), newsObject.getInt(NEWS_HITS),
                    newsObject.getString(NEWS_THREAD), newsObject.getString(NEWS_USER_ID),
                    newsObject.getString(NEWS_USER_NAME), newsObject.getInt(NEWS_POSTS),
                    newsObject.getString(NEWS_CATEGORY_ID), newsObject.getString(NEWS_CATEGORY)));
        }

        return result;
    }

    /**
     * Parses a raw JSON, received by the server upon login.
     *
     * @param object The JSON object.
     * @return The {@link LoginData} object.
     * @throws JSONException If the JSON is not formatted as expected.
     */
    @NonNull
    public static LoginData parseLoginJSON(@NonNull JSONObject object) throws JSONException {
        return new LoginData(object.getString(LOGIN_ID), object.getString(LOGIN_IMAGE));
    }

    /**
     * Parses a raw JSON, returned by the server and returns a List of {@link Conference}s.
     *
     * @param object The JSON object.
     * @return The List of {@link Conference}s.
     * @throws JSONException If the JSON is not formatted as expected.
     */
    @NonNull
    public static List<Conference> parseConferencesJSON(@NonNull JSONObject object) throws JSONException {
        JSONArray conferencesArray = object.getJSONArray(CONFERENCES_ARRAY);
        List<Conference> result = new ArrayList<>(conferencesArray.length());

        for (int i = 0; i < conferencesArray.length(); i++) {
            JSONObject conferenceObject = conferencesArray.getJSONObject(i);

            result.add(new Conference(conferenceObject.getString(CONFERENCES_ID),
                    conferenceObject.getString(CONFERENCES_TOPIC),
                    conferenceObject.getInt(CONFERENCES_PARTICIPANT_AMOUNT),
                    conferenceObject.getInt(CONFERENCES_IS_CONFERENCE) == 1,
                    conferenceObject.getLong(CONFERENCES_TIME),
                    conferenceObject.getInt(CONFERENCES_IS_READ) == 1,
                    conferenceObject.getString(CONFERENCES_IMAGE)));
        }

        return result;
    }

    /**
     * Parses a raw JSON, returned by the server and returns a List of {@link Message}s.
     *
     * @param object The JSON object.
     * @return The List of {@link Message}s.
     * @throws JSONException If the JSON is not formatted as expected.
     */
    public static List<Message> parseMessagesJSON(@NonNull JSONObject object) throws JSONException {
        JSONArray messagesArray = object.getJSONArray(MESSAGES_ARRAY);
        List<Message> result = new ArrayList<>(messagesArray.length());

        for (int i = 0; i < messagesArray.length(); i++) {
            JSONObject messageObject = messagesArray.getJSONObject(i);

            result.add(new Message(messageObject.getString(MESSAGES_ID),
                    messageObject.getString(MESSAGES_FROM_ID),
                    messageObject.getString(MESSAGES_MESSAGE),
                    messageObject.getString(MESSAGES_ACTION),
                    messageObject.getLong(MESSAGES_TIME),
                    messageObject.getString(MESSAGES_DEVICE),
                    messageObject.getString(MESSAGES_USERNAME),
                    messageObject.getString(MESSAGES_IMAGE)));
        }

        return result;
    }
}
