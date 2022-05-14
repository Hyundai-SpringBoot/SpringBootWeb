package com.syys.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
 
@Data
@Setter
@Getter
public class Pager {
    private int totalRows;      //전체 행수
    private int totalPageNo;    //전체 페이지 수
    private int totalGroupNo;   //전체 그룹 수
    private int startPageNo;    //그룹의 시작 페이지 번호
    private int endPageNo;      //그룹의 끝 페이지 번호
    private int pageNo;         //현재 페이지 번호
    private int pagesPerGroup;  //그룹당 페이지 수
    private int groupNo;        //현재 그룹 번호
    private int rowsPerPage;    //페이지당 행 수
    private int startRowNo;     //페이지의 시작 행 번호(1, ..., n)
    private int startRowIndex;  //페이지의 시작 행 인덱스(0, ..., n-1) for mysql
    private int endRowNo;       //페이지의 마지막 행 번호
    private int endRowIndex;    //페이지의 마지막 행 인덱스 for mysql
   
    // 페이지당 행 수 , 그룹당 페이지 수, 현재 페이지 번호
    public Pager(int rowsPerPage, int pagesPerGroup, int totalRows, int pageNo) {
        this.rowsPerPage = rowsPerPage;
        this.pagesPerGroup = pagesPerGroup;
        this.totalRows = totalRows;
        this.pageNo = pageNo;
 
        //전체페이지 번호
        totalPageNo = (totalRows / rowsPerPage);
        if(totalRows % rowsPerPage != 0) {
            totalPageNo = totalPageNo + 1;
        }//end if
       
        //전체 그룹 번호
        totalGroupNo = (totalPageNo / pagesPerGroup);
        if(totalPageNo % pagesPerGroup != 0) {
            totalGroupNo = totalGroupNo + 1 ;
        }//end if
       
        //현재 그룹 번호 세팅, 그룹의 시작 페이지 번호,그룹의 끝 페이지 번호
        groupNo = (pageNo - 1) / pagesPerGroup + 1;    
        startPageNo = (groupNo-1) * pagesPerGroup + 1;      
        endPageNo = startPageNo + pagesPerGroup - 1;
       
        //마지막 페이지 설정
        if(groupNo == totalGroupNo) {
            endPageNo = totalPageNo;
        }//end if
       
        //페이지의 시작 행 번호 세팅
        startRowNo = (pageNo - 1) * rowsPerPage + 1;
        startRowIndex = startRowNo - 1;
       
        //페이지의 마지막 행 번호 세팅
        endRowNo = pageNo * rowsPerPage;
        endRowIndex = endRowNo - 1;
    }//
 
}//

