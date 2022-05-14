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
    private BoardService boardService;
 
    // HTTP 상태 코드 전송은 @ExceptionHandler에서 처리
    //ResponseEntity<> 대신 Map<> 스타일 차이
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
 
    @GetMapping("/{bno}")
    public BoardVO read(@PathVariable int bno
            , @RequestParam(defaultValue = "false") boolean hit) {
        log.info("read 실행");
        BoardVO board = boardService.get(bno);
        return board;
    }//end read
 
    @PostMapping("/create")
    public BoardVO create(BoardVO board) {
        log.info("create 실행");
        //파일 업로드시 동작
        if (board.getImg() != null && !board.getImg().isEmpty()) {
            MultipartFile mf = board.getImg();
            board.setImagefile(new Date().getTime() + "-" + mf.getOriginalFilename());
            board.setType(mf.getContentType());
            try {
                File file = new File("//Users//youngsuh//dev64//upload" + board.getImagefile());
                mf.transferTo(file);
            } catch (Exception e) {
                log.info("업로드 에러" + e);            
            }//end try
        }//end if      
        boardService.register(board);
        //생성후 조회
        board = boardService.get(board.getBno());
        return board;
    }
 
    // multipart/form-data로 데이터를 전송할 때에는 반드시 PUT, PATCH 사용할 수 없다.
    @PostMapping("/update")
    public BoardVO update(BoardVO board) {
        log.info("update 실행");
        //첨부파일 수정
        if (board.getImg() != null && !board.getImg().isEmpty()) {
            MultipartFile mf = board.getImg();
            board.setImagefile(new Date().getTime() + "-" + mf.getOriginalFilename());
            board.setType(mf.getContentType());
            try {
                File file = new File("//Users//youngsuh//dev64//upload" + board.getImagefile());
                mf.transferTo(file);
            } catch (Exception e) {
                log.info("업로드 에러" + e);    
            }//end ret
        }//end if      
        boardService.modify(board);
        //변경 후 조회
        board = boardService.get(board.getBno());
        return board;
    }//end update
 
    @DeleteMapping("/{bno}")
    public Map<String, String> delete(@PathVariable int bno) {
        log.info("delete 실행");
        boardService.remove(bno);
        //object를 Map 전송 --> jSON
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "success");
        return map;
    }//end delete
 
    @GetMapping("/img/{bno}")
    public void download(@PathVariable int bno, HttpServletResponse response) {
        log.info("download 실행");
        try {
            BoardVO board = boardService.get(bno);
            String imagefilename = board.getImagefile();
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
            InputStream is = new FileInputStream("//Users//youngsuh//dev64//upload" + imagefilename);
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
