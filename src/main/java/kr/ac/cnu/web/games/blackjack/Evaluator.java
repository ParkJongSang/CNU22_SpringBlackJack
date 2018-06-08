package kr.ac.cnu.web.games.blackjack;

import java.util.Map;

/**
 * Created by rokim on 2018. 5. 27..
 * Modified by manseongkim on 2018. 6. 8..
 */
//딜러와 각 플레이어의 점수 비교 구현
public class Evaluator {
    private Map<String, Player> playerMap; //게임에 참여한 플레이어들을 key-value 로 매핑하여 구분
    private Dealer dealer; //딜러
    //Constructor of Evaluator
    public Evaluator(Map<String, Player> playerMap, Dealer dealer) {
        this.playerMap = playerMap;
        this.dealer = dealer;
    }
    //점수 비교 함수 (boolean 값)
    public boolean evaluate() {
        //해당 플레이어가 게임 진행중이면 점수 비교 불가(false 반환)
        if (playerMap.values().stream().anyMatch(player -> player.isPlaying())) {
            return false;
        }
        //딜러의 점수결과는 딜러의 패를 모두 합한 값이다
        int dealerResult = dealer.getHand().getCardSum();
        //플레이어의 점수 비교(true 반환)
        playerMap.forEach((s, player) -> {
            //플레이어의 점수결과는 플레이어의 패를 모두 합한 값이다
            int playerResult = player.getHand().getCardSum();
            //플레이어의 점수가 21을 초과하면 해당 플레이어는 딜러의 점수와 관게없이 무조건 패한다
            if (playerResult > 21) {
                player.lost();
            }
            //플레이어어가 버스트되지 않은 상태에서 딜러가 버스트 될때 모든 플레이어는 승리한다
            else if (dealerResult > 21) {
                player.win();
            }
            //플레이어의 점수가 딜러의 점수보다 높으면 해당 플레이어는 승리한다
            else if (playerResult > dealerResult) {
                player.win();
            }
            //플레이어의 점수가 딜러의 점수와 같으면 무승부(타이)
            else if (playerResult == dealerResult) {
                player.tie();
            }
            //플레이어의 점수가 딜러의 점수보다 낮으면 해당 플레이어는 패한다
            else {
                player.lost();
            }
        });
        return true;
    }
}
