/*
 * Copyright 2016 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.contrib.scms.bitkeeper.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by bhupendrakumar on 9/20/16.
 */
public class BitKeeperConfig {

    private String url;
    private String username;
    private String password;

    public BitKeeperConfig(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String getUrlWithCredentials() {
        String[] parts = url.split("://");
        return String.format("%s://%s:%s@%s", parts[0], username, password, parts[1]);
    }

    public String getEffectiveUrl() {
        if (isRemoteUrl() && hasCredentials()) {
            return getUrlWithCredentials();
        }
        return getUrl();
    }

    public boolean isRemoteUrl() {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    public boolean hasCredentials() {
        return !StringUtils.isEmpty(url) && !StringUtils.isEmpty(password);
    }
}
