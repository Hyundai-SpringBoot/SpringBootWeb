/*************************************************************
파일명: BoardController.java
기능: 게시판 글 조회/작성/수정/삭제
작성자: 진영서

[코멘트: RestController, 페이징 처리를 위한 Pager 클래스 활용]
*************************************************************/

package com.syys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.syys.domain.BoardVO;
import com.syys.domain.Pager;
import com.syys.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/board")
@Slf4j

public class BoardController {
	@Autowired
	// Service 객체 주입
    private BoardService boardService;
 
	//페이징 적용O
	//게시물 목록 불러오기
	//파라미터 : pageNo(페이지 번호)
    // HTTP 상태 코드 전송은 @ExceptionHandler에서 처리
    @GetMapping("/list")  // pageNo 기본값 1
    public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo) {
        log.info("list 실행");
        //게시판테이블 전체 행수 가져오기
        int totalRows = boardService.getTotal();
        //페이징 만들기, 5개
        Pager pager = new Pager(5, 5, totalRows, pageNo);
        //게시판 페이지가져오기
        List<BoardVO> list = boardService.getList(pager);
        //2개의 object를 Map 전송 --> jSON
        Map<String, Object> map = new HashMap<>();
        map.put("board", list);
        map.put("pager", pager);
        return map;
    }// end list
 
    //게시글 조회
    //파라미터 : 게시글 번호(bno), hit(조회수 업데이트)
    @GetMapping("/{bno}")
    public BoardVO read(@PathVariable int bno
            , @RequestParam(defaultValue = "false") boolean hit) {
    	//RequestParam hit을 통해 게시글 조회수 업데이트 여부 결정
    	//True->업데이트O / False->업데이트X
        log.info("read 실행");
        BoardVO board = boardService.get(bno, hit);
        return board;
    }//end read
 
    //게시글 작성
    //파라미터 : BoardVO 객체
    @PostMapping("/create")
    public BoardVO create(BoardVO board) {
        log.info("create 실행");
        //파일 업로드시 동작
        if (board.getImg() != null && !board.getImg().isEmpty()) {
            MultipartFile mf = board.getImg();
            board.setImagefile(new Date().getTime() + "-" + mf.getOriginalFilename());//파일 저장 이름 설정
            board.setType(mf.getContentType());//첨부 파일 타입
            try {
            	//경로에 이미지 업로드
                File file = new File("//Users//youngsuh//dev64//upload//" + board.getImagefile());
                mf.transferTo(file);
            } catch (Exception e) {
                log.info("업로드 에러" + e);            
            }//end try
        }//end if      
        //게시글 등록
        boardService.register(board);
        //생성후 조회, 조회수 업데이트 X 
        board = boardService.get(board.getBno(), false);
        return board;
    }
 
    //게시글 수정
    //파라미터 : BoardVO 객체
    // multipart/form-data로 데이터를 전송할 때에는 반드시 PUT, PATCH 사용할 수 없다.
    @PostMapping("/update")
    public BoardVO update(BoardVO board) {
        log.info("update 실행");
        //첨부파일 수정
        if (board.getImg() != null && !board.getImg().isEmpty()) {
            MultipartFile mf = board.getImg();
            board.setImagefile(new Date().getTime() + "-" + mf.getOriginalFilename());//파일 저장 이름 설정
            board.setType(mf.getContentType());//첨부 파일 타입
            try {
            	//경로에 이미지 업로드
                File file = new File("//Users//youngsuh//dev64//upload//" + board.getImagefile());
                mf.transferTo(file);
            } catch (Exception e) {
                log.info("업로드 에러" + e);    
            }//end ret
        }//end if     
        //게시글 수정
        boardService.modify(board);
        //변경 후 조회
        board = boardService.get(board.getBno(), false);
        return board;
    }//end update
 
    //게시글 삭제
    //파라미터 : 글번호(bno)
    @DeleteMapping("/{bno}")
    public Map<String, String> delete(@PathVariable int bno) {
        log.info("delete 실행");
        //게시글 삭제
        boardService.remove(bno);
        //object를 Map 전송 --> jSON
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "success");
        return map;
    }//end delete
 
    //게시글 이미지 다운로드
    @GetMapping("/img/{bno}")
    public void download(@PathVariable int bno, HttpServletResponse response) {
        log.info("download 실행");
        try {
        	//게시글 조회
            BoardVO board = boardService.get(bno, false);
            String imagefilename = board.getImagefile();
            //이미지가 없는 경우
            if (imagefilename == null) {
                return;
            }//end if
            // 파일 이름이 한글로 되어 있을 경우, 응답 헤더에 한글을 넣을 수 없기 때문에 변환해야 함
            imagefilename = new String(imagefilename.getBytes("UTF-8"), "ISO-8859-1");
            String type= board.getType();
            // 응답 생성
            // "Content-Disposition", "attachment; filename="a.jpg";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + imagefilename + "\";");
            response.setContentType(type);
            InputStream is = new FileInputStream("//Users//youngsuh//dev64//upload//" + imagefilename);
            OutputStream os = response.getOutputStream();
            FileCopyUtils.copy(is, os);
            is.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            log.info("다운로드 에러" + e);    
        }//end try
    }

}
