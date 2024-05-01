package dunn.dunnshop.controller;

import dunn.dunnshop.dto.verify.VerifyDto;
import dunn.dunnshop.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @GetMapping("/email-login")
    public ResponseEntity<?> sendEmail(@RequestBody String email) {
        String code = verificationService.generateVerificationCode();
        verificationService.saveVerificationCode(email, code);
        verificationService.sendVerificationEmail(email, code);
        return ResponseEntity.ok().body("인증번호 전송");
    }

    @GetMapping("/login/code")
    public String verifyCode(@RequestBody VerifyDto data) {
        if (verificationService.verifyCode(data.getEmail(), data.getCode())) {
            return "이메일 인증 성공!!";
        } else {
            return "인증번호가 다릅니다.";
        }
    }
}
