package zhe.it_tech613.com.vaderiptv.models;

import android.util.Pair;

public class VadersApiUrl extends Pair<String, String> {
        /* renamed from: a */
        private String key;
        /* renamed from: b */
        private String value;

        public VadersApiUrl(String key, String value) {
            super(key, value);
            this.key = key;
            this.value = value;
        }

        /* renamed from: a */
        public String getKey() {
            return this.key;
        }

        /* renamed from: b */
        public String getUrlValue() {
            return this.value;
        }
    }