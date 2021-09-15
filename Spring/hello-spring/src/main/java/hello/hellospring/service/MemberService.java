package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository inmemberRepository) {
        memberRepository = inmemberRepository;
    }

    /*
    * 회원가입
    */
    public Long join(Member member){
        //같은 이름이 있는 중복 확인
        validateDuplicateMamber(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMamber(Member member) {
        //아래 주석이 바로 아래 코드와 동일함
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(n -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        memberRepository.findByName(member.getName())
                .ifPresent(n -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);

    }
}
