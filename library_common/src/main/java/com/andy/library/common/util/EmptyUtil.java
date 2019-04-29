package com.andy.library.common.util;

import java.util.Collection;

/**
 * @ClassName EmptyUtil
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/10$ 10:08 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/10$ 10:08 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class EmptyUtil {
    /**
     * 判断字符串是否为空，长度为0被认为是空字符串.
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null != str) {
            return str.trim().length() == 0;
        } else {
            return true;
        }
    }

    /**
     * 判断字符串是否为空，长度为0被认为是空字符串.
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空，字符串前后空白被截断，截断后长度为0被认为是空字符串
     *
     * @param str
     * @param isTimed 是否去掉前后空格
     * @return
     */
    public static boolean isEmpty(String str, boolean isTimed) {
        if (isTimed) {
            return null == str || str.trim().length() == 0;
        } else {
            return null == str || str.length() == 0;
        }
    }

    /**
     * 判断字符串是否为空，字符串前后空白被截断，截断后长度为0被认为是空字符串
     *
     * @param str
     * @param isTimed 是否去掉前后空格
     * @return
     */
    public static boolean isNotEmpty(String str, boolean isTimed) {
        return !isEmpty(str, isTimed);
    }

    /**
     * 判断列表是否为空，列表没有元素也被认为是空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.size() == 0;
    }

    /**
     * 判断列表是否为空，列表没有元素也被认为是空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
