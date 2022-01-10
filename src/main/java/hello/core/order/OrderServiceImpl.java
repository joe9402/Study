package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    //@Autowired private MemberRepository memberRepository;// = new MemoryMemberRepository();
    //@Autowired private DiscountPolicy discountPolicy;// = new FixDiscountPolicy();
    private final MemberRepository memberRepository;// = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy;// = new FixDiscountPolicy();

    /*@Autowired(required = false)
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("2 discountPolicy : " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("2 memberRepository : " + memberRepository);
        this.memberRepository = memberRepository;
    }*/

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1 memberRepository : " + memberRepository);
        System.out.println("1 discountPolicy : " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
