package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NotEnoughBalanceException;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by rokim on 2018. 5. 26..
 * Modified by manseongkim on 2018. 6. 8..
 */
//플레이어 구현
public class Player {
    @Getter
    private long balance; //기준치, 여기선 시드머니를 의미
    @Getter
    private long currentBet; //현재 배팅 금액
    @Getter
    private boolean isPlaying; //게임 진행 판별
    @Getter
    private Hand hand; //패
    //Constructor of Player
    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney; //balance 값을 시드머니로 정한다
        this.hand = hand; //패 설정
        isPlaying = false; //게임 시작 전
    }
    //플레이어의 패 초기화
    public void reset() {
        hand.reset();
        isPlaying = false;
    }
    //플레이어의 금액 배팅
    public void placeBet(long bet) {
        //배팅 금액보다 시드머니가 모자라면
        if(balance < bet) {
            //시드머니가 충분치 않으므로 예외처리
            throw new NotEnoughBalanceException();
        }
        //충분하면
        balance -= bet; //배팅 금액만큼 시드머니에서 빼주고
        currentBet = bet; //현재 배팅 금액으로 건다
        //게임 진행중
        isPlaying = true;
    }
    //턴이 시작하고 플레이어가 처음 패를 받을 때
    public void deal() {
        hand.drawCard(); //첫번째 카드 드로우
        hand.drawCard(); //두번째 카드 드로우
    }
    //승리하면 배팅한 금액의 두배만큼 얻는다
    public void win() {
        balance += currentBet * 2;
        currentBet = 0;
    }
    //비기면 아무 변화가 없다
    public void tie() {
        balance += currentBet;
        currentBet = 0;
    }
    //지면 배팅한 금액만큼 잃는다
    public void lost() {
        currentBet = 0;
    }
    //히트: 한 장의 카드를 더 뽑는 행위
    public Card hitCard() {
        return hand.drawCard();
    }
    //스탠드: 카드를 뽑지 않고 그대로 턴 종료
    public void stand() {
        this.isPlaying = false;
    }
    //플레이어의 패를 받아온다
    public Hand getHand() {
        return this.hand;
    }
}
