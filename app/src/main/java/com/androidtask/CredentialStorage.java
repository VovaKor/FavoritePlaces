package com.androidtask;

import android.text.TextUtils;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vova on 16.05.17.
 */

public enum  CredentialStorage {

    INSTANCE {
        private Map<String,String> storage = new HashMap<>();

        public void addUser(String email, String password){
            storage.put(email,generateMD5(password));
        }

        public boolean isEmailValid(String key){
            return storage.containsKey(key);
        }

        public boolean isPasswordValid(String key, String value){
            String password = storage.get(key);
            return TextUtils.equals(password,generateMD5(value));
        }

        private String generateMD5(String password) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                Log.e(MessageDigest.class.getSimpleName(),"Exception creating MD5 algorithm");
            }
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
    };
    abstract void addUser(String key, String value);
    abstract boolean isEmailValid(String key);
    abstract boolean isPasswordValid(String key, String value);

}
