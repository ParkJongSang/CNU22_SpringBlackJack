package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by rokim on 2018. 5. 26..
 * Modified by manseongkim on 2018. 6. 8..
 */
//블랙잭 게임이 이루어지는 테이블 구현
public class GameRoom {
    @Getter
    private final String roomId; //테이블 아이디
    @Getter
    private final Dealer dealer; //딜러
    @Getter
    private final Map<String, Player> playerList; //각 플레이어들
    @Getter
    private final Deck deck; //덱
    @Getter
    private boolean isFinished; //턴 종료 판별
    private final Evaluator evaluator; //점수 비교기

    //Constructor of GameRoom
    public GameRoom(Deck deck) {
        this.roomId = UUID.randomUUID().toString();
        this.deck = deck;
        this.dealer = new Dealer(new Hand(deck));
        this.playerList = new HashMap<>();
        this.evaluator = new Evaluator(playerList, dealer);
        this.isFinished = true;
    }

    //플레이어 추가
    public void addPlayer(String playerName, long seedMoney) {
        //시드머니와 덱에서 받은 카드(패)로 플레이어 생성
        Player player = new Player(seedMoney, new Hand(deck));
        //플레이어 리스트에 추가
        playerList.put(playerName, player);
    }

    //플레이어 삭제
    public void removePlayer(String playerName) {
        //플레이어 리스트에서 제거
        playerList.remove(playerName);
    }

    //게임 초기화
    public void reset() {
        dealer.reset(); //딜러의 패 초기화
        playerList.forEach((s, player) -> player.reset()); //모든 플레이어의 패 초기화
    }

    //금액 배팅
    public void bet(String name, long bet) {
        Player player = playerList.get(name);
        //name 키 값을 가지는 플레이어는 bet 금액만큼 배팅한다
        player.placeBet(bet);
    }

    //패를 돌린다. 즉, 게임시작을 의미한다.
    public void deal() {
        //게임 진행중
        this.isFinished = false;
        dealer.deal(); //딜러는 한장의 카드를 드로우
        playerList.forEach((s, player) -> player.deal()); //플레이어는 2장의 카드를 드로우
    }

    //히트: 플레이어가 처음 2장의 카드 상태에서 한 장의 카드를 더 뽑는 것
    public Card hit(String name) {
        Player player = playerList.get(name);
        //해당 플레어어는 카드 한 장을 더 뽑는다
        Card hitCard = player.hitCard();
        int hitResult = player.getHand().getCardSum();
        //히트한 후, 총합이 21을 넘으면 바로 게임 종료
        if (hitResult > 21) {
            player.stand(); //먼저 스탠드한 후
            evaluator.evaluate(); //점수를 계산하고
            this.isFinished = true; //턴 종료
        }
        return hitCard; //히트카드 반환
    }

    //스탠드: 플레이어는 카드를 뽑지 않고 턴을 종료
    public void stand(String name) {
        Player player = playerList.get(name);
        //해당 플레이어 스탠드
        player.stand();
    }

    //딜러의 플레이
    public void playDealer() {
        dealer.play(); //딜러는 자신의 카드 총합이 17 이상 일때까지 히트한 후
        evaluator.evaluate(); //점수를 계산하고
        this.isFinished = true; //턴 종료
    }
    //테이블 아이디(룸아이디)를 받아온다
    /*public String getRoomId() {
        return this.roomId;
    }*/
}
