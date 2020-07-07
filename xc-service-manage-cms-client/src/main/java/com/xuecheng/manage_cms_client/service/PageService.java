package com.xuecheng.manage_cms_client.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.dao.CmsSiteRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;

@Service
public class PageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageService.class);

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsSiteRepository cmsSiteRepository;

    //保存HTML页面到服务器物理路径
   public void savePageToServerPath(String pageId){
       //根据pageId查询cmsPage
       CmsPage cmsPage = this.findCmsPageById(pageId);
       //得到html文件id，从cmsPage中获得
       String htmlFileId = cmsPage.getHtmlFileId();

       //从gridFS中查询html文件
       InputStream inputStream = this.getFileById(htmlFileId);
       if (inputStream == null){
           LOGGER.error("getFileById InputStream is null ,htmlFileId:{}",htmlFileId);
           return;
       }
       //得到站点id
       String siteId = cmsPage.getSiteId();
       //得到站点信息
       CmsSite cmsSite = this.findCmsSiteById(siteId);
       //得到站点的物理路径
       String sitePhysicalPath = cmsSite.getSitePhysicalPath();
       //得到页面的物理路径
       String pagePath = sitePhysicalPath + cmsPage.getPagePhysicalPath() + cmsPage.getPageName();
       FileOutputStream fileOutputStream = null;
       //将HTML文件保存到物理路径
       try {
           fileOutputStream = new FileOutputStream(new File(pagePath));
           IOUtils.copy(inputStream,fileOutputStream);
       } catch (Exception e) {
           e.printStackTrace();
       }finally {
           try {
               fileOutputStream.close();
             } catch (IOException e) {
               e.printStackTrace();
           }
           try {
               inputStream.close();
           } catch (IOException e) {
               e.printStackTrace();
           }

       }
   }
   //根据文件id从gridFS中查询文件内容
    public InputStream getFileById(String fileId){
       //文件对象
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        //打开下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //定义
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        try {
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
   //根据页面id查询页面信息
    public CmsPage findCmsPageById(String pageId){
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //根据站点id查询页面信息
    public CmsSite findCmsSiteById(String siteId){
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
