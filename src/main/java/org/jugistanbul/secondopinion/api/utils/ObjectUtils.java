package org.jugistanbul.secondopinion.api.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ObjectUtils {

  // then use Spring BeanUtils to copy and ignore null
  public static void myCopyProperties(Object src, Object target) {
    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
  }

  public static String[] getNullPropertyNames (Object source) {
    // we should not change current id
    String[] notCopiedAttributes = {"id"};
    return getNullPropertyNames(source, new HashSet<String>(Arrays.asList(notCopiedAttributes)));
  }

  public static String[] getNullPropertyNames (Object source, Set<String> notCopiedAttributes) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet(notCopiedAttributes);
    for(java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) emptyNames.add(pd.getName());
    }

    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

}
