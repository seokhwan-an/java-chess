# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

----

## 기능 목록

### 체스판
- [x] 초기 체스판을 생성한다.
- [x] 자기 위치로는 움직일 수 없다.
- [x] 경로 내 장애물이 있는 지 검증한다.

### 기물 
- [x] 체스판을 구성하는 요소이다.
- [x] 움직인다.
    - [x] 빈 공간은 움직일 수 없다.
    - [x] pawn은 올바른 위치로 움직일 수 있다.
      - pawn 처음에만 2칸 전진이 가능하다
      - 처음 이후에는 전진만 가능하다
    - [x] rook은 올바른 위치로 움직일 수 있다.
      - 출발 및 도착 위치에 대하여 행의 차이가 0 이거나 열의 차이가 0인 경우에 이동이 가능하다.
    - [x] knight는 올바른 위치로 움직일 수 있다.
      - 출발 및 도착 위치에 대하여 행의 차와 열의 차의 절댓값이 (2,1), (1,2)에 포함되면 이동이 가능하다.
    - [x] bishop는 올바른 위치로 움직일 수 있다.
      - 출발 및 도착 위치에 대하여 행의 차와 열의 차의 절댓값이 같으면 이동이 가능하다.
    - [x] queen은 올바른 위치로 움직일 수 있다.
      - rook과 bishop의 움지이는 조건이 만족하면 이동이 가능하다.
    - [x] king은 올바른 위치로 움직일 수 있다.
      - 출발 및 도착 위치에 대하여 행의 차와 열의 차의 절댓값이 (0,1), (1,0), (1,1)에 포함되면 이동이 가능하다.

### 위치 
- [x] 유효한 위치인지 검증한다.

### 경로
- [x] 출발 위치와 도착 위치 사이의 경로를 반환해준다

### 입력
- [x] 게임 명령어를 입력 받는다(start, end, move)

### 출력
- [x] 게임 시작 문구를 출력한다.
- [x] 체스판 현황을 출력한다.

### 리팩토링 목록
- [ ] 테스트 코드 가독성 높게 수정하기 (parameterizedTest name 속성 이용하기)
- [ ] 빈공간을 의미하는 객체 명칭을 보다 명확하게 하기
- [ ] Name 객체 대신에 Team으로 기물들을 나누기
- [ ] 기물(Piece) 객체가 상태로 방향 객체를 가지게 하기
- [ ] 움직임에 대한 검증 로직을 더 세분화 하기