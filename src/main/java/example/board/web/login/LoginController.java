package example.board.web.login;

import example.board.SessionConst;
import example.board.domain.login.LoginService;
import example.board.domain.member.Member;
import example.board.domain.member.dto.MemberDto;
import example.board.domain.member.service.MemberService;
import example.board.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController implements SessionConst {
    private final LoginService loginService;
    private final SessionManager sessionManager;
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    //    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("LoginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성고 처리(cookie)
        //쿠키에 시간정보를 주지 않으면 새션 쿠키(브라우저 종료시 모두 종료) =>(참고 - 쿠키 말료일을 지정한것은 영속쿠키라한다)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);


        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("LoginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성고 처리(cookie)

        //세션관리자를 통해 세션을 생성하고, 회원데이터 보간
        //쿠키에 시간정보를 주지 않으면 새션 쿠키(브라우저 종료시 모두 종료) =>(참고 - 쿠키 말료일을 지정한것은 영속쿠키라한다)
        sessionManager.createSession(loginMember, response);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("LoginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성고 처리(cookie)
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true); // 세션의 `create`옵션 -  default가 true이며  request.getSession(true) 일경우 세션이 있으면 기존 세션 반환
        //세션에 로그인 회원 정보 보관
        session.setMaxInactiveInterval(60); //60초 - application.properties에 server.servlet.session.timeout=60 로 글로벌 설정하여 주고 session.setMaxInactiveInterval(60)를 쓰면 특정 세션에만 유효시간 적용
        session.setAttribute(LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("LoginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성고 처리(cookie)
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true); // 세션의 `create`옵션 -  default가 true이며  request.getSession(true) 일경우 세션이 있으면 기존 세션 반환
        //세션에 로그인 회원 정보 보관
        session.setMaxInactiveInterval(60); //60초 - application.properties에 server.servlet.session.timeout=60 로 글로벌 설정하여 주고 session.setMaxInactiveInterval(60)를 쓰면 특정 세션에만 유효시간 적용
        session.setAttribute(LOGIN_MEMBER, loginMember);

        return "redirect:"+redirectURL;
    }

    @PostMapping("/login")
    public String loginMyBatis(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        MemberDto loginMember = memberService.loginCheck(loginForm);

        if (loginMember == null) {
            bindingResult.reject("LoginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성고 처리(cookie)
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true); // 세션의 `create`옵션 -  default가 true이며  request.getSession(true) 일경우 세션이 있으면 기존 세션 반환
        //세션에 로그인 회원 정보 보관
        session.setMaxInactiveInterval(60); //60초 - application.properties에 server.servlet.session.timeout=60 로 글로벌 설정하여 주고 session.setMaxInactiveInterval(60)를 쓰면 특정 세션에만 유효시간 적용
        session.setAttribute(LOGIN_MEMBER, loginMember);

        return "redirect:"+redirectURL;
    }

    //    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, "memberId");
        return "redirect:/";
    }

//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate(); //세션과 데이터가 삭제
        }
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
