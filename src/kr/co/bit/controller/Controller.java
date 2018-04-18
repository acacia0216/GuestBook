package kr.co.bit.controller;

import kr.co.bit.dao.GuestBookDAO;
import kr.co.bit.vo.GuestBookVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("cmd");
        cmd = cmd == null ? "" : cmd;
        String url = "./list.jsp";

        if (cmd.equals("insert")) {
            GuestBookDAO dao = new GuestBookDAO();
            GuestBookVO vo = new GuestBookVO();

            vo.setName(request.getParameter("name"));
            vo.setPassword(request.getParameter("pass"));
            vo.setContent(request.getParameter("content"));

            dao.insert(vo);
        } else if (cmd.equals("delete")) {
            System.out.println("delete 들어옴");
            GuestBookDAO dao = new GuestBookDAO();
            System.out.println(request.getParameter("password"));
            String passwordConfirm = request.getParameter("password");
            int no = Integer.parseInt(request.getParameter("no"));
            System.out.println(no);

            GuestBookVO vo = dao.searchContent(no);

            System.out.println(vo.getPassword());

            if (passwordConfirm.equals(vo.getPassword())) {
                dao.delete(vo.getNo());
                System.out.println("데이터삭제 완료");
            }else{
                System.out.println("패스워드 불일치");
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
