// PostView
package org.sopt.post.view;

import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostCategory;

import java.util.Scanner;

public class PostView {
    private final Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("\n=== 게시판 ===");
        System.out.println("1. 게시글 작성");
        System.out.println("2. 게시글 목록 조회");
        System.out.println("3. 게시글 단건 조회");
        System.out.println("4. 게시글 수정");
        System.out.println("5. 게시글 삭제");
        System.out.println("6. 종료");
    }

    public int readCommand() {
        System.out.print("선택: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String readTitle() {
        System.out.print("제목: ");
        return scanner.nextLine();
    }

    public String readContent() {
        System.out.print("내용: ");
        return scanner.nextLine();
    }

    public int readPostNumber(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    public String readAuthor() {
        System.out.print("작성자: ");
        return scanner.nextLine();
    }

    public PostCategory readCategory() {
        System.out.print("카테고리 (1. 정보, 2. 공지, 3. 자유, 4. 질문): ");
        int categoryChoice = Integer.parseInt(scanner.nextLine());
        return switch (categoryChoice) {
            case 1 -> PostCategory.INFO;
            case 2 -> PostCategory.NOTICE;
            case 3 -> PostCategory.FREE;
            case 4 -> PostCategory.QUESTION;
            default -> throw new IllegalArgumentException("잘못된 카테고리 선택입니다.");
        };
    }

    public void printPost(Post post) {
        System.out.println("\n=== 게시글 ===");
        System.out.println("제목: " + post.getTitle());
        System.out.println("내용: " + post.getContent());
        System.out.println("카테고리: " + post.getCategory());
        System.out.println("작성자: " + post.getAuthor());
        System.out.println("작성일: " + post.getCreatedAt());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
