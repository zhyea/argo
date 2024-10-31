package com.zhyea.argo.cms;

import com.zhyea.argo.cms.model.item.FcmItem;

public class MyApp {

    public static void main(String[] args) {
        FcmItem item = new FcmItem();

        FcmItem item2 = new FcmItem();

        boolean r = item.equals(item2);

        System.out.println(item.hashCode());
        System.out.println(item2.hashCode());

    }


}
