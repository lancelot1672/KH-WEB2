package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dto.Member;
import member.service.MemberService;
import member.service.MemberServiceImpl;

@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private MemberService memberService = new MemberServiceImpl();

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. ���ڵ�ó��
        request.setCharacterEncoding("utf-8");
        
        // 2. ������Է°� ó��
        String memberId = request.getParameter("memberId");
        String password = request.getParameter("password");
        System.out.println("memberId@MemberLoginServlet = " + memberId);
        System.out.println("password@MemberLoginServlet = " + password);
        
        // 3. ��������
        Member member = memberService.findByMemberId(memberId);
        System.out.println("member@MemberLoginServlet = " + member);
        
        HttpSession session = request.getSession();
        
        if(member != null && password.equals(member.getPassword())) {
            // �α��� ����!
            
            session.setAttribute("loginMember", member);
        }
        else {
            // �α��� ����!
        	session.setAttribute("msg", "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
        }
        
        // 4. ����ó�� (jsp ����)
        RequestDispatcher reqDispatcher = request.getRequestDispatcher("/index.jsp");
        reqDispatcher.forward(request, response);
    }

}