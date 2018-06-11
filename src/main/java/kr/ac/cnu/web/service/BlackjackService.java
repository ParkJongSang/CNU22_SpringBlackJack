package kr.ac.cnu.web.service;

import kr.ac.cnu.web.games.blackjack.Deck;
import kr.ac.cnu.web.games.blackjack.GameRoom;
import kr.ac.cnu.web.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rokim on 2018. 5. 26..
 */
//블랙잭서비스 구현
@Service
public class BlackjackService {
    private final int DECK_NUMBER = 1;
    private final Map<String, GameRoom> gameRoomMap = new HashMap<>();

    //게임 테이블 생성
    public GameRoom createGameRoom(User user) {
        Deck deck = new Deck(DECK_NUMBER); //덱 생성

        GameRoom gameRoom = new GameRoom(deck); //테이블 생성
        gameRoom.addPlayer(user.getName(), user.getAccount()); //플레이어 추가
        //테이블 맵에 룸아이디 지정하여 추가
        gameRoomMap.put(gameRoom.getRoomId(), gameRoom);

        return gameRoom; //테이블 반환
    }

    //여러 명의 플레이어가 참여하는 게임 테이블 생성
    public GameRoom joinGameRoom(String roomId, User user) {
        //보류 (미완성)
        return null;
    }

    public void leaveGameRoom(String roomId, User user) {
        gameRoomMap.get(roomId).removePlayer(user.getName());
    }

    //테이블 맵으로부터 룸아이디에 해당하는 테이블을 받아온다
    public GameRoom getGameRoom(String roomId) {
        return gameRoomMap.get(roomId);
    }

    //배팅
    public GameRoom bet(String roomId, User user, long bet) {
        GameRoom gameRoom = gameRoomMap.get(roomId);

        gameRoom.reset();
        gameRoom.bet(user.getName(), bet);
        gameRoom.deal();

        return gameRoom;
    }

    //히트
    public GameRoom hit(String roomId, User user) {
        GameRoom gameRoom = gameRoomMap.get(roomId);

        gameRoom.hit(user.getName());

        return gameRoom;
    }

    //스탠드
    public GameRoom stand(String roomId, User user) {
        GameRoom gameRoom = gameRoomMap.get(roomId);

        gameRoom.stand(user.getName());
        gameRoom.playDealer();

        return gameRoom;
    }

    //더블다운
    public GameRoom doubledown(String roomId, User user, long bet) {
        GameRoom gameRoom = gameRoomMap.get(roomId);

        gameRoom.bet(user.getName(), bet * 2);
        gameRoom.hit(user.getName());

        return gameRoom;
    }

}
