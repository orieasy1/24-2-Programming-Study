JPA에 대해
JPA라는 자바 표준 ORM(Object Relatioal Mapping) 기술

쿠팡, 우아한 형제들 등 자사 서비스를 개발하는 곳에서는 SpringBoot & JPA를 전사 표준으로 사용하고 있다

현대 웹 어플리케이션에서 관계형 데이터베이스는 빠질 수 없는 요소로 객체를 관계형 데이터베이스에서 관리하는 것이 무엇보다 중요하다. OR은 자바의 객체와 데이터베이스를 연결하는 프로그래밍 기법이다.



개발자가 아무리 자바클래스르 아름답게 설계해도 SQL을 통해야만 데이터베스에 저장하고 조회할 수 있다.

관계형 데이터베이스: 어떻게 데이터를 저장할지에 초점이 맞춰진 기술
객체지향 프로그래밍 언어: 메세지를 기반으로 기능과 속성을 한 곳에서 관리하는 기술
둘의 패러다임이 다른데 객체를 데이터베이스에 저장하려하니 패러다임 불일치 문제가 발생



서로 지향하는 바가 다른 2개의 영역을 중간에서 패러다임 일치를 시켜주기 위한 기술이 JPA이다.

개발자는 객체지향적으로 프로그래밍을 하고 JPA가 이를 관계형 데이터베이스에 맞게 SQL을 대신 생성해서 실행

SQL에 종속적인 개발을 하지 않아도 된다.



JPA는 인터페이스로 자바 표준명세서이다.

인터페이스인 JPA를 사용하기 위해서는 구현체가 필요한데 대표적으로는 Hibernate, Eclipse Link등이 있다.

관계: JPA <- Hibernate <- Spring Data JPA



구현체 교체의 용이성과 저장소 교체의 용이성을 위해 한단계 더 감싸놓은 Spring Data JPA가 등장

Mongo DB로 교체가 필요하다면 개발자는 Spring Data JPA에서 Spring Data MongoDB로 의존성만 교체하면 된다.





게시판 만들기
domain: 소프트웨어에 대한 요구사항 혹은 문제 영역



어노테이션 정리

@Entity: 테이블과 링크될 클래스임을 나타냄
@Id: 해당 테이블의 PK 필드를 나타냄
@GeneratedValue: PK의 생성규칙을 나타냄
@Column: 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 되지만 기본값 외에 추가로 변경이 필요한 옵션이 있을 시 사용
@NoArgsConstructor: 기본 생성자 자동 추가
@Getter: 클래스 내 모든 필드의 Gettter 메소드를 자동생성
@Builder: 해당 클래스의 빌더 패턴 클래스 생성


domain을 작성하고 돌리는데 SpringBootApplication도 안돌아가고 테스트도 에러뜨고 해서 멘탈이 나갔었는데, 나중에 다시 살펴보니 @Id를 임포트할 때 잘못 임포트했던 것이었다...

책에서는 JUnit4를 기준으로 테스트 코드를 작성해놓아서 나는 아래와 같이 Junit5로 테스트 코드를 수정하여 작성하였다.



package org.example.spring_study.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}




많은 사람들이 Service에서 비즈니스 로직을 처리해얗나다고 생각하지만 Service는 트랜잭션, 도메인 간 순서보장 역할만 한다.



PostsApiControllerTest도 다음과 같이 JUnit5를 바탕으로 수정하여 작성하였다.

package org.example.spring_study.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring_study.domain.posts.Posts;
import org.example.spring_study.domain.posts.PostsRepository;
import org.example.spring_study.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }
}


update 기능을 살펴보면 데이터 베이스에 쿼리를 날리븐느 부분이 없다. 이것이 가능한 이후는 JPA의 영속성 컨테스트 때문이다.

영속성 컨텍스트란 엔티티를 영구 저장하는 환경이다.

JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨택스트가 유지된 상태이다.

즉 Entity 객체 값만 트랙잭션이 끝난 시점에 변경하면 별도로 Update 쿠러리를 날리 필요가 없고 이 개념을 더티체킹이라고 한다.



