// demo.aidl
package com.aidl.demo;

import com.aidl.demo.UserBean;
// Declare any non-default types here with import statements

interface IUserAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    boolean login(String mobile, String password);

    /**
     * 获取token
     *
     * @param mobile
     * @param password
     * @return
     */
    String getToken();


    UserBean getUser();
}
