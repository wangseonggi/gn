package com.fovsoft.gn.security.component;

import com.fovsoft.gn.security.bean.SysRole;
import com.fovsoft.gn.security.bean.SysUser;
import com.fovsoft.gn.security.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据username查找用户，并检查有效性
        SysUser user = sysUserService.findUserByName(username);

        // 获取用户权限，并构造返回
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<SysRole> roles = sysUserService.getRoles(user.getId());
        if(roles != null && roles.size() > 0) {
            for (SysRole role : roles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getMc());
                authorities.add(authority);
            }
        }

        // todo 账号过期，密码过期判断
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLoscked = true;
        if(user.getZt() == 2) {
            accountNonLoscked = false;
        }
        Date now = new Date();
        if(user.getZhyxq() != null) {
            accountNonExpired = now.before(user.getZhyxq());
        }
        if(user.getMmyxq() != null) {
            credentialsNonExpired = now.before(user.getMmyxq());
        }

        return new User(user.getUsername(), user.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLoscked,
                authorities);
    }
}
