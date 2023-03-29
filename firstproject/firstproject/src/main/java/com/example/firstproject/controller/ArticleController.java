package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j  //로깅을 위한 골뱅이(어노테이션)
public class ArticleController {

    @Autowired  //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결!
    private ArticleRepository articleRepository;    //데이터를 가져오는 주체

    @GetMapping("/articles/new")
    public String newArticleForm(){ return "articles/new"; }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        // System.out.println(form.toString()); -> 로깅 기능으로 대체
        log.info(form.toString());

        //1. Dto를 변환: Entity!
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}") //url입력 받아옴.
    public String show(@PathVariable Long id, Model model){  //위 path로 부터 입력이 된다 -> PathVariable
        log.info("id = " + id);

        // 1: id로 데이터를 가져옴!(노션: 조회 흐름 5번)
        Article articleEntity = articleRepository.findById(id).orElse(null);    //id값을 통해 찾고 값이 없으면 null 반환

        // 2: 가져온 데이터를 모델에 등록!(노션: 조회 흐름 6번)
        model.addAttribute("article", articleEntity);

        // 3: 보여줄 페이지를 설정(노션: 조회 흐름 7번)
        return "articles/show";
    }

    @GetMapping("/articels")
    public String index(){
        //1: 모든 Article을 가져온다!
        List<Article> articleEntityList = articleRepository.findAll();

        //2: 가져온 Article 묶음을 뷰로 전달!

        //3: 뷰 페이지를 설정!
        return "";
    }
}
