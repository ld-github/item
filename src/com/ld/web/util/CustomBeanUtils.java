package com.ld.web.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

/**
 * 
 *<p>Title: CustomBeanUtils</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-17
 */
public class CustomBeanUtils extends BeanUtils {

    public static void copyProperties(Object source, Object target, boolean copyNull) throws BeansException {

        copyProperties(source, target, null, copyNull, (String[]) null);
    }

    public static void copyProperties(Object source, Object target, Class<?> editable, boolean copyNull, String... ignoreProperties) throws BeansException {

        if (null == source || null == target) {
            throw new IllegalArgumentException("Source class and target class can not be null");
        }

        Class<?> actualEditable = target.getClass();

        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);

        List<String> ignoreList = (null != ignoreProperties) ? Arrays.asList(ignoreProperties) : null;

        for (PropertyDescriptor targetPd : targetPds) {

            Method writeMethod = targetPd.getWriteMethod();

            if (null != writeMethod && (null == ignoreProperties || (!ignoreList.contains(targetPd.getName())))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());

                if (null != sourcePd) {
                    Method readMethod = sourcePd.getReadMethod();

                    if (null != readMethod && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object value = readMethod.invoke(source);

                            if (!copyNull && null != value) {
                                continue;
                            }

                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            writeMethod.invoke(target, value);

                        } catch (Throwable ex) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }
}
