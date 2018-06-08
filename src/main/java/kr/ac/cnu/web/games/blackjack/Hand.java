package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;

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
        int sum = 0; //총합 0 초기화
        //카드 리스트에 담겨있는 카드들을 스트림 객체배열로 재구성
        Card[] ArrayOfCardList = cardList.stream().toArray(Card[]::new);
        //카드 리스트 배열의 길이만큼 반복 수행
        for (int i = 0; i < ArrayOfCardList.length; i++) {
            //카드에 적힌 Rank 가 아닌, 실제 블랙잭 게임에서 매겨지는 점수(숫자)
            int realValue = ArrayOfCardList[i].getRank();
            //J,Q,K일 때, 점수 10으로 계산
            if (realValue == 11 || realValue == 12 || realValue == 13) {
                sum += 10;
            }
            //ACE 카드라면,
            else if (realValue == 1) {
                //총합이 21을 넘겨 파산할 경우 +1
                if ((sum + 11) > 21)
                    sum += 1;
                //그렇지 아니할 경우 +11
                else
                    sum += 11;
            }
            //그 외의 경우(2~10)
            else {
                //그대로 점수 계산
                sum += realValue;
            }
        }
        return sum; //총합 반환
    }

    //패 초기화
    public void reset() {
        cardList.clear(); //카드 리스트를 깨끗이 비운다
    }
}
