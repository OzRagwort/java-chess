# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 요구사항

* 체스판 초기화
    * 기본 위치에 맞게 기물을 생성한다.
    * row(Rank)는 1~8, column(File)은 a~h로 표현한다.
    * 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

* 기물의 종류
    * 폰(Pawn)
    * 비숍(Bishop)
    * 룩(Rook)
    * 퀸(Queen)
    * 나이트(Knight)
    * 킹(King)

* 기물이 가져야할 기능
    * 팀이 정해져야함
    * 이동 방법
        * 폰(Pawn)
            * 1칸 앞으로 이동(블랙 : 남쪽, 화이트 : 북쪽)
            * 상대 기물을 잡을 때는 대각선 앞으로만 이동 가능
            * 첫 시작은 2칸 앞으로 갈 수 있다.
        * 비숍(Bishop)
            * 대각선 방향으로 직선으로 이동할 수 있다.
            * 중간에 다른 기물이 있으면 이동할 수 없다.
        * 룩(Rook)
            * 상하좌우 방향으로 직선으로 이동할 수 있다.
            * 중간에 다른 기물이 있으면 이동할 수 없다.
        * 퀸(Queen)
            * 상하좌우, 대각선 방향으로 직선으로 이동할 수 있다.
            * 중간에 다른 기물이 있으면 이동할 수 없다.
        * 나이트(Knight)
            * 두칸 앞으로 간 뒤 좌우 방향으로 이동할 수 있다.
            * 중간에 다른 기물이 있어도 이동할 수 있다.
        * 킹(King)
            * 상하좌우, 대각선 1칸 이동할 수 있다.

* 현재 남아있는 말에 대한 점수를 계산한다.
    * queen은 9점
    * rook은 5점
    * bishop은 3점
    * knight는 2.5점
    * pawn 1점(같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.)
    * king은 잡히는 순간 게임이 끝나므로 0점으로 계산한다

* status 명령어를 입력하면 점수를 출력한다. 어느 진영이 이기고 있는지 출력한다.
    * 한 번에 한 쪽의 점수만을 계산해야 한다.

* King이 잡혔을 때 게임을 종료해야 한다.

## 명령 처리 기능 구현 목록(command)

- [x] 사용자로부터 명령을 입력 받는다.
    - [x] 잘못된 명령이 입력되면 에러 메시지를 출력하고 재입력 받는다.
    - [x] start
        - [x] 입력하면 게임이 시작된다.
        - [x] 게임 도중에 입력하면 재입력을 받는다.
    - [x] move a1 a2
        - [x] a1의 기물을 a2로 이동한다.
        - [x] start가 된 상태에서만 입력을 받을 수 있다.
    - [x] statue
        - [x] 각 진영의 점수를 출력하고 어느 팀이 이기고 있는지 출력한다.
        - [x] start가 된 상태에서만 입력을 받을 수 있다.
    - [x] end
        - [x] 어느 상태에서든 입력받으면 게임이 종료된다.

## 게임 진행 기능 구현 목록(state)

- [x] 각 턴마다 기물을 움직일 수 있다.
    - [x] 첫 수는 화이트 턴이 둔다.
    - [x] 블랙 턴에 기물을 움직이면 화이트 턴이 된다.
    - [x] 화이트 턴에 기물을 움직이면 블랙 턴이 된다.
    - [x] 자기 턴에 상대방의 기물을 이동할 수 없다.

- State
    - Ready
        - Running
            - WhiteTurn
            - BlackTurn
        - Finished
            - WhiteWin
            - BlackWin

## 기물 이동 기능 구현 목록

- [x] 이동할 위치로 갈 수 있는지를 확인한다.
    - [x] 기물의 이동 규칙을 지키는지 확인한다.
        - [x] PAWN
            - [x] 화이트는 rank가 높아지는 방향으로, 블랙은 rank가 낮아지는 방향으로 이동할 수 있다.
            - [x] 첫 움직임에 한칸 혹은 두칸 앞으로 갈 수 있다.
                - white -> 2 black -> 7 이면 처음이다.
            - [x] 첫 움직임이 아닌 경우 한 칸만 앞으로 갈 수 있다.
            - [x] 대각선 앞에 상대 기물이 있으면 이동하면서 잡을 수 있다.
            - [x] 뒤로 이동할 수 없다.
        - [x] KNIGHT
            - [x] 상하좌우 두칸 앞으로 이동 후 좌우의 위치로 이동한다.
        - [x] ROOK
            - [x] 상하좌우 직선으로 이동한다.
        - [x] BISHOP
            - [x] 대각선 방향으로 직선으로 이동한다.
        - [x] QUEEN
            - [x] 상하좌우 대각선 방향으로 직선으로 이동한다.
        - [x] KING
            - [x] 상하좌우 대각선으로 한 칸 이동한다.
    - [x] 이동할 위치에 아군 기물이 있으면 안된다.
        - [x] PAWN
        - [x] KNIGHT
        - [x] ROOK
        - [x] BISHOP
        - [x] QUEEN
        - [x] KING
- [x] 이동 경로에 다른 기물이 있는지 확인한다.
    - [x] PAWN
    - [x] ROOK
    - [x] BISHOP
    - [x] QUEEN

---

## 점수 계산 기능 및 승패 판단 기능 구현 목록

- [x] 흰 말의 점수를 계산한다.
- [x] 검은 말의 점수를 계산한다.
- [x] 현재 이기고 있는 진영을 판단한다.
- [x] King이 잡히면 게임을 종료한다.

---

## 리뷰 & 수정 사항 정리

- 객체의 역할에 포인트
- [x] (InputView) 같은 행위를 하는 메서드가 2개가 있어요. 어떻게 해야할까요?
    - 이름을 통해 구체적인 의도를 드러내는 것도 좋지만 이름은 결국 해당 메서드의 행위를 드러내야합니다.
    - `requestStartOrEnd()`와 `requestCommand()`는 이름은 다르지만 결국 명령어를 입력받아서 반환하는 메서드이다.
    - `requestStartOrEnd()` 메서드를 사용하는 곳에서 `requestCommand()`를 사용하도록 바꾸고 `requestStartOrEnd()`를 제거했다.
- [x] Ready와 Running의 상태가 크게 다르지 않은 것 같아요. 어떤 의도로 Ready를 만들었을까요?
    - 미션 구현을 하다보니 생각한대로 구현하지 못한것 같다.
    - 원래 의도는 이 모든 상태의 첫 시작점은 무조건 Ready의 `start()`를 통해서 시작하게 하기 위해서 Ready를 만들었다.
    - 그래서 그 외 모든 상태는 package-private 형식으로 생성자를 만들어 외부에서 직접 만들지 못하게 했다.
    - 그리고 Running에서 시작해도 되지만 Running과 Finished의 중복 코드가 있어서 Ready에서 Running과 Finished의 중복 코드도 가지고 있게 하려했다.
    - Running과 Finished를 만든 이유는 중복 제거의 의미도 있지만 추상화 레벨을 맞추기 위함도 있다. 상태 패턴 수업을들으면서 추상화 레벨을 맞춰주면 좋다고 이야기를 들었기 때문이다.
    - 코드를 다시 확인하면서 Finished 클래스가 Ready를 상속받는게 아니라 State를 구현하고 있는것을 알게되었다.
        - 원래 의도대로 Finished가 Ready를 상속받도록 수정했다.
        - BlackWin, WhiteWin의 생성자를 package-private로 수정했다.
        - 예외가 발생할 때 에러 메시지를 추가했다.
- [x] Ready에서 보드를 가져오는 `getBoard()`메서드는 매번 새로운 객체로 넘겨주는데 이유가 있나요?
    - Board가 `move()`메서드로 기물을 이동시킬 수 있기 때문에 그대로 board를 외부로 반환하면 외부에서 의도하지 않은 수정이 있을 수 있다고 생각했다.
    - 현재 기물 상태를 가지는 새로운 보드를 만들면 수정이 되더라도 실제 게임이 진행되는 board는 수정되지 않는다.
- [x] 축약해서 사용하는 변수명이 있어요!
- [ ] Piece에서 색상만 관리하는 것이 아니라 타입을 같이 관리하면 어떨까요?
    - 각 타입 별로 관리되어야하는 값을 응집도 있게 관리할 수 있을 것 같아요!
- [ ] 각 기물 클래스에서 MovePattern이라는 인터페이스를 사용하는데 왜 추상 클래스로 선언되었을까요?
- [ ] 인터페이스에 정의된 메서드는 곧 외부에 오픈할 메서드라고 볼 수 있어요. 외부에서 사용하지 않는 메서드는 어떻게 하는게 좋을까요?
- [ ] MovePattern에서 `findDirection()`은 null을 반환하고 있어요. NPE가 발생할 가능성이 있는 것 같은데 개선해볼까요?
- [ ] `StatusResult`와 `ScoreResult`는 같은 역할과 책임을 가지는 것 같아요. 해당 클래스를 둔 이유가 있을까요?
- [ ] (CommandState) -> 외부에서 Command를 찾고 그 값을 이용해서 CommandState를 찾아보면 어떨까요?
    - [ ] 메서드 이름도 보편적인 정적 팩토리 메서드로 보여서 특정 행위를 한다는걸 나타면 좋을 것 같아요.
- [ ] CommandState 하위 Start와 같은 클래스는 상태패턴을 사용하기 위해 만든 코드같아요. 심플하게도 풀어낼 수 있을 것 같아요!
- [ ] End 클래스는 CommandState의 대부분의 메서드가 지원하지 않아요. 그런경우 상속이 올바른 상황인지 의심해보는것도 좋아보여요!
    - 추상화는 좋지만 필요한 상황이 아니라면 오히려 코드가 복잡해질 수 있습니다!
    - 수단이 목적이 되지 않도록 코드를 짜보면 좋을 것 같아요!
- [ ] Enum의 경우 모든 객체가 상수, 즉 유일한 객체입니다. 그래서 동등성 비교를 할 때는 equals도 좋지만 ==으로 처리하는 것이 좋아보여요!
- [ ] 체스판에서 Position는 모두 64개입니다. 고정된 개수를 가진 position의 경우 캐싱을 적용해보는것도 좋을 것 같아요!
- [ ] MatchResult는 없어도 될 것 같아요. 혹시 어떠한 의도로 만든 클래스일까요?
- [ ] (Direction) `getBlackPawnDirections()`와 같은 메서드들은 Piece의 타입을 잘 관리하면 분리가 될 수 있을 것 같아요!
- [ ] (Column, Row) 사용 용도와 목적에 맞는 메서드를 구현한다면 좋을 것 같아요!
    - canMove()와 findX()는 다른 역할을 하는데 끼워맞춘게 아닐까..?
    - 잘 구현한다면 try-catch를 사용하지 않아도 될 것 같다.
- [ ] 동작을 보면 `findX`보다는 `findByX`와 같은 이름이 적절해보여요!
- [ ] (BoardGenerator) 해당 인터페이스는 구현체가 단 1개 분인데 인터페이스로 추출한 이유가 있을까요?
- [ ] `BasicChessBoardGenerator`에서 바로 Board를 만들어서 반환해주는건 어떨까요? 그러면 Board를 통해 다시 생성하는 로직이 없어도 될 것 같아요!
