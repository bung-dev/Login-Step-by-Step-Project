package project.member.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class OAuthController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Authentication authentication) {
        String sid = (request.getSession(false) != null) ? request.getSession(false).getId() : "null";
        log.info("INDEX auth={}, sessionId={}", authentication, sid);
        return "index";
    }

    @GetMapping("/oauth2/success")
    public String oauth2Success(@RequestParam(required = false) String accessToken, Model model) {
        model.addAttribute("accessToken", accessToken);
        return "oauth2-success";
    }
}
