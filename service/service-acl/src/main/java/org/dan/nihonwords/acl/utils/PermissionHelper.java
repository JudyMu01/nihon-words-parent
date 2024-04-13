package org.dan.nihonwords.acl.utils;

import org.dan.nihonwords.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mcd
 * @create 2023-07-25 10:41
 */
public class PermissionHelper {

    /**
     * 将权限列表封装成需要的树结构
     * permission类中有一个children属性，存的是一个permission的list
     * @param allList
     * @return
     */
    public static List<Permission> buildPermission(List<Permission> allList){
        List<Permission> trees = new ArrayList<>();
        //遍历所有权限，取得第一层的权限
        for(Permission permission:allList){
            if(permission.getPid()==0){
                permission.setLevel(1);
                trees.add(findChildren(permission,allList));
            }
        }
        return trees;
    }

    private static Permission findChildren(Permission permission, List<Permission> allList) {
        permission.setChildren(new ArrayList<>());

        for(Permission child:allList){
            if(child.getPid().longValue()==permission.getId().longValue()){
                child.setLevel(permission.getLevel()+1);
                permission.getChildren().add(findChildren(child, allList));
            }
        }

        return permission;

    }
}
