package com.linkinone.Lio.service;


import com.linkinone.Lio.domain.Role;
import com.linkinone.Lio.domain.entity.BoardEntity;
import com.linkinone.Lio.domain.entity.ConfirmationToken;
import com.linkinone.Lio.domain.entity.MemberEntity;
import com.linkinone.Lio.domain.repository.ConfirmationTokenRepository;
import com.linkinone.Lio.domain.repository.MemberRepository;
import com.linkinone.Lio.dto.BoardDto;
import com.linkinone.Lio.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;
    private final EmailSenderService emailSenderService;
    private static ConfirmationTokenService confirmationTokenService;

    public static void confirmEmail(String token) throws Exception {
        ConfirmationToken findConfirmationToken = confirmationTokenService.findByIdAndExpirationDateAfterAndExpired(token);
        findConfirmationToken.useToken();
    }

    @Transactional
    public long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberDto.setUserrole(Role.MEMBER);

        return memberRepository.save(memberDto.toEntity()).getMemid();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
        MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@lio.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    @Transactional
    public MemberDto getInfo(String email) {
        Optional<MemberEntity> memberEntityWrapper = memberRepository.findByEmail(email);
        MemberEntity memberEntity = memberEntityWrapper.get();

        return this.convertEntityToDto(memberEntity);
    }

    @Transactional
    public Long updateInfo(MemberDto memberDto) {
        return memberRepository.save(memberDto.toEntity()).getMemid();
    }

    @Transactional
    public void deleteUser(String email) {
        memberRepository.deleteByEmail(email);

    }


    @Transactional
    public Long changePassword(MemberDto memberDto) {

        return memberRepository.save(memberDto.toEntity()).getMemid();
    }

    @Transactional
    public void find_password(String email) {

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }

        Long id = memberRepository.findMemidByEmail(email);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pw = passwordEncoder.encode(str);

        memberRepository.updatepassword(id ,pw);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("LINK IN ONE EMAIL SERVICE - 비밀번호 찾기");
        mailMessage.setText("회원님의 임시비밀번호는 "+str+"입니다!");
        emailSenderService.sendEmail(mailMessage);
    }


    private MemberDto convertEntityToDto(MemberEntity memberEntity) {
        return MemberDto.builder()
                .memid(memberEntity.getMemid())
                .name(memberEntity.getName())
                .email(memberEntity.getEmail())
                .nickname(memberEntity.getNickname())
                .password(memberEntity.getPassword())
                .userrole(memberEntity.getUserrole())
                .build();
    }
}
