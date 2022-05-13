package com.syys.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.syys.dto.UploadResultDTO;

@RestController
@Log4j2
public class UploadController {
	// spring 어노테이션
	// application.properties 변수
	@Value("${com.syys.upload.path}")
	private String uploadPath;

	// 폴더 이름 만드는 함수
	private String makeFolder() {
		// 오늘 날자 생성패턴 대소문자 구분 주의
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// 날짜 구분
		String folderPath = str.replace("/", File.separator);
		// 폴더 생성
		File uploadPathFolder = new File(uploadPath, folderPath);
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		} // end if
		log.info(folderPath);
		return folderPath;
	}// end make..

	   @PostMapping("/uploadAjax")
	   public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles )  {


	       //JSON으로 보낸 객체 저장
	       List<UploadResultDTO> resultDTOList = new ArrayList<>();

	       //파일이 하나 이상일수 있으므로  for
	       for( MultipartFile  i : uploadFiles){
	           //이미지 파일 검사
	           if(  i.getContentType().startsWith("image") == false){
	               log.warn("this file is not image type");
	               //이미지 아닐수 403에러
	               return new ResponseEntity<>(HttpStatus.FORBIDDEN); //for문 나가기;
	           }//end if     
	          
	           // [IE] 실제 파일 이름 전체 경로가 들어오므로
	           // String fileName=originalName.substring(originalName.lastIndexOf("\\"+1));
	           String originalName = i.getOriginalFilename();
	           log.info("fileName :" + originalName);
	           //폴더 생성
	           String folderPath = makeFolder();
	           log.info(folderPath);
	           //uuid
	           String uuid = UUID.randomUUID().toString();
	           //파일 이름 완성
	           String saveName = uploadPath + File.separator + folderPath +
	                   File.separator+ uuid + "_" + originalName;
	           log.info(saveName);
	           //경로 저장
	           Path savePath = Paths.get(saveName);
	           try{ //실제 저장
	               // 원본 이미지 파일 저장
	               i.transferTo(savePath);
	               String thumnailSaveName = uploadPath + File.separator + folderPath +
	                       File.separator+ "s_"+uuid + "_" + originalName;
	               File thumbailFile = new File(thumnailSaveName);
	               //섬네일 파일 생성 100 X 100 생성  input,output, 가로, 세로
	               Thumbnailator.createThumbnail( savePath.toFile(), thumbailFile, 100,100);
	               //각 파일 정보 리스트에 클래스로 저장
	               resultDTOList.add(
	                       new UploadResultDTO(originalName, uuid, folderPath));
	           }catch (Exception e){
	               e.printStackTrace();
	           }//end try
	       }//end for      
	       //JSON 으로 반환 파일 정보 담은 리스트
	       log.info(resultDTOList); //JSON으로 보낼값
	       return  new ResponseEntity<>(resultDTOList, HttpStatus.OK);

	   }
	   
	   @GetMapping("/display")
	   public ResponseEntity<byte[]> getFile(String fileName){
	       ResponseEntity<byte[]> result = null;
	       try{
	           //브라우저에서 파일이름이 오기때문에 디코딩
	           String srcFileName = URLDecoder.decode(fileName,"UTF-8");
	           log.info(srcFileName);
	           File file = new File(uploadPath+ File.separator+ srcFileName);
	           log.info(file);
	           //헤더 생성 브라우져에 보내야 하므로
	           HttpHeaders headers = new HttpHeaders();
	           //헤더에 콘텐츠 타입 설정
	           headers.add("Content-Type", Files.probeContentType(file.toPath()));
	           //
	           result= new ResponseEntity<>(
	                   FileCopyUtils.copyToByteArray(file) , headers, HttpStatus.OK);
	       }
	       catch (Exception e){
	           e.printStackTrace();
	           //500번 에러 보냄
	           return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	       }//end try

	       return  result;
	   }//end getfile..

	   @PostMapping("/removeFile")
	   public ResponseEntity<Boolean> removeFile(String fileName){
	       String srcFileName= null;
	       log.info("removeFile-----");
	      
	       try{
	           srcFileName = URLDecoder.decode(fileName, "UTF-8");
	           log.info("srcFileName: "+srcFileName);
	           //원본 파일 삭제
	           File file = new File(uploadPath + File.separator+ srcFileName);
	           boolean result = file.delete();
	           //섬네일 파일 삭제  s_ +원본파일
	           File thumnailfile =
	                   new File(file.getParent(), "s_" + file.getName());
	           result = thumnailfile.delete();
	           //파일 삭제 결과와 200번 코드
	           return new ResponseEntity<>(result, HttpStatus.OK);
	       }catch (Exception e){
	           e.printStackTrace();

	           //500번 에러 반환
	           return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	       }//end try      
	      
	   }//end remove..


}// end class
