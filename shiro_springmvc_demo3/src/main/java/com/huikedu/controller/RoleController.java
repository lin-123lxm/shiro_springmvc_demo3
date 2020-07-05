package com.huikedu.controller;

import com.huikedu.domain.Role;
import com.huikedu.services.PermissionService;
import com.huikedu.services.RoleService;
import com.huikedu.vo.RoleVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController  {

    @Autowired
    RoleService roleService ;

    @Autowired
    PermissionService permissionService;

    /*
    角色列表
     */
    @RequiresPermissions("role:list")
    @RequestMapping("/list.do")
    public String toList(HttpServletRequest request,
                         @RequestParam(value="currPage",required=false) Long curr,
                         @RequestParam(value="pageSize",required=false) Long pageSize){
        if(curr==null){
            curr=1L;
        }
        if(pageSize==null){
            pageSize=10L;
        }
        Long startRecord = (curr-1)*pageSize;
        Long rowCounts = roleService.selectCount();
        Long totalPage = 0L;
        if(rowCounts>0){
            totalPage = rowCounts%pageSize==0?rowCounts/pageSize:(rowCounts/pageSize)+1;
        }
        List<Role> roleList = roleService.selectBypage(startRecord, pageSize);
        request.setAttribute("rolelist", roleList);
        request.setAttribute("count", rowCounts);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currPage", curr);
        request.setAttribute("pageSize", pageSize);
        return "role/list";
    }

    /*
    to 添加
     */
    @RequestMapping(value = "/toadd.do", method =  RequestMethod.GET)
    public  String toAdd(Long roleId, Model model){
        //查询系统所有的权限信息
        model.addAttribute("permissionList", permissionService.listAllPermissions(null));
        return  "role/add";
    }
    /*
      处理添加提交
     */

    @RequestMapping(value = "/dosubmitAdd.do", method =  RequestMethod.POST)
    public  String doaddSubmitRole(RoleVO roleVO){
        /*
        1、保存角色信息到角色表

        2、保存角色ID和权限ID  到角色权限表
         */
        System.out.println(roleVO.getRoleName()+" , "+roleVO.getRoleDesc());

        if (roleVO.getRolePerssionId()!=null&&roleVO.getRolePerssionId().length>0){
            for (String permissionId: roleVO.getRolePerssionId()){
                System.out.println(" permissionId="+permissionId);
            }
        }
        return  "redirect:list.do";
    }

    @RequestMapping(value = "toeditRolePermission.do" , method =  RequestMethod.GET)
    public String toEditRolePermission(Long roleId,Model model ) throws  Exception{
        if (roleId == null) {
            throw  new Exception("当前角色不存在");
        }
        //根据角色ID查询当前角色持有的权限。
        model.addAttribute("rolePermissions", roleService.listRolePermissions(roleId));
        //查询所有的权限
        model.addAttribute("permissionList", permissionService.listAllPermissions(null));
        return  "rolePermission/update";
    }

    // 处理分配权限的提交方法
    @RequestMapping(value = "doeditSubmitRolePermission.do" , method =  RequestMethod.POST)
    public  String doEditSubmitRolepermissions(Long permissionId[], RedirectAttributes redirectAttributes){

        /*
        1、先清空当前角色原有关联

        2、重新添加关联
         */

        return  "redirect:role/list.do";


    }



}
