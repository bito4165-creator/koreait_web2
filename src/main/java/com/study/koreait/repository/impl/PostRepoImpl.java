package com.study.koreait.repository.impl;

import com.study.koreait.entity.Post;
import com.study.koreait.exception.PostException;
import com.study.koreait.exception.ProductException;
import com.study.koreait.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PostRepoImpl implements PostRepository {

    // DB대용
    private final List<Post> posts = new ArrayList<>(
            Arrays.asList(
                    Post.builder().postId(1).title("첫번째 게시물").content("1빠").build(),
                    Post.builder().postId(2).title("페이커 vs 손흥민").content("난 페이커").build(),
                    Post.builder().postId(3).title("스프링부트 공부").content("같이하실분").build()
            )
    );

    @Override
    public List<Post> findAllPosts() {
        return posts;
    }

    @Override
    public Post findPostById(int id) {
        Optional <Post> optPost = posts.stream()
                .filter(p -> p.getPostId() == id)
                .findFirst();

        Post post = optPost.orElseThrow(
                () -> new RuntimeException("해당 id의 게시글은 존재하지 않습니다.")
        );
//        if (optPost.isEmpty()) {
//            throw new ProductException("해당 id의 게시물은 존재하지 않습니다.", HttpStatus.NOT_FOUND);
//        }

        return post;
    }

    // 실습) Post도 controller까지 작성을 완료해 주세요
    // dto가 없다면, 생성을 하여 완성시켜 주세요.
    @Override
    public int insertPost(Post post) {
        int maxId = 0;
        for (Post p : posts) {
            int postId = p.getPostId();
            if(maxId < postId) {
                maxId = postId;
            }
        }
        // id만 없는게 확실하다
        post.setPostId(maxId + 1) ; // 기존 매개변수로 들어온 객체
        posts.add(post);

        return 1;
    }

    @Override
    public int deleteById(int id) {
        Optional<Post> optPost = posts.stream()
                .filter(p -> p.getPostId() == id)
                .findFirst();

        if (optPost.isEmpty()) {
            throw new PostException("해당 id의 작성글은 존재하지 않습니다.",HttpStatus.NOT_FOUND);
        }

        Post post = optPost.get();

        posts.remove(post);
        log.info("작성글 삭제 완료 : {}", post);

        return 1;
    }
}
