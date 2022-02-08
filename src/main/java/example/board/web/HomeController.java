package example.board.web;

import example.board.SessionConst;
import example.board.domain.member.Member;
import example.board.domain.member.MemberRepository;
import example.board.domain.member.dto.MemberDto;
import example.board.web.argumentREsolver.Login;
import example.board.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController{

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) {
            return "home";
        }

        //login
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    //    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {
        //세션 관리자에 저장된 데이터 조회
        Member member = (Member) sessionManager.getSession(request);

        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        log.info("session member = {}", member);
        return "loginHome";
    }

    //    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        //세션에 회원 데이터가 없으면 home
        if (member == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", member);
        log.info("session member = {}", member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name =SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        //세션에 회원 데이터가 없으면 home
        if (member == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", member);
        log.info("session member = {}", member);
        return "loginHome";
    }

//    @GetMapping("/")
//    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model) {
//        //세션에 회원 데이터가 없으면 home
//        if (loginMember == null) {
//            return "home";
//        }
//        //세션이 유지되면 로그인으로 이동
//        model.addAttribute("member", loginMember);
//        log.info("session loginMember = {}", loginMember);
//        return "loginHome";
//    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login MemberDto loginMember,
                                              Model model,
                                              HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpMethod httpMethod,    //Http메서드를 조회한다
                                              Locale locale,    //Locale 정보 조회
                                              @RequestHeader MultiValueMap<String, String> headerMap,   //모든 Httpㅐ더를 MultiValueMap형식으로 조회
                                              @RequestHeader("host") String host,   //특정 HTTP헤더를 조회, 속성(필수값 여부 : required, 기본값 속성 : defaultValue)
                                              @CookieValue(value = "myCooke", required = false) String cookie) {
        //trace > debug > info > warn > error//
        log.trace("trace = {}", loginMember);
        log.debug("debug = {}", loginMember);
        log.info("info = {}", loginMember);
        log.warn("warn = {}", loginMember);
        log.error("error = {}", loginMember);

        log.info("request = {}", request);
        log.info("response = {}", response);
        log.info("httpMethod = {}", httpMethod);
        log.info("locale = {}", locale);
        log.info("headerMap = {}", headerMap); //Map과 유사한데, 하나의 키에 여러 값을 받을 수 있다.
        log.info("host = {}", host);
        log.info("cookie = {}", cookie);



        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        log.info("session loginMember = {}", loginMember);
        return "loginHome";
    }
}
