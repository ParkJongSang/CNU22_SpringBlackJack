package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NoSuchRankException;
import lombok.Data;

/**
 * Created by rokim on 2018. 5. 26..
 * Modified by manseongkim on 2018. 6. 8..
 */
//카드구현
@Data
public class Card {
    private final int rank; //숫자
    private final Suit suit; //문양
    //Constructor of Card
    public Card(int rank, Suit suit) {
        //숫자가 13보다 크면 예외처리
        if (rank > 13) {
            throw new NoSuchRankException();
        }
        //카드의 숫자와 문양 설정
        this.rank = rank;
        this.suit = suit;
    }
    //카드의 숫자를 읽어온다
    public int getRank() {
        return this.rank;
    }
}
