package org.example.container;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;
import org.example.dao.ArticleDao;
import org.example.dao.MemberDao;
import org.example.service.ArticleService;
import org.example.service.MemberService;
import org.example.session.Session;

import java.sql.Connection;
import java.util.Scanner;

public class Container {
    public static ArticleController articleController;
    public static MemberController memberController;

    public static ArticleService articleService;
    public static MemberService memberService;

    public static ArticleDao articleDao;
    public static MemberDao memberDao;

    public static Scanner sc;

    public static Connection conn;

    public static Session session;

    // 왜 반대 순서대로 만들었을까?
    public static void init(){
        sc = new Scanner(System.in);
        session = new Session();

        articleDao = new ArticleDao();
        memberDao = new MemberDao();

        articleService = new ArticleService();
        memberService = new MemberService();

        articleController = new ArticleController();
        memberController = new MemberController();
    }




}
