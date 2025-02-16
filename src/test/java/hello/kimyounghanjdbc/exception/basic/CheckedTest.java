package hello.kimyounghanjdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(service::callThrows)
                .isInstanceOf(MyCheckedException.class);
    }

    /*
     * Exception을 상속받으면 체크 예외
     *  */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    // 체크 예외는 잡아서 처리하거나 던지거나 둘 중 하나를 해야 한다.
    static class Service {
        Repository repository = new Repository();

        // 예외를 잡아서 처리
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                // 예외 처리 로직
                log.info("예외 처리, message = {}", e.getMessage(), e);
            }
        }

        // 체크 예외를 잡지 않고 밖으로 던지려면 throws를 메서드에 선언한다.
        public void callThrows() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
