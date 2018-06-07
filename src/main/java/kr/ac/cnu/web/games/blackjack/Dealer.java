package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rokim on 2018. 5. 26..
 * Modified by manseongkim on 2018. 6. 8..
 */
//딜러구현
public class Dealer {
    @Getter
    private Hand hand; //패
    //딜러의 패
    public Dealer(Hand hand) {
        this.hand = hand;
    }
    //딜러의 패 초기화
    public void reset() {
        hand.reset();
    }
    //카드 한장 드로우
    public void deal() {
        hand.drawCard();
    }
    //딜러의 플레이
    public void play() {
        /* 딜러의 카드 총합이 17 보다 작으면 17 이상 일때까지 무조건 히트(카드한장씩 드로우)하고,
         * 17 이상이면 스테이한다.
         */
        while(hand.getCardSum() < 17) {
            hand.drawCard();
        }
    }
    //딜러의 패를 받아온다
    public Hand getHand() {
        return this.hand;
    }
}
