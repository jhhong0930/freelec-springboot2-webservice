package com.jojoldu.book.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

   @Test
   public void 게시글저장_불러오기() {
       // given
       String title = "테스트 게시글";
       String content = "테스트 본문";

       postsRepository.save(Posts.builder() // save: id값이 있다면 update, 없다면 insert 쿼리 실행
               .title(title)
               .content(content)
               .author("jojoldu@gmail.com")
               .build());

       // when
       List<Posts> postsList = postsRepository.findAll(); // 모든 데이터 조회

       // then
       Posts posts = postsList.get(0);
       assertThat(posts.getTitle()).isEqualTo(title);
       assertThat(posts.getContent()).isEqualTo(content);
   }


}