package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 * Modified by manseongkim on 2018. 6. 8..
 */
//패 구현
public class Hand {
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>(); //카드 리스트 생성
    //Constructor of Hand
    public Hand(Deck deck) {
        this.deck = deck;
    }
    //카드 한장 드로우
    public Card drawCard() {
        //덱에서 드로우한 카드를 카드 리스트에 추가하고 반환
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }
    //카드 총합 계산
    public int getCardSum() {
        //카드 리스트에 담긴 카드의 숫자를 모두 더한다
        return cardList.stream().mapToInt(card -> card.getRank()).sum();
    }
    //패 초기화
    public void reset() {
        cardList.clear(); //카드 리스트를 깨끗이 비운다
    }
}
