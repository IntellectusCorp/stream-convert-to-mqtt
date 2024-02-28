package kr.intellectus.biz;

/**
 * KMS3Decoder 는 Buffer Fragmentation 문제를 다루기 위한 로직을 포함하고 있다.
 * 높은 빈도는 아닐지라도 여러가지 조건에 의해서 Stream-based 데이터 전송에서는 상대에서 전송한 메시지가 파편화 될 가능성이 있다. 
 * 이는 SOKCET 프로그래밍에서 접근할 수 있는 데이터의 형태가 Packets 이 아니라 Packets 에서 Load 된 바이트 단위이기 때문이다.
 * 
 * 참고: https://netty.io/wiki/user-guide-for-4.x.html#one-small-caveat-of-socket-buffer
 **/

 
public class KMA3MessageDecoder {
    
}
