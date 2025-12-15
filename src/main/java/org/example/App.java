package org.example;

import java.sql.*;
import java.util.*;

public class App {
    public void run() {
        System.out.println("==프로그램 시작==");

        Scanner sc = new Scanner(System.in);
        int lastArticleId = 0;

        //------------------------------------------------------------------------
        while (true) {
            System.out.print("명령어 > ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("article write")) {
                System.out.println("==글쓰기==");
                int id = lastArticleId + 1;
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();

                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    String url = "jdbc:mariadb://127.0.0.1:3306/JDBC_AM_25_12?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");

                    String sql = "INSERT INTO article";
                    sql += " SET regDate = NOW(),";
                    sql += "updateDate = NOW(),";
                    sql += "title = '" + title + "',";
                    sql += "`body` = '" + body + "';";

                    System.out.println(sql);

                    pstmt = conn.prepareStatement(sql);

                    int affectedRow =  pstmt.executeUpdate();

                    System.out.println("affectedRow = " + affectedRow);

                } catch (ClassNotFoundException e) {
                    System.out.println("드라이버 로딩 실패" + e);
                } catch (SQLException e) {
                    System.out.println("에러 : " + e);
                } finally {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (pstmt != null && !pstmt.isClosed()) {
                            pstmt.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                lastArticleId++;

            }
            else if (cmd.equals("article list")) {
                List<Article> articles = new ArrayList<>();

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    String url = "jdbc:mariadb://127.0.0.1:3306/JDBC_AM_25_12?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");

                    String sql = "SELECT *";
                    sql += " FROM article";
                    sql += " ORDER BY id DESC";

                    System.out.println(sql);

                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery(sql);

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String regDate = rs.getString("regDate");
                        String updateDate = rs.getString("updateDate");
                        String title = rs.getString("title");
                        String body = rs.getString("body");
                        Article article = new Article(id, regDate, updateDate, title, body);
                        articles.add(article);
                    }

                    System.out.println("==목록==");
                    System.out.println("번호   /   작성   /   수정   /   제목   /   본문   ");
                    System.out.println("=".repeat(30));

                    for (Article article : articles) {
                        System.out.printf("%s   /   %s   /   %s   /   %s   /   %s   \n", article.getId(), article.getRegDate(), article.getUpdateDate(), article.getTitle(), article.getBody());
                    }


                } catch (ClassNotFoundException e) {
                    System.out.println("드라이버 로딩 실패" + e);
                } catch (SQLException e) {
                    System.out.println("에러 : " + e);
                } finally {
                    try {
                        if (rs != null && !rs.isClosed()) {
                            rs.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (pstmt != null && !pstmt.isClosed()) {
                            pstmt.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (articles.size() == 0) {
                    System.out.println("게시글 없음");
                    return;
                }

            }
            else if (cmd.startsWith("article modify")) {
                int id = 0;
                try {
                    id = Integer.parseInt(cmd.split(" ")[2]);
                }catch (Exception e){
                    System.out.println("수정할 글 번호를 입력하세요");
                    continue;
                }

                System.out.println("==수정하기==");
                System.out.print("새 제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String body = sc.nextLine().trim();

                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    String url = "jdbc:mariadb://127.0.0.1:3306/JDBC_AM_25_12?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");

                    String sql = "UPDATE article";
                    sql += " Set updateDate = NOW()"; // 컴마가 있다는 건 뒤에 이어지는 쿼리가 있어야 하는데 여기는 무조건 뒤에 쿼리가 나오는 상황이 아니다, 컴마를 빼고 조건에 따라 추가되는 if문 쪽에 붙여줘야 한다
                    if (title.length() > 0) {
                        sql += ", title = '" + title + "'";
                    }
                    if (body.length() > 0) {
                        sql += ", `body` = '" + body + "'";
                    }
                    sql += " WHERE id = " + id + ";";


                    System.out.println(sql);

                    pstmt = conn.prepareStatement(sql);

                    int affectedRow =  pstmt.executeUpdate();

                    System.out.println("affectedRow = " + affectedRow);

                    System.out.println(id + "번 글 수정 완료");

                } catch (ClassNotFoundException e) {
                    System.out.println("드라이버 로딩 실패" + e);
                } catch (SQLException e) {
                    System.out.println("에러 : " + e);
                } finally {
                    try {
                        if (pstmt != null && !pstmt.isClosed()) {
                            pstmt.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                lastArticleId++;

            }

        }
        //------------------------------------------------------------------------
        System.out.println("==프로그램 종료==");
        sc.close();
    }
}
