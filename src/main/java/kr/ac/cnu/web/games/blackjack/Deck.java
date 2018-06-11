package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NoMoreCardException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 * Modified by manseongkim on 2018. 6. 8..
 */
//덱 구현
public class Deck {
    @Getter
    private final int number; //덱의 수, 기본덱은 52장의 카드로 구성
    @Getter
    private final List<Card> cardList; //카드 리스트

    //덱의 수만큼 받아와 하나로 합쳐서 실제 게임에서 플레이할 하나의 덱을 완성
    public Deck(int number) {
        this.number = number;
        this.cardList = new ArrayList<Card>(); //카드 리스트 생성
        createCards(number);
        Collections.shuffle(cardList); //셔플(모든 카드를 섞는다)
    }

    //카드 생성 함수
    private void createCards(int number) {
        //덱의 수만큼 반복
        for (int j = 0; j < number; j++) {
            //문양 반복(하트,스페이드,클럽,다이아)
            for (Suit suit : Suit.values()) {
                //숫자 반복(1,2,...,10,J,Q,K)
                for (int i = 1; i < 14; i++) {
                    Card card = new Card(i, suit); //카드 생성
                    cardList.add(card); //카드 리스트에 추가
                }
            }
        }
    }

    //카드 한장을 드로우
    public Card drawCard() {
        //카드 리스트에 카드가 10장 이하인 경우
        if(cardList.size () <= 10){
            createCards ( number );
            //카드리스트를 새로 덱에 추가한다.
            Collections.shuffle(cardList); //다시 셔플(모든 카드를 섞는다)
        }
        //카드 리스트(덱의 맨 윗장)에서 한 장의 카드를 제거
        return cardList.remove(0);
    }
}
