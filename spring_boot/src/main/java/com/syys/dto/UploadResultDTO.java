package com.syys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.net.URLEncoder;

@Data
@AllArgsConstructor     // 인터페이스 구현 JSON변환 때문에
public class UploadResultDTO implements Serializable {
   private String fileName;
   private String uuid;
   private String folderPath;
   public String getImageURL(){
       try{
           return URLEncoder
                   .encode(folderPath+
                           "/"+uuid+"_"+fileName,"UTF-8");
       }catch (Exception e){
           e.printStackTrace();
       }//end try
       return "fail";
   }//end get..
   
   public String getThumbnailURL(){
       try{
           return URLEncoder
                   .encode(folderPath+
                           "/"+"s_"+uuid+"_"+fileName,"UTF-8");
       }catch (Exception e){
           e.printStackTrace();
       }//end try
       return "Thumb fail";
   }//end getTh.

}//end class
