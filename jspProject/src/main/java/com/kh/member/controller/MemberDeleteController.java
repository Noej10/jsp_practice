package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 요청 실행할 sql
		 * UPDATE MEMBER
		 * SET STATUS='N',
		 * 	   MODIFY_DATE=SYSDATE
		 * WHERE USER_ID = ? AND USER_PWD = ?
		 * 
		 * (정보변경, 비밀번호 변경처럼 갱신된 회원 다시 조회 X)
		 * (성공할 경우 => 메인페이지에서 alert 회원탈퇴 성공 메시지 출력 / 로그아웃되어야함(세션 loginUser만료)
		 * */
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		int result = new MemberService().deleteMember(m);
		
		HttpSession session = request.getSession();
		if(result>0) {
			session.removeAttribute("loginUser");
			session.setAttribute("alertMsg", "어 탈퇴 완료 잘가고");
			
			response.sendRedirect(request.getContextPath());
			
		}else {
			session.setAttribute("alertMsg", "회원 탈퇴에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
