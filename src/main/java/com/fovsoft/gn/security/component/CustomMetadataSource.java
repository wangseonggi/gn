package com.fovsoft.gn.security.component;

import com.fovsoft.gn.security.bean.SysMenu;
import com.fovsoft.gn.security.bean.SysRole;
import com.fovsoft.gn.security.service.SysMenuService;
import com.fovsoft.gn.security.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        /**
         * 重点 ：
         * 这里的入参object是FilterInvocation
         */
        String url = ((FilterInvocation) object).getRequestUrl();

        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        List<ConfigAttribute> result = new ArrayList<>();

        try {
            List<SysMenu> menuList = sysMenuService.getMenus(url);

            if(menuList != null && menuList.size() > 0) {
                for (SysMenu menu : menuList) {
                    List<SysRole> roles = sysRoleService.selectRolesByMenuId(menu.getId());
                    if (roles != null && roles.size() > 0) {
                        for (SysRole role : roles) {
                            ConfigAttribute conf = new SecurityConfig(role.getMc());
                            result.add(conf);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    @Bean
//    private SysMenuService getSysMenuService() {
//        return new SysMenuService();
//    }
//
//    @Bean
//    private SysRoleService getSysRoleService() {
//        return new SysRoleService();
//    }

    /**
     * 获取所有权限
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
