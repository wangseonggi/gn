package com.fovsoft.gn.service.system;

import com.fovsoft.gn.entity.SystemRoleDO;
import com.fovsoft.gn.entity.holder.Menu;
import com.fovsoft.gn.entity.holder.MenuData;
import com.fovsoft.gn.mapper.system.SystemRoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class SystemRoleService {
    @Resource
    private SystemRoleMapper systemRoleMapper;

    public PageInfo list(Integer page, Integer limit, String mc, String sm) {
        PageHelper.startPage(page, limit);
        List<SystemRoleDO> list = systemRoleMapper.list(mc, sm);

        return new PageInfo(list);
    }

    /**
     * 新增角色
     *
     * @param systemRoleDO
     * @return
     */
    public int add(SystemRoleDO systemRoleDO) {
        int num = systemRoleMapper.add(systemRoleDO);
        return num;
    }

    /**
     * 删除角色，附带删除中间表
     * @param roleid 角色id
     * @return
     */
    @Transactional
    public int del(int roleid) {
        // 删除角色
        int delRoleNum = systemRoleMapper.del(roleid);
        // 删除中间表的关联
        systemRoleMapper.delFromMidTable(roleid);
        return delRoleNum;
    }

    public List getMenuTree(Integer roleId) {
        // 根据传入的角色id查询的已授权限
        List<Integer> permitIDList = systemRoleMapper.getPermitID(roleId);
        List<Menu> menuList = systemRoleMapper.getMenus();

        if(permitIDList.size() != 0) {
            // 逐条对比，存在则checked
            for(Menu menu : menuList) {
                for(Integer permitID : permitIDList) {
                    if(permitID == menu.getId()) {
                        // 如果存在可访问的URL，则选定
                        menu.setChecked(true);
                        menu.setOpen(true);
                        break;
                    }
                }
            }
        }

        List<Menu> rootMenu = this.getRootNode(menuList);
        List<Menu> result = this.buildTree(menuList, rootMenu);

//        List<Menu> result = this.setMenuTreeSpread(tree);
        return result;
    }


    /**
     * 获取全部可用的菜单树
     * 根据角色id获取当前角色所拥有的菜单权限
     *
     * @param roleId 角色id
     * @return
     */
    public Map getMenuData(Integer roleId) {
        Map result = new HashMap();

        // 获取菜单，构造成树
        List<Menu> menus = systemRoleMapper.getMenus();
        List<Menu> rootNodes = this.getRootNode(menus);
        List<Menu> menuTree = this.buildTree(menus, rootNodes);

        // 获取已授权的菜单id（列表）
        List<Integer> permitedIDList = systemRoleMapper.getPermitID(roleId);

        // 生成不在无授权的菜单id（列表）
        List<Integer> unpermitedIDList = new ArrayList<>();
        for(Menu item : menus) {
            boolean exit = false;
            for(Integer permitedID : permitedIDList) {
                if(item.getId() == permitedID) {
                    exit = true;
                    break;
                }
            }
            if(!exit)
                unpermitedIDList.add(item.getId());
        }

        result.put("menuList",menuTree);
        result.put("permitList", permitedIDList);
        result.put("unpermitList", unpermitedIDList);

        return result;
    }

    /**
     * 根据传入的菜单id，角色id，是否选中处理角色与权限的对应关系
     * @param menuData
     * @return
     */
    @Transactional
    public int grant(MenuData menuData) {
        int resultNum = 0;

        List<Integer> idsList = new ArrayList<>();
        Collections.addAll(idsList, menuData.getIds());

        systemRoleMapper.delG(menuData.getRoleId());
        resultNum = systemRoleMapper.addG(menuData.getRoleId(), idsList);


        return resultNum;
    }

    /************  处理菜单树的逻辑  **************/

    /**
     * 从未经处理过的菜单列表中获取根菜单（列表）
     * 未经处理指的是列表中未区分根及子菜单
     *
     * 当pid为0时则为根
     *
     * @param menuList 未经处理的菜单数组
     * @return
     */
    private List<Menu> getRootNode(List<Menu> menuList) {
        List<Menu> rootMenuList = new ArrayList<>();

        for(Menu menu : menuList) {
            if(menu.getPid() == 0) {
                rootMenuList.add(menu);
            }
        }
        return rootMenuList;
    }

    /**
     * 将某一个菜单视为父菜单，递归生成该菜单的子菜单树
     * 该方法将返回以该菜单为根节点的菜单树
     *
     * @param menuList 未经处理的菜单列表
     * @param pNode 父菜单
     * @return
     */
    private Menu buildChildTree(List<Menu> menuList, Menu pNode) {
        List<Menu> children = new ArrayList<>();

        for(Menu menuNode : menuList) {
            if(menuNode.getPid() == pNode.getId()) {
                children.add(buildChildTree(menuList, menuNode));
            }
        }
        pNode.setChildren(children);
        return pNode;
    }

    /**
     * 构造菜单树
     * 通过遍历根菜单列表，逐个生成每一个根菜单下的菜单树
     *
     * @param menuList 未经处理过的菜单列表
     * @param rootList 根菜单列表
     * @return
     */
    private List<Menu> buildTree(List<Menu> menuList, List<Menu> rootList) {
        List<Menu> tree = new ArrayList<>();
        for(Menu menuNode : rootList) {
            menuNode = buildChildTree(menuList, menuNode);
            tree.add(menuNode);
        }
        return tree;
    }

    /************  处理菜单树的逻辑  **************/

    /**
     * 递归设置菜单树的spread属性
     *
     * @param menuList
     * @return
     */
    private List<Menu> setMenuTreeSpread(List<Menu> menuList) {
        for(Menu menu : menuList) {
            if(menu.getChecked() != null && menu.getChecked()) {
                menu.setOpen(true);
            }
            if(menu.getChildren() != null) {
                setMenuTreeSpread(menu.getChildren());
            }
        }

        return menuList;
    }

    /**
     * 根据pid获取其下所有子菜单id（如果有）
     *
     * @param menuList
     * @return
     */
    private List<Integer> getSubMenuID(Menu pnode, List<Menu> menuList, List result) {
        for(Menu menuNode : menuList) {
            if(menuNode.getPid() == pnode.getId()) {
                result.add(menuNode.getId());
                if(menuNode.getChildren() != null) {
                    getSubMenuID(menuNode, menuNode.getChildren(), result);
                }
            }
        }
        return result;
    }
}


