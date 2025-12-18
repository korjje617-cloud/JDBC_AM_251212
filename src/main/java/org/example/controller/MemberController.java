package org.example.controller;

import org.example.Member;
import org.example.service.MemberService;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;
import java.util.Scanner;

public class MemberController {
    private Connection conn;
    private Scanner sc;
    private MemberService memberService;

    public MemberController(Scanner sc, Connection conn) {
        this.sc = sc;
        this.conn = conn;
        this.memberService = new MemberService();
    }

    public void login() {
        String loginId = null;
        String loginPw = null;

        System.out.println("==로그인==");

        // 아이디 입력
        while (true) {
            System.out.print("아이디 : ");
            loginId = sc.nextLine().trim();

            // 아이디 입력값이 없음
            if (loginId.length() == 0 || loginId.contains(" ")) {
                System.out.println("아이디를 다시 입력하세요");
                continue;
            }

            // 아이디 조회
            SecSql sql = new SecSql();
            sql.append("SELECT COUNT(*) > 0 FROM `member` WHERE `loginId` = ?;", loginId);

            // 아이디 없음
            boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);
            if (isLoginIdDup == false) {
                System.out.println("아이디가 없습니다");
                continue;
            }
            break;
        }

        Member member = memberService.getMemberByLoginId(conn, loginId);

        int tryMaxCount = 3;
        int tryCount = 0;

        // 비밀번호 입력
        while (true) {
            if (tryCount >= tryMaxCount) {
                System.out.println("비밀번호를 확인하세요");
                break;
            }

            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine().trim();

            // 비밀번호 입력값이 없음
            if (loginPw.length() == 0 || loginPw.contains(" ")) {
                tryCount++;
                System.out.printf("비밀번호 확인 후 재시도 (%d/3)\n", tryCount);
                continue;
            }

            if (member.getLoginPw().equals(loginPw) == false) {
                tryCount++;
                System.out.printf("비밀번호 확인 후 재시도 (%d/3)\n", tryCount);
                continue;
            }
            System.out.println(member.getName() + "로그인 완료");
            break;
        }

    }




    public void doJoin() {
        String loginId = null;
        String loginPw = null;
        String loginPwConfirm = null;
        String name = null;

        System.out.println("==회원가입==");

        // 아이디 입력
        while (true) {
            System.out.print("아이디 : ");
            loginId = sc.nextLine().trim();

            // 아이디 입력값이 없음
            if (loginId.length() == 0 || loginId.contains(" ")) {
                System.out.println("아이디를 다시 입력하세요");
                continue;
            }

            // 중복 아이디 조회
            SecSql sql = new SecSql();
            sql.append("SELECT COUNT(*) > 0 FROM `member` WHERE `loginId` = ?;", loginId);

            // 중복 아이디임
            boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);
            if (isLoginIdDup) {
                System.out.println("사용중인 아이디");
                continue;
            }
            break;
        }
        // 중복 아이디가 없으면

        // 비밀번호 입력
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine();

            // 비밀번호 입력값이 없음
            if (loginPw.length() == 0 || loginPw.contains(" ")) {
                System.out.println("비밀번호를 다시 입력하세요");
                continue;
            }

            // 비밀번호 이중확인 일단 같다고 인정
            boolean loginCheckPw = true;

            // 비밀번호 재입력
            while (true) {
                System.out.print("비밀번호 확인 : ");
                loginPwConfirm = sc.nextLine().trim();

                // 비밀번호 재입력값이 없음
                if (loginPwConfirm.length() == 0 || loginPwConfirm.contains(" ")) {
                    System.out.println("비밀번호를 다시 입력하세요");
                    loginCheckPw = false;
                    continue;
                }

                // 원래 비밀번호랑 재입력 비밀번호랑 다름
                if (loginPw.equals(loginPwConfirm) == false) {
                    System.out.println("비밀번호가 일치하지 않습니다");
                    loginCheckPw = false;
                }
                break;
            }

            // 비밀번호 재입력까지 통과
            if (loginCheckPw) {
                break;
            }
        }

        // 이름 입력
        while (true) {
            System.out.print("이름 : ");
            name = sc.nextLine();

            // 이름 입력값이 없음
            if (name.length() == 0 || name.contains(" ")) {
                System.out.println("이름을 다시 입력하세요");
                continue;
            }
            break;
        }

        // 쿼리 실행하고 나온 정수값 = PK = 회원 번호 반환
        int id = memberService.doJoin(conn, loginId, loginPw, name);

        System.out.println(id + "번 회원 가입 완료");

    }
}

