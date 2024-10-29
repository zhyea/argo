package com.zhyea.argo.cms;

import com.zhyea.argo.cms.model.item.FcmItem;

public class MyApp {

    public static void main(String[] args) {
        FcmItem item = new FcmItem();
        item.setAppCode("111111");

        FcmItem item2 = new FcmItem();
        item2.setAppCode("111111");

        boolean r = item.equals(item2);

        System.out.println(item.hashCode());
        System.out.println(item2.hashCode());

    }


}
