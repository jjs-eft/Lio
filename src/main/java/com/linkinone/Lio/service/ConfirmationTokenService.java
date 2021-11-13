package com.linkinone.Lio.service;

import com.linkinone.Lio.domain.entity.ConfirmationToken;
import com.linkinone.Lio.domain.repository.ConfirmationTokenRepository;
import com.linkinone.Lio.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;

    public String createEmailConfirmationToken(MemberDto memberDto){

        Assert.hasText(String.valueOf(memberDto.getMemid()),"memId는 필수 입니다.");
        Assert.hasText(String.valueOf(memberDto.getEmail()),"receiverEmail은 필수 입니다.");

        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(Long.valueOf(memberDto.getMemid()));
        confirmationTokenRepository.save(emailConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(memberDto.getEmail());
        mailMessage.setSubject("LINK IN ONE EMAIL SERVICE - 회원가입 확인");
        mailMessage.setText("회원가입 확인용 이메일 주소 : http://localhost:8080/confirm-email?token="+emailConfirmationToken.getId()+"\n환영합니다!");
        emailSenderService.sendEmail(mailMessage);

        return emailConfirmationToken.getId();
    }


    public ConfirmationToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId) throws Exception {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(),false);
        return confirmationToken.orElseThrow(()-> new Exception("oh"));
    };


}