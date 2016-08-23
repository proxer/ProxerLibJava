/*
 * Copyright (C) 2016 Francisco José Montiel Navarro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.proxerme.library.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;
import com.franmontiel.persistentcookiejar.persistence.SerializableCookie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;

/**
 * Hack for cookies which incorrectly do not have an "max-age" or "expires" property. This custom
 * implementation ignores these values and just saves every incoming cookie.
 *
 * @author Ruben Gees
 */
public class SaveAllSharedPrefCookiePersistor implements CookiePersistor {

    private final SharedPreferences sharedPreferences;

    public SaveAllSharedPrefCookiePersistor(Context context) {
        this(context.getSharedPreferences("CookiePersistence", Context.MODE_PRIVATE));
    }

    public SaveAllSharedPrefCookiePersistor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private static String createCookieKey(Cookie cookie) {
        return (cookie.secure() ? "https" : "http") + "://" + cookie.domain() + cookie.path()
                + "|" + cookie.name();
    }

    @Override
    public List<Cookie> loadAll() {
        List<Cookie> cookies = new ArrayList<>(sharedPreferences.getAll().size());

        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String serializedCookie = (String) entry.getValue();
            Cookie cookie = new SerializableCookie().decode(serializedCookie);

            cookies.add(cookie);
        }
        return cookies;
    }

    @Override
    public void saveAll(Collection<Cookie> cookies) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (Cookie cookie : cookies) {
            editor.putString(createCookieKey(cookie), new SerializableCookie().encode(cookie));
        }

        editor.apply();
    }

    @Override
    public void removeAll(Collection<Cookie> cookies) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (Cookie cookie : cookies) {
            editor.remove(createCookieKey(cookie));
        }

        editor.apply();
    }

    @Override
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
