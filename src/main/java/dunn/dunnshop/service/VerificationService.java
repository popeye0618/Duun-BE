package dunn.dunnshop.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final JavaMailSender mailSender;

    public String generateVerificationCode() {
        return RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }

    private final Map<String, String> verificationCodes = new HashMap<>();

    public void saveVerificationCode(String email, String code) {
        verificationCodes.put(email, code);
    }

    public String getVerificationCode(String email) {
        return verificationCodes.get(email);
    }

    public void sendVerificationEmail(String to, String code) {
        System.out.println("Sending to: " + to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("popeye0618@naver.com");
        message.setTo(to);
        message.setSubject("duunShop 인증번호");
        message.setText("인증번호는 " + code);
        mailSender.send(message);
    }

    public boolean verifyCode(String email, String inputCode) {
        String storedCode = getVerificationCode(email);
        return storedCode != null && storedCode.equalsIgnoreCase(inputCode);
    }
}
