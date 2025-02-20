package hello.kimyounghanjdbc.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LogRepository logRepository;

    /**
     * MemberService @Transactional:OFF
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON
     */
    @Test
    void outerTxOff_success() {
        String username = "outerTxOff_success";

        memberService.joinV1(username);

        // 모든 데이터 정상 저장
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    @Test
    void outerTxOff_fail() {
        String username = "로그예외_outerTxOff_success";

        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        // 모든 데이터 정상 저장
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:OFF
     * LogRepository @Transactional:OFF
     */
    @Test
    void singleTx() {
        String username = "singleTx";

        memberService.joinV1(username);

        // 모든 데이터 정상 저장
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON
     */
    @Test
    void outerTxOn_success() {
        String username = "outerTxOn_success";

        memberService.joinV1(username);

        // 모든 데이터 정상 저장
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }
}