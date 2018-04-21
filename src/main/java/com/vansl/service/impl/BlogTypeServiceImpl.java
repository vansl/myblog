package com.vansl.service.impl;

import com.vansl.dao.BlogTypeDao;
import com.vansl.dto.TypeTreeNode;
import com.vansl.entity.BlogType;
import com.vansl.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: vansl
 * @create: 18-4-15 下午4:19
 */

@Service
public class BlogTypeServiceImpl implements BlogTypeService{

    @Autowired
    private BlogTypeDao blogTypeDao;

      // 通过id查询博客分类
      @Override
      public TypeTreeNode selectById(Integer id) {
          return getAncestors(id);
      }

      // 递归建立继承树
      public TypeTreeNode getAncestors(Integer id) {
          //把BlogType转换为TypeTreeNode以创建当前节点
          TypeTreeNode son=new TypeTreeNode();
          BlogType type=blogTypeDao.selectById(id);
          son.setId(type.getId());
          son.setText(type.getTypeName());
          //如果是一级分类（id为0）则直接返回TypeTreeNode作为根节点
          if(type.getParentId()==0){
              return son;
          //否则递归得到父节点
          }else{
              TypeTreeNode ansetor=getAncestors(type.getParentId());
              //递归结束后把当前节点添加到父节点中作为子节点
              //循环遍历树得到当前节点的父节点
              TypeTreeNode father=ansetor;
              while (father.getChildren()!=null){
                  father=father.getChildren().get(0);
              }
              List<TypeTreeNode> child=new ArrayList<>();
              child.add(son);
              father.setChildren(child);
              //返回树
              return ansetor;
          }
      }

    // 返回某个用户的所有分类数据
    @Override
    public List<TypeTreeNode> selectAll(Integer userId) {
        //查询结果
        List<BlogType> typeList=blogTypeDao.selectAll(userId);
        //以parentId为索引建立map以查找子分类
        HashMap<Integer,List<TypeTreeNode>> map=new HashMap<Integer,List<TypeTreeNode>>();
        //把blogType对象转换成TypeTreeNode对象并放入map
        for (BlogType type:typeList) {
            if (map.get(type.getParentId())==null){
                map.put(type.getParentId(),new ArrayList<TypeTreeNode>());
            }
            TypeTreeNode node=new TypeTreeNode();
            node.setId(type.getId());
            node.setText(type.getTypeName());
            map.get(type.getParentId()).add(node);
        }
        //规定根结点id为0
        TypeTreeNode root=new TypeTreeNode();
        root.setId(0);
        //递归添加子节点建立树
        appendChildren(root,map);
        //返回根结点的子节点
        return root.getChildren();
    }

    // 递归建立树
    public void appendChildren(TypeTreeNode node,HashMap<Integer,List<TypeTreeNode>> map){
        //当子节点为空时放入一个空的List以保证JSON键的完整性，然后返回
        if (map.get(node.getId())==null){
            node.setChildren(new ArrayList<>());
            return;
        }
        //设置子节点
        node.setChildren(map.get(node.getId()));
        //递归处理子节点
        for (TypeTreeNode child:map.get(node.getId())) {
            appendChildren(child,map);
        }
    }

    // 通过博客id查询博客分类
    @Override
    public BlogType selectByBlogId(Integer blogId) {
        return blogTypeDao.selectByBlogId(blogId);
    }

    // 添加博客分类
    @Override
    public Integer insertBlogType(BlogType blogType) {
        if(blogType.getParentId()!=0){
            BlogType father=blogTypeDao.selectById(blogType.getParentId());
            if(father==null){
                return -1;
                // 限制最多只能有二级分类,以父分类id和parentId是否为0判断
            }else if(father.getId()!=0&&father.getParentId()!=0){
                return -1;
            }
        }
        try{
            blogTypeDao.insertBlogType(blogType);
        }catch (Exception e){
            //同个父分类下不允许有同名子分类
            return 0;
        }
        return 1;
    }

    // 更新博客分类
    @Override
    public Integer updateBlogType(BlogType blogType) {
          return blogTypeDao.updateBlogType(blogType);
    }

    // 删除博客分类
    @Override
    public Integer deleteBlogType(BlogType blogType) {
        Integer id=blogType.getId();
        Integer userId=blogType.getUserId();
        //所有分类数据
        List<BlogType> typeList=blogTypeDao.selectAll(userId);
        //以parentId为索引建立map以查找子分类
        HashMap<Integer,List<BlogType>> map=new HashMap<Integer,List<BlogType>>();
        for (BlogType type:typeList) {
            if (map.get(type.getParentId())==null){
                map.put(type.getParentId(),new ArrayList<BlogType>());
            }
            map.get(type.getParentId()).add(type);
        }
        //递归删除所有子类以及文章
        return deleteChildren(id,map);
    }
    public Integer deleteChildren(Integer id,HashMap<Integer,List<BlogType>> map){
        //递归处理子分类
        if (map.get(id)!=null){
            for (BlogType type:map.get(id)) {
                Integer result=deleteChildren(type.getId(),map);
                if(result==-1){
                    return -1;
                }
            }
        }
        //删除分类以及所属文章
        try{
            blogTypeDao.deleteBlogType(id);
            //blogDao.deleteBlogByTypeId(id);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}