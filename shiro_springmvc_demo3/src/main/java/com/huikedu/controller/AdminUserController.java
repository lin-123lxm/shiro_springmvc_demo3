package com.huikedu.controller;

import com.huikedu.domain.AdminUser;
import com.huikedu.domain.AdminUserRole;
import com.huikedu.domain.Role;
import com.huikedu.services.AdminUserService;
import com.huikedu.services.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("adminUser")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    @Autowired
    RoleService roleService;

    @RequiresPermissions("adminUser:list")
    @RequestMapping("list.do")
    public String dolist(HttpServletRequest request, @RequestParam(value = "currPage", required = false) Long curr, @RequestParam(value = "pageSize", required = false) Long pageSize) {
        Long recoredRows = adminUserService.selectCount();
        if (curr == null) {
            curr = 1L; //
        }
        if (pageSize == null) {
            pageSize = 10L; // 初始记录条数
        }
        Long totalPage = recoredRows % pageSize == 0 ? recoredRows / pageSize : recoredRows / pageSize + 1;
        Long startRecord = (curr - 1) * pageSize;
        //查询当前页的区间记录
        List<AdminUser> adminUserlist = adminUserService.selectByPage(startRecord, pageSize); // 10 条记录
        request.setAttribute("adminUserlist", adminUserlist);
        request.setAttribute("currPage", curr);// 当前面
        request.setAttribute("pageSize", pageSize);//每页显示的记录数
        request.setAttribute("count", recoredRows);//总记录条数
        request.setAttribute("totalPage", totalPage);//总页数
        //返回 视图资源名称
        return "adminUser/list";// /WEB-INF/jsp/adminUser/list.jsp
    }

    /*
    跳转至add添加页面
     */
    @RequiresPermissions("adminUser:create")
    @RequestMapping("toadd.do")
    public String goAdd(Model model) {
        //查询所有角色信息
        model.addAttribute("rolelist", roleService.queryAllroles());
        return "adminUser/add";

    }

    /*
    处理添加用户
     */
    @RequiresPermissions("adminUser:create")
    @RequestMapping(value = "adminUserAdd.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doAdd(HttpServletRequest request,
                                     @RequestParam(value = "account", required = true) String account,
                                     @RequestParam(value = "password", required = true) String password,
                                     @RequestParam(value = "checkPID", required = false) String checkPID) {

        //处理数据添加
        Map<String, Object> maps = new HashMap<>();
        boolean flag = adminUserService.addAdminUserRole(account, password, checkPID);
        if (flag == true) {
            maps.put("status", 200);
            maps.put("message", "添加成功！");
        } else {
            maps.put("status", 500);
            maps.put("message", "添加失败！");
        }
        return maps;
    }

    /**
     * 验证用户名是否重名
     */
    @RequiresPermissions("adminUser:create")
    @RequestMapping(value = "checkUserName.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkUsername(@RequestParam(value = "account", required = true) String account) {

        Map<String, Object> resultmap = new HashMap<>();
        boolean check = adminUserService.checkUsername(account);
        if (check == true) {
            //
            resultmap.put("status", "200");
            resultmap.put("message", true);

        } else {
            resultmap.put("status", "300");
            resultmap.put("message", false);
        }
        return resultmap;


    }

    /**
     * 转向到用户修改update.jsp页面，修改时显示角色信息，已有的角色选中。
     */
    @RequiresPermissions("adminUser:updateAdminUserRole")
    @RequestMapping(value = "updateAdminUserRole.do", method = RequestMethod.GET)
    public String updateAdminUserRole(Model model, @RequestParam(value = "adminUserId", required = true) String adminuserId) {
       /*
        1、查询当前用户的信息.???【待完成】
        2、查询所有的角色信息。
        3、查询当前用户的角色ID列表,查询用户_角色的中间表，根据用户ID查询AmdinUserRole
         */
        AdminUserRole adminUserRole = new AdminUserRole();
        adminUserRole.setAdminUserId(Long.parseLong(adminuserId));
        // 根据用户ID查询，该用户下的角色ID
        List<AdminUserRole> adminUserRoleList = adminUserService.selectAdminUserRole(adminUserRole);
        List<Role> roleList = roleService.queryAllroles();
        model.addAttribute("adminUserRoleList", adminUserRoleList);
        model.addAttribute("roleList", roleList);
        model.addAttribute("adminUserId", adminuserId);
        return "adminUserRole/update";


    }

    /**
     * 修改用户提交。修改用户信息，用户角色的中间表关联记录
     */
    @RequiresPermissions("adminUser:updateAdminUserRole")
    @RequestMapping(value = "dosubmitUpdateAdminUserRoles.do", method = RequestMethod.POST)
    public String updateAdminUserRolesSumbit(@RequestParam(value = "adminUserId", required = true) String adminUserId,
                                             @RequestParam(value = "roleId", required = false) String roleId[], RedirectAttributes attributes) throws Exception {
        /**
         *1、修改当前用户的信息。
         * 2、清空原来跟当前用户关联的中间表的角色记录。
         * 3、重新添加新的关联记录。
         */
        if (adminUserId == null || "".equals(adminUserId)) {
            throw new Exception("当前用户ID为空");
        } else {
            adminUserService.updateAdminUserRolesubmit(adminUserId, roleId);
        }
        return "redirect:list.do";
    }

    /**
     * 启用和禁用用户
     */
    @RequiresPermissions("adminUser:updateDisabled")
    @RequestMapping(value = "updateAdminUserDisable.do", method = RequestMethod.GET)
    public String updateAdminUserDisable(@RequestParam(value = "adminUserId", required = true) String adminUserId, RedirectAttributes redirectAttributes) {
        try{
            if (adminUserId!=null) {
                adminUserService.updateAdminUserDisable(adminUserId);
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","启用/禁用失败");
            return "redirect:list.do";
        }
        return "redirect:list.do";
    }
    /**
     *删除管理用户
     */
    @RequiresPermissions("adminUser:delete")
    @RequestMapping(value = "delAdminUser.do", method = RequestMethod.GET)
    public String delAdminUser(@RequestParam(name = "adminUserId" ,required = true) String adminUserId ){

        /**
         * 删除只能删除被禁用用户。
         *1、查询出删除的用户
         * 2、判断用户状态，决定能否删除。
         */
        return  "redirect:list.do";
    }



}
