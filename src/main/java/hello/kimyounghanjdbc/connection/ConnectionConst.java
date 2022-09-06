package hello.kimyounghanjdbc.connection;

// 상수로만 쓰고 객체 생성을 막기 위해 abstract를 사용한다.
public abstract class ConnectionConst {

    // 외부에서 가져다 써야 하므로 public이 된다.
    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

}
